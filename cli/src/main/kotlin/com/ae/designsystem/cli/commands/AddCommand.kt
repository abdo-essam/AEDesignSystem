package com.ae.designsystem.cli.commands

import com.github.ajalt.clikt.core.Abort
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.ae.designsystem.cli.model.RegistryComponent
import com.ae.designsystem.cli.model.AeConfig
import com.ae.designsystem.cli.registry.ConfigManager
import com.ae.designsystem.cli.registry.PackageRewriter
import com.ae.designsystem.cli.registry.RegistryClient
import java.io.File

class AddCommand : CliktCommand(name = "add") {

    override fun help(context: Context) = "Add components to your project"

    private val components by argument(help = "Component names (e.g. button dialog card)")
        .multiple(required = false)

    private val pathFlag by option("--path", help = "Target directory for component files")

    private val packageFlag by option("--package", "-p", help = "Target package name")

    private val registryFlag by option("--registry", "-r", help = "Registry URL")

    private val overwrite by option("--overwrite", "-o", help = "Overwrite existing files")
        .flag()

    private val dryRun by option("--dry-run", help = "Preview changes without writing files")
        .flag()

    private val all by option("--all", "-a", help = "Add all available components")
        .flag()

    override fun run() {
        if (!all && components.isEmpty()) {
            echo("Error: Provide component names or use --all.", err = true)
            throw Abort()
        }

        val resolved = resolveTargets()
        val componentsDir = resolved.first
        val packageName = resolved.second
        val registryUrl = resolved.third

        val client = RegistryClient(registryUrl)

        val names = if (all) {
            echo("Fetching component index...")
            val index = try {
                client.fetchIndex()
            } catch (e: Exception) {
                echo("Error: Failed to fetch registry index: ${e.message}", err = true)
                throw Abort()
            }
            index.items.map { it.name }
        } else {
            components.map { it.lowercase() }
        }

        echo("Resolving dependencies...")
        val tree: List<RegistryComponent>
        try {
            tree = client.resolveTree(names)
        } catch (e: Exception) {
            echo("Error: Failed to fetch components: ${e.message}", err = true)
            throw Abort()
        }

        val projectDir = ConfigManager.projectRoot() ?: File(System.getProperty("user.dir"))
        val baseDir = projectDir.resolve(componentsDir)

        echo("  Target: ${baseDir.absolutePath}")
        echo("")

        var filesWritten = 0
        var filesSkipped = 0
        val addedComponents = mutableListOf<String>()
        val implicitDeps = mutableListOf<String>()
        val writtenFiles = mutableListOf<String>()

        tree.forEach { component ->
            var componentWritten = false
            component.files.forEach fileLoop@{ file ->
                val relativePath = file.path.removePrefix("ui/")
                val targetFile = baseDir.resolve(relativePath)

                if (targetFile.exists() && !overwrite) {
                    filesSkipped++
                    return@fileLoop
                }

                val rewritten = PackageRewriter.rewrite(file.content, packageName)

                if (dryRun) {
                    echo("  [dry-run] Would write: ${targetFile.relativeTo(projectDir)}")
                } else {
                    try {
                        targetFile.parentFile.mkdirs()
                        targetFile.writeText(rewritten)
                    } catch (e: Exception) {
                        echo(
                            "Error: Cannot write to ${targetFile.path}: ${e.message}",
                            err = true,
                        )
                        throw Abort()
                    }
                    filesWritten++
                    writtenFiles.add(targetFile.relativeTo(projectDir).path)
                }
                componentWritten = true
            }

            if (componentWritten || dryRun) {
                if (component.name in names) {
                    addedComponents.add(component.name)
                } else {
                    implicitDeps.add(component.name)
                }
            }
        }

        echo("")
        if (dryRun) {
            echo("  Dry run complete. No files were written.")
        } else if (filesWritten > 0) {
            addedComponents.forEach { name ->
                echo("  + Added $name")
            }
            implicitDeps.forEach { name ->
                echo("  + Added $name (dependency)")
            }
            echo("")
            echo("  Files written:")
            writtenFiles.forEach { path ->
                echo("    $path")
            }
            if (filesSkipped > 0) {
                echo("")
                echo("  ~ Skipped $filesSkipped existing files (use --overwrite to replace)")
            }
        } else if (filesSkipped > 0) {
            echo("  All components already exist. ($filesSkipped files skipped)")
            echo("  Use --overwrite to replace existing files.")
        } else {
            echo("  No files to write.")
        }

        val gradleDeps = tree.flatMap { it.gradleDependencies }.distinct()
        if (gradleDeps.isNotEmpty()) {
            echo("")
            echo("  Ensure these dependencies are in your build.gradle.kts:")
            gradleDeps.forEach { dep ->
                echo("  implementation(\"$dep\")")
            }
        }

        echo("")
    }

    private fun resolveTargets(): Triple<String, String, String> {
        val defaultRegistry = "https://aedesignsystem.com/r"

        if (pathFlag != null && packageFlag != null) {
            return Triple(pathFlag!!, packageFlag!!, registryFlag ?: defaultRegistry)
        }

        val config = ConfigManager.load()
        if (config != null) {
            return Triple(
                pathFlag ?: config.componentsDir,
                packageFlag ?: config.packageName,
                registryFlag ?: config.registry,
            )
        }

        val projectDir = File(System.getProperty("user.dir"))
        echo("")
        echo("  No ae.json found. Let's set up.")
        echo("")

        val packageName = packageFlag ?: promptPackageName(projectDir)

        val componentsDir = pathFlag ?: promptComponentsDir(projectDir, packageName)

        echo("")
        echo("  Package:    $packageName")
        echo("  Components: $componentsDir")
        echo("")

        echo("  Save as default? (Y/n): ", trailingNewline = false)
        val save = readlnOrNull()?.trim()?.lowercase()
        if (save != "n") {
            val newConfig = AeConfig(
                packageName = packageName,
                componentsDir = componentsDir,
            )
            ConfigManager.save(newConfig)
            echo("  Saved to ae.json")
        }

        echo("")

        return Triple(componentsDir, packageName, registryFlag ?: defaultRegistry)
    }

    private fun promptPackageName(projectDir: File): String {
        val detected = ProjectDetector.detectPackageName(projectDir)
        if (detected != null) {
            echo("  Detected package: $detected")
            echo("  Package name [$detected]: ", trailingNewline = false)
            val input = readlnOrNull()?.trim()
            return if (input.isNullOrBlank()) detected else input
        }

        echo("  Package name (e.g. com.myapp.ui): ", trailingNewline = false)
        val input = readlnOrNull()?.trim()
        if (input.isNullOrBlank()) {
            echo("Error: Package name is required.", err = true)
            throw Abort()
        }
        return input
    }

    private fun promptComponentsDir(projectDir: File, packageName: String): String {
        val candidates = ProjectDetector.scanSourceDirs(projectDir)
        val packagePath = packageName.replace('.', '/')

        if (candidates.isNotEmpty()) {
            echo("")
            echo("  Where should components be added?")
            echo("")
            candidates.forEachIndexed { index, dir ->
                echo("  ${index + 1}) $dir/$packagePath")
            }
            echo("  ${candidates.size + 1}) Browse folders...")
            echo("")
            echo("  Choose [1]: ", trailingNewline = false)
            val input = readlnOrNull()?.trim()
            val choice = if (input.isNullOrBlank()) 1 else input.toIntOrNull() ?: 1

            return if (choice in 1..candidates.size) {
                "${candidates[choice - 1]}/$packagePath"
            } else {
                browseFolders(projectDir) ?: throw Abort()
            }
        }

        echo("")
        echo("  No source directories detected.")
        return browseFolders(projectDir) ?: throw Abort()
    }

    private fun browseFolders(projectDir: File): String? {
        val selected = FolderBrowser.browse(
            projectRoot = projectDir,
            echo = { msg, newline -> echo(msg, trailingNewline = newline) },
        )

        if (selected == null) {
            echo("  Cancelled.")
            return null
        }

        echo("")
        echo("  Selected: $selected")
        return selected
    }
}
