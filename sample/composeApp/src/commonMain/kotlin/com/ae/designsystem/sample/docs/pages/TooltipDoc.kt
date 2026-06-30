package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.tooltip.AETooltip
import com.ae.designsystem.components.ui.tooltip.AETooltipPosition
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun TooltipDoc() {
    ComponentPageHeader(
        name = "Tooltip",
        description = "Contextual tooltip label shown when hovering over components.",
    )

    ComponentFamily(
        related = ComponentFamilies.POPUPS,
        currentId = "tooltip",
    )

    TabbedDocPage(
        overview = { TooltipOverviewTab() },
        usage = { TooltipUsageTab() },
        api = { TooltipApiTab() },
    )
}

@Composable
private fun TooltipOverviewTab() {
    DocSection("Interactive Hover") {
        DemoBox {
            AETooltip(tooltip = "Confirm details and submit file info", position = AETooltipPosition.Top) {
                AEButton(onClick = {}) {
                    AEText("Hover Me")
                }
            }
        }
    }
}

@Composable
private fun TooltipUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AETooltip(
    tooltip = "Hover Details",
    position = AETooltipPosition.Bottom
) {
    AEButton(onClick = {}) { AEText("Action") }
}
            """.trimIndent()
        )
    }
}

@Composable
private fun TooltipApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("tooltip", "String", "required", "Descriptive tooltip string label."),
                PropInfo("position", "AETooltipPosition", "Top", "Where to position relative to anchor (Top, Bottom, Start, End)."),
                PropInfo("content", "@Composable () -> Unit", "required", "Anchor target composable slot.")
            )
        )
    }
}
