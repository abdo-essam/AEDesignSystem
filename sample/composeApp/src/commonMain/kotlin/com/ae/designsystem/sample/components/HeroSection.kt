package com.ae.designsystem.sample.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.AEButton
import com.ae.designsystem.components.AEButtonVariant
import com.ae.designsystem.components.AEBadge
import com.ae.designsystem.components.AEBadgeVariant
import com.ae.designsystem.components.AEText
import com.ae.designsystem.foundation.theme.AETheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroSection(
    onCustomizeThemeClick: () -> Unit,
    onViewComponentsClick: () -> Unit,
) {
    Spacer(Modifier.height(AETheme.spacing.xxxl))

    AEBadge(
        label = "v0.1.0",
        variant = AEBadgeVariant.Outline,
    )

    Spacer(Modifier.height(AETheme.spacing.md))

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        AEText(
            text = "AEDesignSystem",
            style = AETheme.typography.displayLarge,
        )
    }

    Spacer(Modifier.height(AETheme.spacing.sm))

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        AEText(
            text = "Composing elements into harmony",
            style = AETheme.typography.headingMedium,
        )
    }

    Spacer(Modifier.height(AETheme.spacing.sm))

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        AEText(
            text = "A shadcn/ui-inspired component library and design system for Compose Multiplatform. Copy-paste components you own, zero Material dependency.",
            style = AETheme.typography.bodyMedium,
            color = AETheme.colors.textMuted,
        )
    }

    Spacer(Modifier.height(AETheme.spacing.xl))

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(
            AETheme.spacing.sm,
            Alignment.CenterHorizontally,
        ),
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
        modifier = Modifier.fillMaxWidth(),
    ) {
        AEButton(onClick = onCustomizeThemeClick) {
            AEText("Customize Theme", color = AETheme.colors.textOnAccent)
        }

        AEButton(
            onClick = onViewComponentsClick,
            variant = AEButtonVariant.Outlined,
        ) {
            AEText("View Components")
        }
    }

    Spacer(Modifier.height(AETheme.spacing.xxxl))
}
