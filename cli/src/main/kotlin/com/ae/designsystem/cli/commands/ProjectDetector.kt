package com.ae.designsystem.cli.commands

import java.io.File

object ProjectDetector {

    fun detectPackageName(projectDir: File): String? {
        val gradleFiles = listOf(
            "build.gradle.kts",
            "app/build.gradle.kts",
            "composeApp/build.gradle.kts",
        )

        for (path in gradleFiles) {
            val file = projectDir.resolve(path)
            if (file.exists()) {
                val match = Regex("""namespace\s*=\s*"([^"]+)"""").find(file.readText())
                if (match != null) return match.groupValues[1]
            }
        }

        return null
    }

    fun scanSourceDirs(projectDir: File): List<String> {
        val sourceSets = listOf("commonMain", "main", "webMain", "desktopMain", "androidMain")
        val found = mutableListOf<String>()

        projectDir.walk()
            .maxDepth(4)
            .filter { it.isDirectory && it.name == "kotlin" }
            .forEach { kotlinDir ->
                val sourceSetDir = kotlinDir.parentFile ?: return@forEach
                val srcDir = sourceSetDir.parentFile ?: return@forEach
                if (srcDir.name != "src") return@forEach

                val relativePath = kotlinDir.relativeTo(projectDir).path
                if (relativePath.contains("build/") || relativePath.contains("buildSrc/")) return@forEach

                found.add(relativePath)
            }

        return found.sortedWith(
            compareBy<String> { path ->
                val sourceSet = sourceSets.indexOfFirst {
                    path.contains("/$it/") || path.contains("\\$it\\")
                }
                if (sourceSet >= 0) sourceSet else sourceSets.size
            }.thenBy { it.length },
        ).distinct()
    }
}
