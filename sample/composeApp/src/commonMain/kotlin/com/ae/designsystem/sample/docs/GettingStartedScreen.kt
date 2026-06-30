package com.ae.designsystem.sample.docs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.components.ui.textfield.AETextField
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Getting Started screen — quick install guide and usage snippets.
 * Stateless and side-effect free.
 */
@Composable
internal fun GettingStartedScreen(modifier: Modifier = Modifier) {
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
        HeroSection()
        InstallSection()
        WrapSection()
        UseSection()
        Spacer(Modifier.height(spacing.xxxl))
    }
}

@Composable
private fun HeroSection() {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
        AEText("Getting Started", style = AETheme.typography.displayMedium, color = colors.accent)
        AEText(
            text  = "AEDesignSystem is a zero-Material3, token-driven component library for Compose Multiplatform. " +
                    "Copy the dependency, wrap your app, and start building.",
            style = AETheme.typography.bodyLarge,
            color = colors.textSecondary,
        )
    }
}

@Composable
private fun InstallSection() {
    val spacing = AETheme.spacing

    GuideSectionCard(title = "1. Add Dependency") {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            AEText(
                text  = "Add both modules to your commonMain dependencies:",
                style = AETheme.typography.bodyMedium,
                color = AETheme.colors.textSecondary,
            )
            CodeBlock(
                """
commonMain.dependencies {
    implementation("io.github.abdo-essam:ae-foundation:0.1.0")
    implementation("io.github.abdo-essam:ae-components:0.1.0")
}
                """.trimIndent()
            )
        }
    }
}

@Composable
private fun WrapSection() {
    val spacing = AETheme.spacing

    GuideSectionCard(title = "2. Wrap Your App") {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            AEText(
                text  = "Wrap your root composable with AETheme to provide design tokens:",
                style = AETheme.typography.bodyMedium,
                color = AETheme.colors.textSecondary,
            )
            CodeBlock(
                """
AETheme(
    palette   = AEPalette.Zinc,
    accent    = AEAccent.Blue,
    preset    = AEStylePreset.Default,
    darkTheme = isSystemInDarkTheme(),
) {
    // Your app content
}
                """.trimIndent()
            )
        }
    }
}

@Composable
private fun UseSection() {
    val spacing = AETheme.spacing

    GuideSectionCard(title = "3. Use Components") {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            AEText(
                text  = "All components read from AETheme automatically — no manual token passing needed:",
                style = AETheme.typography.bodyMedium,
                color = AETheme.colors.textSecondary,
            )
            CodeBlock(
                """
AEButton(onClick = { }) {
    AEText("Click me", color = AETheme.colors.textOnAccent)
}

AETextField(
    value         = text,
    onValueChange = { text = it },
    label         = "Email",
)
                """.trimIndent()
            )
        }
    }
}

@Composable
private fun GuideSectionCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    val spacing = AETheme.spacing

    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText(title, style = AETheme.typography.headingSmall)
            AEDivider()
            content()
        }
    }
}

@Composable
private fun CodeBlock(code: String) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing
    val radius  = AETheme.radius

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(radius.md))
            .background(colors.surface)
            .padding(spacing.md),
    ) {
        AEText(text = code, style = AETheme.typography.code, color = colors.textPrimary)
    }
}
