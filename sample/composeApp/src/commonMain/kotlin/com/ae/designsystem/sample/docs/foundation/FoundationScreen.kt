package com.ae.designsystem.sample.docs.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.tabs.AETabs
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.docs.foundation.sections.*

private enum class FoundationTab(val label: String) {
    Typography(label = "Typography"),
    Colors(label = "Colors"),
    Spacing(label = "Spacing"),
    Shapes(label = "Shapes"),
    Elevation(label = "Elevation"),
}

/**
 * Foundation screen — routes to token documentation sections via [AETabs].
 * Each section is an isolated composable — zero shared state between them.
 */
@Composable
internal fun FoundationScreen(modifier: Modifier = Modifier) {
    var selected by remember { mutableStateOf(FoundationTab.Typography) }
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background),
    ) {
        AETabs(
            tabs          = FoundationTab.entries.map { it.label },
            selectedIndex = FoundationTab.entries.indexOf(selected),
            onTabSelected = { selected = FoundationTab.entries[it] },
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(spacing.xl),
        ) {
            when (selected) {
                FoundationTab.Typography -> TypographySection()
                FoundationTab.Colors     -> ColorTokensSection()
                FoundationTab.Spacing    -> SpacingSection()
                FoundationTab.Shapes     -> ShapeSection()
                FoundationTab.Elevation  -> ElevationSection()
            }
        }
    }
}
