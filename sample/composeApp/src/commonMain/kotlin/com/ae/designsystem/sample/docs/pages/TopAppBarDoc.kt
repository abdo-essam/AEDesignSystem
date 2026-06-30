package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.topappbar.AETopAppBar
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun TopAppBarDoc() {
    ComponentPageHeader(
        name = "TopAppBar",
        description = "Header app bar supporting title text and navigation/action slots.",
    )

    ComponentFamily(
        related = ComponentFamilies.NAVIGATION,
        currentId = "topappbar",
    )

    TabbedDocPage(
        overview = { TopAppBarOverviewTab() },
        usage = { TopAppBarUsageTab() },
        api = { TopAppBarApiTab() },
    )
}

@Composable
private fun TopAppBarOverviewTab() {
    DocSection("Top Header Showcase") {
        DemoBox {
            AETopAppBar(
                title = "Dashboard View",
                navigationIcon = AEIcons.Menu,
                onNavigationClick = {},
                actions = {
                    AEButton(onClick = {}, variant = AEButtonVariant.Ghost) {
                        AEIcon(AEIcons.Search)
                    }
                }
            )
        }
    }
}

@Composable
private fun TopAppBarUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AETopAppBar(
    title = "Application Title",
    navigationIcon = AEIcons.Menu,
    onNavigationClick = { },
    actions = {
        AEButton(onClick = {}, variant = AEButtonVariant.Ghost) {
            AEIcon(AEIcons.Search)
        }
    }
)
            """.trimIndent()
        )
    }
}

@Composable
private fun TopAppBarApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("title", "String", "required", "Title text string centered/placed in header."),
                PropInfo("navigationIcon", "AEIconToken?", "null", "Left navigation icon token (e.g. Menu)."),
                PropInfo("onNavigationClick", "(() -> Unit)?", "null", "Callback on clicking navigation icon."),
                PropInfo("actions", "@Composable RowScope.() -> Unit", "null", "Slot for trailing action buttons."),
                PropInfo("elevated", "Boolean", "false", "Whether to draw a shadow beneath the bar.")
            )
        )
    }
}
