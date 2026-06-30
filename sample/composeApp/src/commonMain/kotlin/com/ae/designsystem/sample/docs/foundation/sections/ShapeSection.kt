package com.ae.designsystem.sample.docs.foundation.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun ShapeSection(modifier: Modifier = Modifier) {
    val radius  = AETheme.radius
    val spacing = AETheme.spacing
    val colors  = AETheme.colors

    AECard(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText("Shapes", style = AETheme.typography.headingSmall)
            AEDivider()
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.xl),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                listOf(
                    "none" to radius.none,
                    "sm"   to radius.sm,
                    "md"   to radius.md,
                    "lg"   to radius.lg,
                    "xl"   to radius.xl,
                    "full" to radius.full,
                ).forEach { (name, r) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(spacing.xs),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(colors.accentMuted, RoundedCornerShape(r))
                                .border(1.5.dp, colors.accent, RoundedCornerShape(r)),
                        )
                        AEText(name, style = AETheme.typography.labelSmall, color = colors.textMuted)
                    }
                }
            }
        }
    }
}
