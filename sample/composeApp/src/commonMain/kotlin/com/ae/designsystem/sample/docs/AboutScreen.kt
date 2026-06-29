package com.ae.designsystem.sample.docs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.theme.AETheme

/**
 * About screen — describes the project philosophy, design goals,
 * and target audience. Stateless and purely informational.
 */
@Composable
internal fun AboutScreen(modifier: Modifier = Modifier) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(spacing.xl),
        verticalArrangement = Arrangement.spacedBy(spacing.xxl),
    ) {
        AboutHero()
        PhilosophyCard()
        PillarsRow()
        PlatformCard()
        Spacer(Modifier.height(spacing.xxxl))
    }
}

@Composable
private fun AboutHero() {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
        AEText("AEDesignSystem", style = AETheme.typography.displayMedium, color = colors.accent)
        AEText("v0.1.0", style = AETheme.typography.labelMedium, color = colors.textMuted)
        AEText(
            text  = "A zero-Material3, token-driven design system for Compose Multiplatform. " +
                    "Built for developers who want full ownership of their UI without fighting framework opinions.",
            style = AETheme.typography.bodyLarge,
            color = colors.textSecondary,
        )
    }
}

@Composable
private fun PhilosophyCard() {
    val spacing = AETheme.spacing

    AECard(header = { AEText("Philosophy", style = AETheme.typography.headingSmall) }) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEDivider()
            listOf(
                "Zero Material3"    to "Built entirely on compose.foundation. No Material opinions, no forced design patterns.",
                "Token-driven"      to "Every color, spacing, radius, motion, and elevation value is a named, typed token.",
                "Slot-based API"    to "Composable trailing lambdas give you maximum control over inner content.",
                "Swappable Icons"   to "AEIconToken decouples semantic icons from their vector implementations.",
                "Accessible"        to "Semantic roles, focus rings, content descriptions, and WCAG touch targets built in.",
            ).forEach { (title, body) ->
                PhilosophyItem(title = title, body = body)
            }
        }
    }
}

@Composable
private fun PhilosophyItem(title: String, body: String) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
        AEBadge(title, modifier = Modifier.padding(top = spacing.xxs))
        AEText(body, style = AETheme.typography.bodyMedium, color = colors.textSecondary)
    }
}

@Composable
private fun PillarsRow() {
    val spacing = AETheme.spacing

    AECard(header = { AEText("For Developers Who Want", style = AETheme.typography.headingSmall) }) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            AEDivider()
            listOf(
                "Beautiful defaults without Material3 constraints",
                "Full ownership — no dependency lock-in",
                "Consistent cross-platform UI (Android, iOS, Desktop, Web)",
                "Composable components that scale with the product",
                "A design system that is easy to audit and extend",
            ).forEach { point ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                    verticalAlignment     = Alignment.Top,
                ) {
                    AEText("·", color = AETheme.colors.accent, style = AETheme.typography.bodyLarge)
                    AEText(point, style = AETheme.typography.bodyMedium, color = AETheme.colors.textSecondary)
                }
            }
        }
    }
}

@Composable
private fun PlatformCard() {
    val spacing = AETheme.spacing

    AECard(header = { AEText("Platform Support", style = AETheme.typography.headingSmall) }) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            AEDivider()
            listOf("Android ✅", "iOS ✅", "Desktop (JVM) ✅", "Web (WasmJs) ✅", "Web (JS) ✅").forEach { platform ->
                AEText(platform, style = AETheme.typography.bodyMedium, color = AETheme.colors.textSecondary)
            }
        }
    }
}
