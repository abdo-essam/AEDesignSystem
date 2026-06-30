package com.ae.designsystem.sample.docs.components.cards

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun ButtonsCard(modifier: Modifier = Modifier) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    AECard(modifier = modifier, header = { AEText("Buttons", style = AETheme.typography.headingSmall) }) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xl)) {
            VariantsRow()
            SizesRow()
            IconButtonsRow()
            DisabledRow()
        }
    }
}

@Composable
private fun VariantsRow() {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        AEText("Variants", style = AETheme.typography.labelMedium, color = colors.textMuted)
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.md),
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            AEButton(onClick = {}) { AEText("Filled",       color = colors.textOnAccent) }
            AEButton(onClick = {}, variant = AEButtonVariant.Outlined)    { AEText("Outlined",     color = colors.accent) }
            AEButton(onClick = {}, variant = AEButtonVariant.Ghost)       { AEText("Ghost",        color = colors.textPrimary) }
            AEButton(onClick = {}, variant = AEButtonVariant.Destructive) { AEText("Destructive",  color = colors.destructiveText) }
            AEButton(onClick = {}, variant = AEButtonVariant.Link)        { AEText("Link",         color = colors.accent) }
        }
    }
}

@Composable
private fun SizesRow() {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        AEText("Sizes", style = AETheme.typography.labelMedium, color = colors.textMuted)
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.md),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            AEButton(onClick = {}, size = AEButtonSize.Small)  { AEText("Small",  color = colors.textOnAccent) }
            AEButton(onClick = {}, size = AEButtonSize.Medium) { AEText("Medium", color = colors.textOnAccent) }
            AEButton(onClick = {}, size = AEButtonSize.Large)  { AEText("Large",  color = colors.textOnAccent) }
        }
    }
}

@Composable
private fun IconButtonsRow() {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        AEText("With Icons", style = AETheme.typography.labelMedium, color = colors.textMuted)
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.md),
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            AEButton(onClick = {}) {
                AEIcon(AEIcons.Heart, tint = colors.textOnAccent, size = 16.dp)
                Spacer(Modifier.width(spacing.xs))
                AEText("Like", color = colors.textOnAccent)
            }
            AEButton(onClick = {}, variant = AEButtonVariant.Outlined) {
                AEIcon(AEIcons.Download, tint = colors.accent, size = 16.dp)
                Spacer(Modifier.width(spacing.xs))
                AEText("Download", color = colors.accent)
            }
        }
    }
}

@Composable
private fun DisabledRow() {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        AEText("Disabled", style = AETheme.typography.labelMedium, color = colors.textMuted)
        Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEButton(onClick = {}, enabled = false)                              { AEText("Disabled", color = colors.textMuted) }
            AEButton(onClick = {}, enabled = false, variant = AEButtonVariant.Outlined) { AEText("Disabled", color = colors.textMuted) }
        }
    }
}
