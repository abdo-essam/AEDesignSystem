package com.ae.designsystem.sample.docs.foundation.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun SpacingSection(modifier: Modifier = Modifier) {
    val spacing = AETheme.spacing
    val colors  = AETheme.colors

    AECard(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText("Spacing", style = AETheme.typography.headingSmall)
            AEDivider()
            listOf(
                "xxs"  to spacing.xxs,
                "xs"   to spacing.xs,
                "sm"   to spacing.sm,
                "md"   to spacing.md,
                "lg"   to spacing.lg,
                "xl"   to spacing.xl,
                "xxl"  to spacing.xxl,
                "xxxl" to spacing.xxxl,
            ).forEach { (name, value) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.md),
                ) {
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .width(value)
                            .clip(RoundedCornerShape(2.dp))
                            .background(colors.accent),
                    )
                    AEText(
                        text  = "$name — ${value.value.toInt()}dp",
                        style = AETheme.typography.code,
                        color = colors.textSecondary,
                    )
                }
            }
        }
    }
}
