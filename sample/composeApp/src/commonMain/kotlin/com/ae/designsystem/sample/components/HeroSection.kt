package com.ae.designsystem.sample.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroSection(
    onCustomizeThemeClick: () -> Unit,
    onViewComponentsClick: () -> Unit,
) {
    Spacer(Modifier.height(72.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // ── Version badge ───────────────────────────────────────────────────
        AEBadge(
            label = "v0.3.0",
            variant = AEBadgeVariant.Outline,
        )

        Spacer(Modifier.height(16.dp))

        // ── Main title — bold black, large, centered ────────────────────────
        AEText(
            text = "AEDesignSystem",
            style = AETheme.typography.displayLarge.copy(
                fontWeight = FontWeight.Black,
                fontSize = 56.sp,
                letterSpacing = (-1).sp,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(12.dp))

        // ── Tagline ─────────────────────────────────────────────────────────
        AEText(
            text = "Composing elements into harmony",
            style = AETheme.typography.headingSmall.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            ),
            color = AETheme.colors.textSecondary,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(12.dp))

        // ── Description — constrained width, centered ───────────────────────
        Box(
            modifier = Modifier.widthIn(max = 640.dp).fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            AEText(
                text = "A shadcn/ui-inspired component library and design system for Compose Multiplatform." +
                    " Copy-paste components you own, zero Material dependency.",
                style = AETheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = AETheme.colors.textMuted,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(Modifier.height(32.dp))

        // ── CTA buttons ─────────────────────────────────────────────────────
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(12.dp),
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
    }

    Spacer(Modifier.height(72.dp))
}
