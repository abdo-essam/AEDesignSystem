package com.ae.designsystem.foundation.color

import androidx.compose.ui.graphics.Color

/**
 * Accent color families — orthogonal to palette selection.
 * Each accent defines a primary, hover, and muted variant.
 */
public enum class AEAccent(
    public val primary: Color,
    public val hover: Color,
    public val muted: Color,
    public val onAccent: Color,
) {
    Blue(
        primary = Color(0xFF3B82F6),
        hover = Color(0xFF2563EB),
        muted = Color(0xFF1D4ED8),
        onAccent = Color.White,
    ),
    Violet(
        primary = Color(0xFF8B5CF6),
        hover = Color(0xFF7C3AED),
        muted = Color(0xFF6D28D9),
        onAccent = Color.White,
    ),
    Rose(
        primary = Color(0xFFF43F5E),
        hover = Color(0xFFE11D48),
        muted = Color(0xFFBE123C),
        onAccent = Color.White,
    ),
    Emerald(
        primary = Color(0xFF10B981),
        hover = Color(0xFF059669),
        muted = Color(0xFF047857),
        onAccent = Color.White,
    ),
    Amber(
        primary = Color(0xFFF59E0B),
        hover = Color(0xFFD97706),
        muted = Color(0xFFB45309),
        onAccent = Color(0xFF1C1917),
    ),
    Cyan(
        primary = Color(0xFF06B6D4),
        hover = Color(0xFF0891B2),
        muted = Color(0xFF0E7490),
        onAccent = Color.White,
    ),
}
