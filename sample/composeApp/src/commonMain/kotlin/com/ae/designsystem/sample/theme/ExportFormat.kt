package com.ae.designsystem.sample.theme

/**
 * Supported export formats for the theme configurator.
 *
 * Designed for extensibility: future formats (e.g., TOML, Swift, Dart)
 * require only adding an entry and implementing [ThemeExporter] rendering.
 */
internal enum class ExportFormat(val label: String) {
    Kotlin(label = "Kotlin"),
    Json(label = "JSON"),
    Yaml(label = "YAML"),
}
