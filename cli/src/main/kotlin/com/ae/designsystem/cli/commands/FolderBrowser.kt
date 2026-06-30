package com.ae.designsystem.cli.commands

import java.io.File

object FolderBrowser {

    fun browse(
        projectRoot: File,
        startDir: File = projectRoot,
        echo: (String, Boolean) -> Unit,
    ): String? {
        var current = if (startDir.isDirectory) startDir else projectRoot

        while (true) {
            val relativePath = current.relativeTo(projectRoot).path.ifEmpty { "." }

            echo("", true)
            echo("  📂 $relativePath", true)
            echo("", true)

            val subdirs = current.listFiles()
                ?.filter { it.isDirectory && !it.name.startsWith(".") && it.name != "build" }
                ?.sortedBy { it.name }
                ?: emptyList()

            if (subdirs.isEmpty()) {
                echo("  (empty)", true)
            } else {
                subdirs.forEachIndexed { index, dir ->
                    echo("  ${index + 1}) ${dir.name}/", true)
                }
            }

            val canGoUp = current.absolutePath != projectRoot.absolutePath
            if (canGoUp) {
                echo("  0) ../", true)
            }

            echo("", true)
            echo("  [s]elect this folder | [q]uit | number to enter", true)
            echo("  > ", false)

            val input = readlnOrNull()?.trim()?.lowercase() ?: return null

            when {
                input == "s" || input == "select" -> {
                    return current.relativeTo(projectRoot).path.ifEmpty { "." }
                }

                input == "q" || input == "quit" -> {
                    return null
                }

                input == "0" && canGoUp -> {
                    current = current.parentFile ?: current
                }

                input.toIntOrNull() != null -> {
                    val idx = input.toInt() - 1
                    if (idx in subdirs.indices) {
                        current = subdirs[idx]
                    } else {
                        echo("  Invalid choice.", true)
                    }
                }

                else -> {
                    echo("  Invalid input. Enter a number, 's' to select, or 'q' to quit.", true)
                }
            }
        }
    }
}
