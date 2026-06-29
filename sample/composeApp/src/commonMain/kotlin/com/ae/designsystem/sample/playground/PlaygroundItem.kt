package com.ae.designsystem.sample.playground

import androidx.compose.runtime.Composable

/**
 * Contract for a self-contained playground module.
 *
 * Each implementation encapsulates:
 *  - A [title] displayed in the component selector.
 *  - A [Controls] composable for parameter knobs (variant, size, state, etc.).
 *  - A [Preview] composable showing the live-updated component render.
 *
 * Adding a new playground component requires only implementing this interface
 * and registering it in [playgroundItems] — zero modifications to the shell.
 *
 * Example:
 * ```
 * class ButtonPlayground : PlaygroundItem {
 *     override val title = "Button"
 *
 *     @Composable override fun Controls() { ... }
 *     @Composable override fun Preview()  { ... }
 * }
 * ```
 */
internal interface PlaygroundItem {
    val title: String

    @Composable
    fun Controls()

    @Composable
    fun Preview()
}
