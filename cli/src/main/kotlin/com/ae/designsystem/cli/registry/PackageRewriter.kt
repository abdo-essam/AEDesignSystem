package com.ae.designsystem.cli.registry

private const val SOURCE_BASE_PACKAGE = "com.ae.designsystem.components.ui"

object PackageRewriter {

    fun rewrite(source: String, targetBasePackage: String): String {
        if (targetBasePackage.isBlank()) return source

        return source
            .lineSequence()
            .joinToString("\n") { line ->
                when {
                    line.startsWith("package $SOURCE_BASE_PACKAGE") ->
                        line.replace(SOURCE_BASE_PACKAGE, targetBasePackage)

                    line.startsWith("import $SOURCE_BASE_PACKAGE") ->
                        line.replace(SOURCE_BASE_PACKAGE, targetBasePackage)

                    else -> line
                }
            }
    }
}
