package com.ae.designsystem.sample.docs.foundation.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun ColorTokensSection(modifier: Modifier = Modifier) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing
    val radius  = AETheme.radius

    AECard(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            AEText("Color Tokens", style = AETheme.typography.headingSmall)
            AEDivider()
            listOf(
                "background"          to colors.background,
                "backgroundSecondary" to colors.backgroundSecondary,
                "surface"             to colors.surface,
                "surfaceHover"        to colors.surfaceHover,
                "accent"              to colors.accent,
                "accentHover"         to colors.accentHover,
                "accentMuted"         to colors.accentMuted,
                "textPrimary"         to colors.textPrimary,
                "textSecondary"       to colors.textSecondary,
                "textMuted"           to colors.textMuted,
                "destructive"         to colors.destructive,
                "success"             to colors.success,
                "warning"             to colors.warning,
                "info"                to colors.info,
                "border"              to colors.border,
                "ring"                to colors.ring,
            ).forEach { (name, color) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.md),
                ) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .clip(RoundedCornerShape(radius.sm))
                            .background(color)
                            .border(1.dp, colors.border, RoundedCornerShape(radius.sm)),
                    )
                    AEText(name, style = AETheme.typography.code, color = colors.textSecondary)
                }
            }
        }
    }
}
