package com.ae.designsystem.sample.docs.foundation.sections

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.surface.AESurface
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun ElevationSection(modifier: Modifier = Modifier) {
    val spacing   = AETheme.spacing
    val elevation = AETheme.elevation
    val colors    = AETheme.colors

    AECard(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText("Elevation", style = AETheme.typography.headingSmall)
            AEDivider()
            listOf(
                "none" to elevation.none,
                "sm"   to elevation.sm,
                "md"   to elevation.md,
                "lg"   to elevation.lg,
            ).forEach { (name, elev) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.md),
                ) {
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
    }
}
