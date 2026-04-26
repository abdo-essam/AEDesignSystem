package com.ae.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

/** Sizes for [AEAvatar]. */
public enum class AEAvatarSize(public val dp: Dp) {
    XSmall(24.dp), Small(32.dp), Medium(40.dp), Large(56.dp), XLarge(72.dp)
}

/**
 * Avatar — circular user identity component with fallback initials or icon.
 *
 * ```
 * // Initials fallback
 * AEAvatar(initials = "AE")
 *
 * // Custom icon
 * AEAvatar(size = AEAvatarSize.Large, icon = AEIcons.User)
 *
 * // With status indicator
 * AEAvatar(initials = "JD", showOnlineStatus = true)
 * ```
 */
@Composable
public fun AEAvatar(
    modifier: Modifier = Modifier,
    initials: String? = null,
    size: AEAvatarSize = AEAvatarSize.Medium,
    backgroundColor: Color = AETheme.colors.accentMuted,
    contentColor: Color = AETheme.colors.accent,
    borderColor: Color? = null,
    showOnlineStatus: Boolean = false,
    isOnline: Boolean = true,
    shape: Shape = CircleShape,
) {
    val colors = AETheme.colors
    val typography = AETheme.typography

    val sizeDp = size.dp
    val iconSize = sizeDp * 0.5f
    val textStyle = when (size) {
        AEAvatarSize.XSmall -> typography.labelSmall
        AEAvatarSize.Small -> typography.labelMedium
        AEAvatarSize.Medium -> typography.labelLarge
        AEAvatarSize.Large -> typography.headingSmall
        AEAvatarSize.XLarge -> typography.headingMedium
    }

    Box(modifier = modifier) {
        // Main avatar circle
        Box(
            modifier = Modifier
                .size(sizeDp)
                .clip(shape)
                .background(backgroundColor)
                .then(
                    if (borderColor != null) Modifier.border(2.dp, borderColor, shape)
                    else Modifier
                ),
            contentAlignment = Alignment.Center,
        ) {
            when {
                initials != null -> AEText(
                    text = initials.take(2).uppercase(),
                    style = textStyle,
                    color = contentColor,
                )
                else -> AEIcon(
                    token = AEIcons.User,
                    tint = contentColor,
                    size = iconSize,
                )
            }
        }

        // Online status indicator
        if (showOnlineStatus) {
            val dotSize = sizeDp * 0.28f
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(colors.surface)
                    .padding(1.5.dp)
                    .clip(CircleShape)
                    .background(if (isOnline) colors.success else colors.textMuted),
            )
        }
    }
}

/**
 * Avatar group — overlapping stack of [AEAvatar]s.
 *
 * ```
 * AEAvatarGroup(
 *     avatars = listOf("AE", "JD", "MK"),
 *     maxVisible = 3,
 * )
 * ```
 */
@Composable
public fun AEAvatarGroup(
    avatars: List<String>,
    modifier: Modifier = Modifier,
    size: AEAvatarSize = AEAvatarSize.Small,
    maxVisible: Int = 4,
) {
    val colors = AETheme.colors
    val shown = avatars.take(maxVisible)
    val overflow = avatars.size - maxVisible

    Row(modifier = modifier) {
        shown.forEachIndexed { index, initials ->
            Box(modifier = Modifier.offset(x = (-8 * index).dp)) {
                AEAvatar(
                    initials = initials,
                    size = size,
                    borderColor = colors.background,
                )
            }
        }
        if (overflow > 0) {
            Box(modifier = Modifier.offset(x = (-8 * shown.size).dp)) {
                AEAvatar(
                    initials = "+$overflow",
                    size = size,
                    backgroundColor = colors.surfaceHover,
                    contentColor = colors.textMuted,
                    borderColor = colors.background,
                )
            }
        }
    }
}
