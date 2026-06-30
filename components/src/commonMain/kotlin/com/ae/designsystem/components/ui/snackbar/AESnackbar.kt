package com.ae.designsystem.components.ui.snackbar

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.theme.AETheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.text.AEText

/** Visual style of a [AESnackbar]. */
public enum class AESnackbarVariant { Default, Success, Destructive, Warning, Info }

/**
 * Snackbar state — hoist this above your scaffold and call [show] to display messages.
 *
 * ```
 * val snackbar = rememberSnackbarState()
 *
 * AESnackbarHost(state = snackbar)
 *
 * // Trigger from anywhere in scope:
 * scope.launch { snackbar.show("Item deleted", variant = AESnackbarVariant.Destructive) }
 * ```
 */
public class AESnackbarState {
    internal var message by mutableStateOf("")
    internal var actionLabel by mutableStateOf<String?>(null)
    internal var variant by mutableStateOf(AESnackbarVariant.Default)
    internal var visible by mutableStateOf(false)
    internal var onAction: (() -> Unit)? = null

    public suspend fun show(
        message: String,
        actionLabel: String? = null,
        variant: AESnackbarVariant = AESnackbarVariant.Default,
        durationMs: Long = 3000L,
        onAction: (() -> Unit)? = null,
    ) {
        this.message = message
        this.actionLabel = actionLabel
        this.variant = variant
        this.onAction = onAction
        visible = true
        delay(durationMs)
        visible = false
    }

    public fun dismiss() { visible = false }
}

/** Creates and remembers an [AESnackbarState]. */
@Composable
public fun rememberSnackbarState(): AESnackbarState = remember { AESnackbarState() }

/**
 * Host that renders the snackbar when [state] has a visible message.
 *
 * Place this at the bottom of your scaffold:
 * ```
 * Box(Modifier.fillMaxSize()) {
 *     // your screen content
 *     AESnackbarHost(state = snackbar, modifier = Modifier.align(Alignment.BottomCenter))
 * }
 * ```
 */
@Composable
public fun AESnackbarHost(
    state: AESnackbarState,
    modifier: Modifier = Modifier,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius
    val motion = AETheme.motion

    val (bg, textColor) = when (state.variant) {
        AESnackbarVariant.Default -> (if (colors.isLight) Color(0xFF18181B) else Color(0xFFF4F4F5)) to
                (if (colors.isLight) Color(0xFFFAFAFA) else Color(0xFF18181B))
        AESnackbarVariant.Success -> colors.success to Color.White
        AESnackbarVariant.Destructive -> colors.destructive to colors.destructiveText
        AESnackbarVariant.Warning -> colors.warning to Color(0xFF1C1917)
        AESnackbarVariant.Info -> colors.info to Color.White
    }

    AnimatedVisibility(
        visible = state.visible,
        modifier = modifier.padding(spacing.lg).windowInsetsPadding(WindowInsets.navigationBars),
        enter = slideInVertically(tween(motion.durationMedium)) { it } + fadeIn(tween(motion.durationFast)),
        exit = slideOutVertically(tween(motion.durationMedium)) { it } + fadeOut(tween(motion.durationFast)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(AETheme.elevation.lg, RoundedCornerShape(radius.lg))
                .clip(RoundedCornerShape(radius.lg))
                .background(bg)
                .padding(horizontal = spacing.lg, vertical = spacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AEText(
                text = state.message,
                style = AETheme.typography.bodyMedium,
                color = textColor,
                modifier = Modifier.weight(1f),
            )

            if (state.actionLabel != null) {
                Spacer(Modifier.width(spacing.md))
                AEButton(
                    onClick = {
                        state.onAction?.invoke()
                        state.dismiss()
                    },
                    variant = AEButtonVariant.Ghost,
                    size = AEButtonSize.Small,
                ) {
                    AEText(
                        text = state.actionLabel!!,
                        style = AETheme.typography.labelMedium,
                        color = colors.accent,
                    )
                }
            }
        }
    }
}