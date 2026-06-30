package com.ae.designsystem.sample.docs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.docs.catalog.ComponentRegistry

@Composable
internal fun ComponentsScreen(modifier: Modifier = Modifier) {
    var selectedId by remember { mutableStateOf("button") }

    CompositionLocalProvider(LocalDocNavigation provides { selectedId = it }) {
        BoxWithConstraints(
            modifier = modifier
                .fillMaxSize()
                .background(AETheme.colors.background)
        ) {
            val isWide = maxWidth >= 800.dp

            if (isWide) {
                WideLayout(
                    selectedId = selectedId,
                    onSelect = { selectedId = it }
                )
            } else {
                CompactLayout(
                    selectedId = selectedId,
                    onSelect = { selectedId = it }
                )
            }
        }
    }
}

@Composable
private fun WideLayout(
    selectedId: String,
    onSelect: (String) -> Unit,
) {
    val sidebarScroll = rememberScrollState()
    val contentScroll = rememberScrollState()

    Row(Modifier.fillMaxSize()) {
        DocsSidebar(
            grouped = ComponentRegistry.groupedByCategory(),
            selectedId = selectedId,
            onSelect = onSelect,
            modifier = Modifier
                .width(240.dp)
                .fillMaxHeight()
                .verticalScroll(sidebarScroll)
                .padding(
                    start = AETheme.spacing.lg,
                    top = AETheme.spacing.lg,
                    bottom = AETheme.spacing.lg,
                    end = AETheme.spacing.md,
                ),
        )

        Box(
            Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(AETheme.colors.border),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .verticalScroll(contentScroll)
                .padding(AETheme.spacing.xl),
        ) {
            PageContent(
                selectedId = selectedId,
                registry = ComponentRegistry,
            )
        }
    }
}

@Composable
private fun CompactLayout(
    selectedId: String,
    onSelect: (String) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(AETheme.spacing.md),
    ) {
        CompactSelector(
            entries = ComponentRegistry.entries,
            selectedId = selectedId,
            onSelect = onSelect,
        )

        Spacer(Modifier.height(AETheme.spacing.lg))

        PageContent(
            selectedId = selectedId,
            registry = ComponentRegistry,
        )
    }
}
