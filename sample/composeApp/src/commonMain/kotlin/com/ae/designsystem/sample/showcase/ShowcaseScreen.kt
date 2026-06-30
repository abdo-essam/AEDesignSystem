package com.ae.designsystem.sample.showcase

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.components.ExamplesGrid
import com.ae.designsystem.sample.components.FooterSection
import com.ae.designsystem.sample.components.HeroSection
import com.ae.designsystem.sample.components.ThemeToolbar
import com.ae.designsystem.sample.utils.WindowSizeClass

@Composable
fun ShowcaseScreen(
    state: ShowcaseState,
    isDark: Boolean,
    onAction: (ShowcaseAction) -> Unit,
    onNavigateToComponents: () -> Unit,
) {
    val scrollState = rememberScrollState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter,
    ) {
        val sizeClass = WindowSizeClass.fromWidth(maxWidth)
        val horizontalPadding =
            when (sizeClass) {
                WindowSizeClass.Compact -> AETheme.spacing.md
                WindowSizeClass.Medium -> AETheme.spacing.lg
                WindowSizeClass.Expanded -> AETheme.spacing.lg
            }

        Column(
            modifier = Modifier
                .widthIn(max = 1200.dp)
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeroSection(
                onCustomizeThemeClick = { },
                onViewComponentsClick = onNavigateToComponents,
            )

            Spacer(Modifier.height(AETheme.spacing.xl))

            AEText(
                text = "Components in Action",
                style = AETheme.typography.displayMedium,
            )

            Spacer(Modifier.height(AETheme.spacing.xs))

            AEText(
                text = "Explore how AEDesignSystem components work together.",
                style = AETheme.typography.bodyLarge,
                color = AETheme.colors.textMuted,
            )

            Spacer(Modifier.height(AETheme.spacing.lg))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                ThemeToolbar(
                    palette = state.palette,
                    onPaletteChange = { onAction(ShowcaseAction.ChangePalette(it)) },
                    accent = state.accent,
                    onAccentChange = { onAction(ShowcaseAction.ChangeAccent(it)) },
                    stylePreset = state.stylePreset,
                    onStylePresetChange = { onAction(ShowcaseAction.ChangeStylePreset(it)) },
                )
            }

            Spacer(Modifier.height(AETheme.spacing.xxl))

            AETheme(
                palette = state.palette,
                accent = state.accent,
                preset = state.stylePreset,
                darkTheme = isDark,
            ) {
                ExamplesGrid()
            }

            Spacer(Modifier.height(AETheme.spacing.xxxl))

            FooterSection()
        }
    }
}
