package com.ae.designsystem.sample.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Generic playground screen.
 *
 * Renders any [PlaygroundItem] selected from the registry.
 * Adding a new component requires only registering it in [playgroundItems] —
 * this screen needs no modification (Open/Closed Principle).
 *
 * Layout:
 *  - Top: Component selector chips
 *  - Left panel: Controls
 *  - Right panel: Live preview
 */
@Composable
internal fun PlaygroundScreen(modifier: Modifier = Modifier) {
    val items = remember { playgroundItems() }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val selected = items[selectedIndex]

    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(spacing.xl),
        verticalArrangement = Arrangement.spacedBy(spacing.xl),
    ) {
        ComponentSelector(items = items, selectedIndex = selectedIndex, onSelect = { selectedIndex = it })
        PlaygroundContent(item = selected)
    }
}

@Composable
private fun ComponentSelector(
    items: List<PlaygroundItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
) {
    val spacing = AETheme.spacing

    Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
        items.forEachIndexed { index, item ->
            AEChip(
                label    = item.title,
                selected = index == selectedIndex,
                onClick  = { onSelect(index) },
            )
        }
    }
}

@Composable
private fun PlaygroundContent(item: PlaygroundItem) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.xl),
    ) {
        // Controls panel
        AECard(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(spacing.md),
            ) {
                AEText("Controls", style = AETheme.typography.headingSmall)
                AEDivider()
                item.Controls()
            }
        }

        // Preview panel
        AECard(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier.fillMaxWidth().height(spacing.xxxl * 4),
                contentAlignment = Alignment.Center,
            ) {
                item.Preview()
            }
        }
    }
}
