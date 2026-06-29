package com.ae.designsystem.sample

import com.ae.designsystem.foundation.color.AEAccent
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.tokens.AEStylePreset

/**
 * Consolidated immutable theme state for the catalog shell.
 *
 * Replaces four separate state variables with a single source of truth.
 * Immutability ensures predictable, unidirectional state transitions.
 *
 * @param palette  Neutral grayscale palette (background, surface, text).
 * @param accent   Brand accent color family (primary, hover, muted).
 * @param preset   Spatial style preset (spacing, radius, motion feel).
 * @param darkMode Whether the catalog renders in dark theme.
 */
internal data class ThemeConfiguration(
    val palette: AEPalette = AEPalette.Zinc,
    val accent: AEAccent = AEAccent.Blue,
    val preset: AEStylePreset = AEStylePreset.Default,
    val darkMode: Boolean = true,
)
