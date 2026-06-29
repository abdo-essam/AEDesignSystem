package com.ae.designsystem.sample

/**
 * Type-safe navigation destinations for the catalog shell.
 *
 * Replaces integer-indexed tab navigation to eliminate magic numbers
 * and make routing logic explicit and exhaustive.
 */
internal enum class CatalogDestination(val label: String) {
    GettingStarted(label = "Getting Started"),
    Foundation(label = "Foundation"),
    Components(label = "Components"),
    Playground(label = "Playground"),
    Theme(label = "Theme"),
    About(label = "About"),
}
