package com.ae.designsystem.components.ui.slider

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.semantics.*
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme
import kotlin.math.roundToInt

/**
 * Horizontal slider — drag-based value input, zero Material3.
 *
 * ```
 * var volume by remember { mutableFloatStateOf(0.5f) }
 * AESlider(value = volume, onValueChange = { volume = it })
 * ```
 *
 * @param value Current value in [valueRange].
 * @param onValueChange Callback with updated value.
 * @param valueRange Allowed value range. Defaults to 0f..1f.
 * @param enabled Whether the slider is interactive.
 */
@Composable
public fun AESlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    enabled: Boolean = true,
) {
    val colors = AETheme.colors
    val motion = AETheme.motion

    val fraction = ((value - valueRange.start) / (valueRange.endInclusive - valueRange.start))
        .coerceIn(0f, 1f)

    var trackWidthPx by remember { mutableIntStateOf(0) }
    val thumbSizeDp = 20.dp

    val animatedFraction by animateFloatAsState(
        targetValue = fraction,
        animationSpec = tween(motion.durationFast),
        label = "sliderFraction",
    )

    val trackColor = if (enabled) colors.accent else colors.textMuted
    val trackBg = colors.border

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thumbSizeDp)
            .onSizeChanged { trackWidthPx = it.width }
            .semantics(mergeDescendants = true) {
                contentDescription = "Slider, value ${(value * 100).roundToInt()}%"
            }
            .then(
                if (enabled) Modifier.pointerInput(valueRange, trackWidthPx) {
                    detectHorizontalDragGestures { change, _ ->
                        change.consume()
                        if (trackWidthPx > 0) {
                            val newFraction = (change.position.x / trackWidthPx).coerceIn(0f, 1f)
                            onValueChange(valueRange.start + newFraction * (valueRange.endInclusive - valueRange.start))
                        }
                    }
                }.pointerInput(valueRange, trackWidthPx) {
                    detectTapGestures { offset ->
                        if (trackWidthPx > 0) {
                            val newFraction = (offset.x / trackWidthPx).coerceIn(0f, 1f)
                            onValueChange(valueRange.start + newFraction * (valueRange.endInclusive - valueRange.start))
                        }
                    }
                } else Modifier
            ),
        contentAlignment = Alignment.CenterStart,
    ) {
        // Track background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(trackBg),
        )

        // Active fill
        if (animatedFraction > 0f) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedFraction)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(trackColor),
            )
        }

        // Thumb
        BoxWithConstraints(Modifier.fillMaxSize()) {
            val trackPx = constraints.maxWidth.toFloat()
            val thumbPx = with(androidx.compose.ui.platform.LocalDensity.current) { thumbSizeDp.toPx() }
            val offsetX = ((trackPx * animatedFraction) - thumbPx / 2)
                .coerceIn(0f, (trackPx - thumbPx).coerceAtLeast(0f))
                .roundToInt()

            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX, 0) }
                    .size(thumbSizeDp)
                    .clip(CircleShape)
                    .background(trackColor),
            )
        }
    }
}
