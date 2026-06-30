package com.ae.designsystem.sample.docs.components.cards

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.surface.AESurface
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun SurfacesCard(modifier: Modifier = Modifier) {
    val spacing = AETheme.spacing

    AECard(modifier = modifier, header = { AEText("Cards & Surfaces", style = AETheme.typography.headingSmall) }) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xl)) {
            CardExamples()
            AEDivider()
            ElevationExamples()
        }
    }
}

@Composable
private fun CardExamples() {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        AEText("Card Variants", style = AETheme.typography.headingSmall)
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.lg),
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            AECard(modifier = Modifier.width(220.dp), header = { AEText("Basic", style = AETheme.typography.headingSmall) }) {
                AEText("Header + content slot.", color = colors.textSecondary)
            }
            AECard(
                modifier = Modifier.width(220.dp),
                header = {
                    Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                        AEIcon(AEIcons.Star, tint = colors.accent, size = 18.dp)
                        AEText("With Actions", style = AETheme.typography.headingSmall)
                    }
                },
                footer = {
                    AEButton(onClick = {}, size = AEButtonSize.Small) {
                        AEText("Confirm", color = colors.textOnAccent)
                    }
                },
            ) {
                AEText("Header + footer action.", color = colors.textSecondary)
            }
        }
    }
}

@Composable
private fun ElevationExamples() {
    val colors    = AETheme.colors
    val spacing   = AETheme.spacing
    val elevation = AETheme.elevation

    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        AEText("Elevation Levels", style = AETheme.typography.headingSmall)
        listOf("none" to elevation.none, "sm" to elevation.sm, "md" to elevation.md, "lg" to elevation.lg).forEach { (name, elev) ->
            AESurface(elevation = elev, modifier = Modifier.fillMaxWidth()) {
                AEText(
                    text     = "elevation.$name",
                    modifier = Modifier.padding(spacing.sm),
                    style    = AETheme.typography.code,
                    color    = colors.textMuted,
                )
            }
        }
    }
}
