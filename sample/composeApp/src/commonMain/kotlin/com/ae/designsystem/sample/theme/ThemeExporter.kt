package com.ae.designsystem.sample.theme

import com.ae.designsystem.sample.ThemeConfiguration

/**
 * Pure function — converts a [ThemeConfiguration] to a formatted string
 * for the given [ExportFormat].
 *
 * No side effects. Stateless. Easily unit-testable.
 */
internal object ThemeExporter {

    fun export(config: ThemeConfiguration, format: ExportFormat): String = when (format) {
        ExportFormat.Kotlin -> toKotlin(config)
        ExportFormat.Json   -> toJson(config)
        ExportFormat.Yaml   -> toYaml(config)
    }

    private fun toKotlin(config: ThemeConfiguration): String = """
        AETheme(
            palette   = AEPalette.${config.palette.name},
            accent    = AEAccent.${config.accent.name},
            preset    = AEStylePreset.${config.preset.name},
            darkTheme = ${config.darkMode},
        ) {
            // Your content
        }
    """.trimIndent()

    private fun toJson(config: ThemeConfiguration): String = """
        {
          "palette":  "${config.palette.name}",
          "accent":   "${config.accent.name}",
          "preset":   "${config.preset.name}",
          "darkMode":  ${config.darkMode}
        }
    """.trimIndent()

    private fun toYaml(config: ThemeConfiguration): String = """
        theme:
          palette:  ${config.palette.name}
          accent:   ${config.accent.name}
          preset:   ${config.preset.name}
          darkMode: ${config.darkMode}
    """.trimIndent()
}
