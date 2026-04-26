package com.ae.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.theme.AETheme

/** State variants for [AETextField]. */
public enum class AETextFieldState { Default, Error, Success }

/**
 * Single-line text input field — slot-based, zero Material3.
 *
 * ```
 * var text by remember { mutableStateOf("") }
 * AETextField(
 *     value = text,
 *     onValueChange = { text = it },
 *     label = "Email",
 *     leadingIcon = AEIcons.User,
 * )
 * ```
 */
@Composable
public fun AETextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    leadingIcon: AEIconToken? = null,
    trailingIcon: AEIconToken? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    state: AETextFieldState = AETextFieldState.Default,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val colors = AETheme.colors
    val radius = AETheme.radius
    val spacing = AETheme.spacing
    val typography = AETheme.typography
    val motion = AETheme.motion

    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.border.copy(alpha = 0.4f)
            state == AETextFieldState.Error -> colors.destructive
            state == AETextFieldState.Success -> colors.success
            isFocused -> colors.borderFocused
            else -> colors.inputBorder
        },
        animationSpec = tween(motion.durationFast),
        label = "borderColor",
    )

    val containerColor = if (enabled) colors.inputBackground else colors.backgroundSecondary
    val shape = RoundedCornerShape(radius.md)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        // Label
        if (label != null) {
            AEText(
                text = label,
                style = typography.labelMedium,
                color = if (enabled) colors.textSecondary else colors.textMuted,
            )
        }

        // Input container
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = singleLine,
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            textStyle = typography.bodyMedium.copy(color = if (enabled) colors.textPrimary else colors.textMuted),
            cursorBrush = SolidColor(colors.accent),
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(containerColor)
                .border(1.dp, borderColor, shape)
                .semantics {
                    if (label != null) contentDescription = label
                },
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.md, vertical = spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                ) {
                    if (leadingIcon != null) {
                        AEIcon(
                            token = leadingIcon,
                            size = 18.dp,
                            tint = if (isFocused) colors.accent else colors.textMuted,
                        )
                    }

                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty() && placeholder != null) {
                            AEText(
                                text = placeholder,
                                style = typography.bodyMedium,
                                color = colors.textMuted,
                            )
                        }
                        innerTextField()
                    }

                    when {
                        trailingContent != null -> trailingContent()
                        trailingIcon != null -> AEIcon(
                            token = trailingIcon,
                            size = 18.dp,
                            tint = colors.textMuted,
                        )
                    }
                }
            },
        )

        // Supporting text / error
        if (supportingText != null) {
            AEText(
                text = supportingText,
                style = typography.bodySmall,
                color = when (state) {
                    AETextFieldState.Error -> colors.destructive
                    AETextFieldState.Success -> colors.success
                    else -> colors.textMuted
                },
            )
        }
    }
}
