import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.Base64

abstract class GenerateComponentSourcesTask : DefaultTask() {
    @get:InputDirectory
    abstract val componentsUiDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val dirs = componentsUiDir.get().asFile
            .listFiles()
            ?.filter { it.isDirectory }
            ?.sortedBy { it.name }
            ?: emptyList()

        val sb = StringBuilder()
        sb.appendLine("package com.ae.designsystem.sample.docs.sources")
        sb.appendLine()
        sb.appendLine("import kotlin.io.encoding.Base64")
        sb.appendLine("import kotlin.io.encoding.ExperimentalEncodingApi")
        sb.appendLine()
        sb.appendLine("data class SourceFile(val name: String, val content: String)")
        sb.appendLine()
        sb.appendLine("@OptIn(ExperimentalEncodingApi::class)")
        sb.appendLine("object ComponentSources {")
        sb.appendLine("    private fun d(b64: String): String =")
        sb.appendLine("        Base64.decode(b64).decodeToString()")
        sb.appendLine()
        sb.appendLine("    val sources: Map<String, List<SourceFile>> = mapOf(")

        dirs.forEach { dir ->
            val ktFiles = dir.listFiles()
                ?.filter { it.extension == "kt" }
                ?.sortedBy { it.name }
                ?: emptyList()
            if (ktFiles.isNotEmpty()) {
                sb.appendLine("        \"${dir.name}\" to listOf(")
                ktFiles.forEach { file ->
                    val encoded = Base64.getEncoder().encodeToString(file.readBytes())
                    sb.appendLine("            SourceFile(\"${file.name}\", d(\"$encoded\")),")
                }
                sb.appendLine("        ),")
            }
        }

        sb.appendLine("    )")
        sb.appendLine("}")

        val outFile = outputDir.get().asFile.resolve(
            "com/ae/designsystem/sample/docs/sources/ComponentSources.kt",
        )
        outFile.parentFile.mkdirs()
        outFile.writeText(sb.toString())
    }
}
