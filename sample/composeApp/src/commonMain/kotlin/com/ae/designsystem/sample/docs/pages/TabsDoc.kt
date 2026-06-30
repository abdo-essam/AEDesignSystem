package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.tabs.AETabs
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun TabsDoc() {
    ComponentPageHeader(
        name = "Tabs",
        description = "Horizontal tab navigation with animated indicator pill.",
    )

    ComponentFamily(
        related = ComponentFamilies.NAVIGATION,
        currentId = "tabs",
    )

    TabbedDocPage(
        overview = { TabsOverviewTab() },
        usage = { TabsUsageTab() },
        api = { TabsApiTab() },
    )
}

@Composable
private fun TabsOverviewTab() {
    DocSection("Live Preview") {
        var selectedTab by remember { mutableStateOf(0) }

        DemoBox {
            Column(modifier = Modifier.fillMaxWidth()) {
                AETabs(
                    tabs = listOf("Overview", "Usage Guidelines", "API Reference"),
                    selectedIndex = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
                Spacer(Modifier.height(AETheme.spacing.md))
                AEText("Currently selected tab index: $selectedTab")
            }
        }
    }
}

@Composable
private fun TabsUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
var activeTab by remember { mutableStateOf(0) }

AETabs(
    tabs = listOf("Tab 1", "Tab 2", "Tab 3"),
    selectedIndex = activeTab,
    onTabSelected = { activeTab = it }
)
            """.trimIndent()
        )
    }
}

@Composable
private fun TabsApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("tabs", "List<String>", "required", "List of string labels for each tab destination."),
                PropInfo("selectedIndex", "Int", "required", "Active tab index."),
                PropInfo("onTabSelected", "(Int) -> Unit", "required", "Triggered when a tab index is clicked.")
            )
        )
    }
}
