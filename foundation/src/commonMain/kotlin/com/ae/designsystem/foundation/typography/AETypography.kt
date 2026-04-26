package com.ae.designsystem.foundation.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Typography scale for AEDesignSystem.
 *
 * Uses **Barlow** as the default font family. Consumers can override
 * the entire scale or individual styles via [AETheme].
 *
 * Note: The actual Barlow [FontFamily] is loaded from compose resources
 * and provided via [AEFontFamily.Barlow]. If unavailable on a platform,
 * the system default sans-serif is used as fallback.
 */
@Immutable
public data class AETypography(
    /** Hero headings — 36sp bold */
    val displayLarge: TextStyle,
    /** Secondary display — 30sp bold */
    val displayMedium: TextStyle,
    /** Page titles — 24sp semibold */
    val headingLarge: TextStyle,
    /** Section titles — 20sp semibold */
    val headingMedium: TextStyle,
    /** Card titles — 16sp semibold */
    val headingSmall: TextStyle,
    /** Primary body — 16sp regular */
    val bodyLarge: TextStyle,
    /** Default body — 14sp regular */
    val bodyMedium: TextStyle,
    /** Secondary text — 12sp regular */
    val bodySmall: TextStyle,
    /** Button labels — 14sp medium */
    val labelLarge: TextStyle,
    /** Form labels — 12sp medium */
    val labelMedium: TextStyle,
    /** Overlines, captions — 11sp medium */
    val labelSmall: TextStyle,
    /** Monospace for code — 13sp regular */
    val code: TextStyle,
) {
    public companion object {
        /**
         * Creates the default typography scale using the given [fontFamily].
         * Pass [AEFontFamily.Barlow] for the branded default, or any custom
         * [FontFamily] for brand overrides.
         */
        public fun default(fontFamily: FontFamily = FontFamily.SansSerif): AETypography {
            return AETypography(
                displayLarge = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    lineHeight = 44.sp,
                    letterSpacing = (-0.25).sp,
                ),
                displayMedium = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    lineHeight = 38.sp,
                    letterSpacing = (-0.25).sp,
                ),
                headingLarge = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    letterSpacing = (-0.15).sp,
                ),
                headingMedium = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                ),
                headingSmall = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                ),
                bodyLarge = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                ),
                bodyMedium = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                ),
                bodySmall = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                ),
                labelLarge = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                ),
                labelMedium = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                ),
                labelSmall = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    letterSpacing = 0.5.sp,
                ),
                code = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                ),
            )
        }
    }
}
