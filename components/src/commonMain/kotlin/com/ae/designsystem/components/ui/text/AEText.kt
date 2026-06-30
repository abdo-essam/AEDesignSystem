package com.ae.designsystem.components.ui.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Text component applying AEDesignSystem typography tokens.
 *
 * Thin wrapper around [BasicText] — avoids Material3 `Text`.
 *
 * ```
 * AEText("Hello World")
 * AEText("Title", style = AETheme.typography.headingLarge)
 * AEText("Muted", color = AETheme.colors.textMuted)
 * ```
 *
 * @param text The text string to render.
 * @param modifier Modifier for the text element.
 * @param style Typography style. Defaults to [AETheme.typography.bodyMedium].
 * @param color Text color override. If provided, merges with [style].
 * @param maxLines Maximum lines before truncation. [Int.MAX_VALUE] for unlimited.
 * @param overflow How to handle text overflow.
 */
@Composable
public fun AEText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = AETheme.typography.bodyMedium,
    color: Color = AETheme.colors.textPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    BasicText(
        text = text,
        modifier = modifier,
        style = style.copy(color = color),
        maxLines = maxLines,
        overflow = overflow,
    )
}
