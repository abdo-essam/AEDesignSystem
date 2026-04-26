package com.ae.designsystem.foundation.accessibility

import androidx.compose.foundation.focusable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Draws a visible focus ring around a component when focused.
 *
 * Uses the theme's `ring` color token by default. Applied as a
 * draw-behind modifier so it doesn't affect layout.
 *
 * @param focused Whether the component is currently focused.
 * @param color Focus ring color.
 * @param width Ring stroke width.
 * @param cornerRadius Ring corner radius.
 * @param offset Gap between component edge and ring.
 */
public fun Modifier.aeFocusRing(
    focused: Boolean,
    color: Color,
    width: Dp = 2.dp,
    cornerRadius: Dp = 8.dp,
    offset: Dp = 2.dp,
): Modifier = if (focused) {
    this.drawBehind {
        val strokeWidth = width.toPx()
        val offsetPx = offset.toPx()
        drawRoundRect(
            color = color,
            topLeft = Offset(-offsetPx, -offsetPx),
            size = Size(
                size.width + offsetPx * 2,
                size.height + offsetPx * 2,
            ),
            cornerRadius = CornerRadius(cornerRadius.toPx()),
            style = Stroke(width = strokeWidth),
        )
    }
} else {
    this
}

/**
 * Minimum interactive component size per WCAG guidelines (44×44dp).
 */
public val MinimumInteractiveSize: Dp = 44.dp

/**
 * Creates a [FocusRequester] that automatically requests focus when
 * the composable enters composition.
 *
 * Useful for dialogs, modals, and first-field-in-form auto-focus.
 */
@Composable
public fun rememberAutoFocusRequester(): FocusRequester {
    val requester = androidx.compose.runtime.remember { FocusRequester() }
    LaunchedEffect(Unit) {
        requester.requestFocus()
    }
    return requester
}

/**
 * Applies minimum interactive size and focusable modifier.
 */
public fun Modifier.aeInteractive(
    focusRequester: FocusRequester? = null,
): Modifier {
    var mod = this
        .focusable()
    if (focusRequester != null) {
        mod = mod.focusRequester(focusRequester)
    }
    return mod
}
