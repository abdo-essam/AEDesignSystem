package com.ae.designsystem.sample.docs.foundation.sections

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun TypographySection(modifier: Modifier = Modifier) {
    val spacing = AETheme.spacing
    val typo    = AETheme.typography
    val colors  = AETheme.colors

    AECard(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText("Typography", style = typo.headingSmall)
            AEDivider()
            listOf(
                "displayLarge"   to typo.displayLarge,
                "displayMedium"  to typo.displayMedium,
                "headingLarge"   to typo.headingLarge,
                "headingMedium"  to typo.headingMedium,
                "headingSmall"   to typo.headingSmall,
                "bodyLarge"      to typo.bodyLarge,
                "bodyMedium"     to typo.bodyMedium,
                "bodySmall"      to typo.bodySmall,
                "labelLarge"     to typo.labelLarge,
                "labelMedium"    to typo.labelMedium,
                "labelSmall"     to typo.labelSmall,
                "code"           to typo.code,
            ).forEach { (name, style) ->
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(spacing.lg),
                ) {
                    AEText(name, style = typo.code, color = colors.textMuted, modifier = Modifier.width(spacing.xxxl * 3))
                    AEText("AEDesignSystem", style = style)
                }
            }
        }
    }
}
