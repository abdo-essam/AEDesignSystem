package com.ae.designsystem.sample.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.textfield.AETextField
import com.ae.designsystem.components.ui.progress.AELinearProgress
import com.ae.designsystem.components.ui.progress.AECircularProgress
import com.ae.designsystem.components.ui.checkbox.AECheckbox
import com.ae.designsystem.components.ui.switch.AESwitch
import com.ae.designsystem.components.ui.slider.AESlider
import com.ae.designsystem.components.ui.avatar.AEAvatar
import com.ae.designsystem.components.ui.avatar.AEAvatarSize
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.theme.AETheme

// --- Card Compatibility ---
@Composable
fun Card(
    modifier: Modifier = Modifier,
    label: String = "",
    content: @Composable ColumnScope.() -> Unit
) {
    AECard(modifier = modifier, content = content)
}

@Composable
fun CardHeader(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier, content = content)
}

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier, content = content)
}

@Composable
fun CardFooter(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = modifier, content = content)
}

@Composable
fun Separator(modifier: Modifier = Modifier) {
    AEDivider(modifier = modifier)
}

// --- Text Compatibility ---
enum class TextVariant {
    H1, H2, H3, H4, H5, Lead, P, Small, Muted, Large
}

@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    variant: TextVariant = TextVariant.P,
    color: Color? = null,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    val style = when (variant) {
        TextVariant.H1 -> AETheme.typography.displayLarge
        TextVariant.H2 -> AETheme.typography.displayMedium
        TextVariant.H3 -> AETheme.typography.headingLarge
        TextVariant.H4 -> AETheme.typography.headingMedium
        TextVariant.H5 -> AETheme.typography.headingSmall
        TextVariant.Lead -> AETheme.typography.bodyLarge
        TextVariant.P -> AETheme.typography.bodyMedium
        TextVariant.Small -> AETheme.typography.bodySmall
        TextVariant.Muted -> AETheme.typography.bodyMedium
        TextVariant.Large -> AETheme.typography.bodyLarge
    }
    val finalColor = color ?: if (variant == TextVariant.Muted) AETheme.colors.textMuted else AETheme.colors.textPrimary
    AEText(
        text = text,
        modifier = modifier,
        style = if (textAlign != null) style.copy(textAlign = textAlign) else style,
        color = finalColor,
        maxLines = maxLines,
        overflow = overflow,
    )
}

// --- Label, Input, Textarea Compatibility ---
@Composable
fun Label(text: String, modifier: Modifier = Modifier) {
    AEText(text = text, style = AETheme.typography.labelMedium)
}

@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    label: String? = null,
    modifier: Modifier = Modifier,
) {
    AETextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        label = label,
        modifier = modifier,
    )
}

@Composable
fun Textarea(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String? = null,
    label: String? = null,
    modifier: Modifier = Modifier,
) {
    AETextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        label = label,
        singleLine = false,
        modifier = modifier,
    )
}

// --- Spinner Compatibility ---
enum class SpinnerSize { Sm, Md, Lg }

@Composable
fun Spinner(size: SpinnerSize = SpinnerSize.Md, modifier: Modifier = Modifier) {
    val dpSize = when (size) {
        SpinnerSize.Sm -> 16.dp
        SpinnerSize.Md -> 24.dp
        SpinnerSize.Lg -> 40.dp
    }
    AECircularProgress(progress = null, size = dpSize, strokeWidth = 2.dp, modifier = modifier)
}

// --- ToggleGroup Compatibility ---
@Composable
fun ToggleGroup(content: @Composable () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        content()
    }
}

@Composable
fun ToggleGroupItem(text: String, selected: Boolean, onClick: () -> Unit) {
    AEButton(
        onClick = onClick,
        variant = if (selected) AEButtonVariant.Filled else AEButtonVariant.Ghost,
        size = AEButtonSize.Small
    ) {
        AEText(text = text)
    }
}

// --- Table Compatibility ---
@Composable
fun Table(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        content()
    }
}

@Composable
fun TableHeader(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        content()
    }
}

@Composable
fun TableHeaderCell(text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        AEText(text = text, style = AETheme.typography.labelSmall, color = AETheme.colors.textMuted)
    }
}

@Composable
fun TableRow(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Composable
fun TableCell(text: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        AEText(text = text, style = AETheme.typography.bodySmall)
    }
}

// --- IconButton Compatibility ---
@Composable
fun IconButton(
    icon: AEIconToken,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AEButton(
        onClick = onClick,
        variant = AEButtonVariant.Ghost,
        size = AEButtonSize.Small,
        modifier = modifier,
    ) {
        AEIcon(token = icon, contentDescription = contentDescription, size = 16.dp)
    }
}

// --- Pagination Compatibility ---
@Composable
fun Pagination(
    currentPage: Int,
    totalPages: Int,
    onPageChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AEButton(
            onClick = { if (currentPage > 1) onPageChange(currentPage - 1) },
            variant = AEButtonVariant.Ghost,
            size = AEButtonSize.Small,
            enabled = currentPage > 1
        ) {
            AEText("Prev")
        }
        for (page in 1..totalPages) {
            AEButton(
                onClick = { onPageChange(page) },
                variant = if (page == currentPage) AEButtonVariant.Filled else AEButtonVariant.Ghost,
                size = AEButtonSize.Small
            ) {
                AEText(page.toString())
            }
        }
        AEButton(
            onClick = { if (currentPage < totalPages) onPageChange(currentPage + 1) },
            variant = AEButtonVariant.Ghost,
            size = AEButtonSize.Small,
            enabled = currentPage < totalPages
        ) {
            AEText("Next")
        }
    }
}

// --- Button Compatibility ---
enum class ButtonVariant { Default, Secondary, Outline, Ghost, Destructive }
enum class ButtonSize { Sm, Md, Lg, Icon }

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Default,
    size: ButtonSize = ButtonSize.Md,
    enabled: Boolean = true,
    label: String = "",
) {
    val aeVariant = when (variant) {
        ButtonVariant.Default -> AEButtonVariant.Filled
        ButtonVariant.Secondary -> AEButtonVariant.Outlined
        ButtonVariant.Outline -> AEButtonVariant.Outlined
        ButtonVariant.Ghost -> AEButtonVariant.Ghost
        ButtonVariant.Destructive -> AEButtonVariant.Destructive
    }
    val aeSize = when (size) {
        ButtonSize.Sm -> AEButtonSize.Small
        ButtonSize.Md -> AEButtonSize.Medium
        ButtonSize.Lg -> AEButtonSize.Large
        ButtonSize.Icon -> AEButtonSize.Small
    }
    AEButton(
        onClick = onClick,
        modifier = modifier,
        variant = aeVariant,
        size = aeSize,
        enabled = enabled,
    ) {
        AEText(text = text)
    }
}

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Default,
    size: ButtonSize = ButtonSize.Md,
    enabled: Boolean = true,
    label: String = "",
    content: @Composable RowScope.() -> Unit,
) {
    val aeVariant = when (variant) {
        ButtonVariant.Default -> AEButtonVariant.Filled
        ButtonVariant.Secondary -> AEButtonVariant.Outlined
        ButtonVariant.Outline -> AEButtonVariant.Outlined
        ButtonVariant.Ghost -> AEButtonVariant.Ghost
        ButtonVariant.Destructive -> AEButtonVariant.Destructive
    }
    val aeSize = when (size) {
        ButtonSize.Sm -> AEButtonSize.Small
        ButtonSize.Md -> AEButtonSize.Medium
        ButtonSize.Lg -> AEButtonSize.Large
        ButtonSize.Icon -> AEButtonSize.Small
    }
    AEButton(
        onClick = onClick,
        modifier = modifier,
        variant = aeVariant,
        size = aeSize,
        enabled = enabled,
        content = content,
    )
}

// --- Badge Compatibility ---
enum class BadgeVariant { Default, Secondary, Success, Warning, Info, Outline, Destructive }
enum class BadgeSize { Sm, Md }

@Composable
fun Badge(
    text: String,
    modifier: Modifier = Modifier,
    variant: BadgeVariant = BadgeVariant.Default,
    size: BadgeSize = BadgeSize.Md,
) {
    val aeVariant = when (variant) {
        BadgeVariant.Default -> AEBadgeVariant.Default
        BadgeVariant.Secondary -> AEBadgeVariant.Outline
        BadgeVariant.Success -> AEBadgeVariant.Success
        BadgeVariant.Warning -> AEBadgeVariant.Warning
        BadgeVariant.Info -> AEBadgeVariant.Info
        BadgeVariant.Outline -> AEBadgeVariant.Outline
        BadgeVariant.Destructive -> AEBadgeVariant.Destructive
    }
    AEBadge(label = text, modifier = modifier, variant = aeVariant)
}

// --- Checkbox Compatibility ---
@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String = "",
    modifier: Modifier = Modifier,
) {
    AECheckbox(checked = checked, onCheckedChange = onCheckedChange, label = label, modifier = modifier)
}

// --- Switch Compatibility ---
@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    AESwitch(checked = checked, onCheckedChange = onCheckedChange, modifier = modifier)
}

// --- Slider Compatibility ---
@Composable
fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    AESlider(value = value, onValueChange = onValueChange, modifier = modifier)
}

// --- Progress Compatibility ---
@Composable
fun Progress(
    progress: Float,
    modifier: Modifier = Modifier,
    label: String = "",
) {
    AELinearProgress(progress = progress, modifier = modifier)
}

// --- Avatar Compatibility ---
enum class AvatarSize { Sm, Md, Lg }

@Composable
fun Avatar(
    fallback: String,
    size: AvatarSize = AvatarSize.Md,
    modifier: Modifier = Modifier,
) {
    val aeSize = when (size) {
        AvatarSize.Sm -> AEAvatarSize.Small
        AvatarSize.Md -> AEAvatarSize.Medium
        AvatarSize.Lg -> AEAvatarSize.Large
    }
    AEAvatar(initials = fallback, size = aeSize, modifier = modifier)
}

// --- Icon Compatibility ---
@Composable
fun Icon(
    imageVector: AEIconToken,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = AETheme.colors.textPrimary,
) {
    AEIcon(token = imageVector, contentDescription = contentDescription, modifier = modifier, tint = tint)
}

// --- Alert Compatibility ---
enum class AlertVariant { Default, Destructive }

@Composable
fun Alert(
    variant: AlertVariant = AlertVariant.Default,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (variant == AlertVariant.Destructive) AETheme.colors.destructive else AETheme.colors.border,
                shape = RoundedCornerShape(AETheme.radius.md)
            )
            .background(
                color = if (variant == AlertVariant.Destructive) AETheme.colors.destructive.copy(alpha = 0.1f) else AETheme.colors.surface,
                shape = RoundedCornerShape(AETheme.radius.md)
            )
            .padding(AETheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        content = content
    )
}

@Composable
fun AlertTitle(
    text: String,
    variant: AlertVariant = AlertVariant.Default,
    modifier: Modifier = Modifier,
) {
    AEText(
        text = text,
        style = AETheme.typography.headingSmall,
        color = if (variant == AlertVariant.Destructive) AETheme.colors.destructiveText else AETheme.colors.textPrimary,
        modifier = modifier
    )
}

@Composable
fun AlertDescription(
    text: String,
    variant: AlertVariant = AlertVariant.Default,
    modifier: Modifier = Modifier,
) {
    AEText(
        text = text,
        style = AETheme.typography.bodyMedium,
        color = if (variant == AlertVariant.Destructive) AETheme.colors.destructiveText else AETheme.colors.textMuted,
        modifier = modifier
    )
}

// --- Kbd Compatibility ---
@Composable
fun Kbd(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(1.dp, AETheme.colors.border, RoundedCornerShape(AETheme.radius.sm))
            .background(AETheme.colors.backgroundSecondary, RoundedCornerShape(AETheme.radius.sm))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        AEText(
            text = text,
            style = AETheme.typography.code,
            color = AETheme.colors.textPrimary
        )
    }
}
