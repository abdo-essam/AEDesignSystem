package com.ae.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Card component with 3 optional slots: header, content, footer.
 *
 * ```
 * AECard(
 *     header = { Text("Title", style = AETheme.typography.headingSmall) },
 *     footer = { AEButton(onClick = {}) { Text("Action") } },
 * ) {
 *     Text("Card body content")
 * }
 * ```
 *
 * @param modifier Modifier for the card root.
 * @param color Background color.
 * @param shape Corner shape.
 * @param elevation Shadow elevation.
 * @param header Optional header slot, rendered above content.
 * @param footer Optional footer slot, rendered below content.
 * @param content Main content slot.
 */
@Composable
public fun AECard(
    modifier: Modifier = Modifier,
    color: Color = AETheme.colors.surface,
    shape: Shape = RoundedCornerShape(AETheme.radius.lg),
    elevation: Dp = AETheme.elevation.sm,
    header: (@Composable () -> Unit)? = null,
    footer: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val spacing = AETheme.spacing

    AESurface(
        modifier = modifier,
        color = color,
        shape = shape,
        elevation = elevation,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.lg),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            if (header != null) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    header()
                }
            }

            content()

            if (footer != null) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    footer()
                }
            }
        }
    }
}
