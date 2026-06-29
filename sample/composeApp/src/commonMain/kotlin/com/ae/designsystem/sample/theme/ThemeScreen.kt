package com.ae.designsystem.sample.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.color.AEAccent
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.foundation.tokens.AEStylePreset
import com.ae.designsystem.sample.ThemeConfiguration

/**
 * Theme configurator screen.
 *
 * Delegates upward via [onThemeChange] — owns no state itself.
 * Triggers [ExportDialog] locally for a clean separation.
 *
 * @param config        Current theme configuration (read from parent).
 * @param onThemeChange Called when any knob changes.
 */
@Composable
internal fun ThemeScreen(
    config: ThemeConfiguration,
    onThemeChange: (ThemeConfiguration) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showExport by remember { mutableStateOf(false) }
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    if (showExport) {
        ExportDialog(config = config, onDismiss = { showExport = false })
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(spacing.xl),
        verticalArrangement = Arrangement.spacedBy(spacing.xxl),
    ) {
        ThemeHeader(onExport = { showExport = true })
        PaletteSection(config, onThemeChange)
        AccentSection(config, onThemeChange)
        PresetSection(config, onThemeChange)
        DarkModeSection(config, onThemeChange)
    }
}

@Composable
private fun ThemeHeader(onExport: () -> Unit) {
    val colors = AETheme.colors

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            AEText("Theme", style = AETheme.typography.displayMedium, color = colors.accent)
            AEText("Customize and export your theme", style = AETheme.typography.bodyMedium, color = colors.textMuted)
        }
        AEButton(onClick = onExport, variant = AEButtonVariant.Outlined) {
            AEText("Export", color = colors.accent)
        }
    }
}

@Composable
private fun PaletteSection(config: ThemeConfiguration, onThemeChange: (ThemeConfiguration) -> Unit) {
    val spacing = AETheme.spacing

    SectionCard(title = "Palette") {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            AEPalette.entries.forEach { p ->
                AEChip(
                    label    = p.name,
                    selected = p == config.palette,
                    onClick  = { onThemeChange(config.copy(palette = p)) },
                )
            }
        }
    }
}

@Composable
private fun AccentSection(config: ThemeConfiguration, onThemeChange: (ThemeConfiguration) -> Unit) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    SectionCard(title = "Accent Color") {
        Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
            AEAccent.entries.forEach { a ->
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(a.primary)
                        .then(
                            if (a == config.accent)
                                Modifier.border(2.dp, colors.textPrimary, CircleShape)
                            else Modifier,
                        )
                        .clickable { onThemeChange(config.copy(accent = a)) },
                )
            }
        }
    }
}

@Composable
private fun PresetSection(config: ThemeConfiguration, onThemeChange: (ThemeConfiguration) -> Unit) {
    val spacing = AETheme.spacing

    SectionCard(title = "Style Preset") {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            AEStylePreset.entries.forEach { s ->
                AEChip(
                    label    = s.name,
                    selected = s == config.preset,
                    onClick  = { onThemeChange(config.copy(preset = s)) },
                )
            }
        }
    }
}

@Composable
private fun DarkModeSection(config: ThemeConfiguration, onThemeChange: (ThemeConfiguration) -> Unit) {
    SectionCard(title = "Appearance") {
        AESwitch(
            checked       = config.darkMode,
            onCheckedChange = { onThemeChange(config.copy(darkMode = it)) },
            label         = "Dark Mode",
        )
    }
}

@Composable
private fun SectionCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    val spacing = AETheme.spacing

    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText(title, style = AETheme.typography.headingSmall)
            AEDivider()
            content()
        }
    }
}
