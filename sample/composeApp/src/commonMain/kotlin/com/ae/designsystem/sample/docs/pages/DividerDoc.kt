package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.divider.AEVerticalDivider
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun DividerDoc() {
    ComponentPageHeader(
        name = "Divider",
        description = "Thin separator lines to divide content sections.",
    )

    TabbedDocPage(
        overview = { DividerOverviewTab() },
        usage = { DividerUsageTab() },
        api = { DividerApiTab() },
    )
}

@Composable
private fun DividerOverviewTab() {
    DocSection("Horizontal Divider") {
        DemoBox {
            Column(modifier = Modifier.fillMaxWidth()) {
                AEText("First section")
                AEDivider(modifier = Modifier.padding(vertical = AETheme.spacing.md))
                AEText("Second section")
            }
        }
    }

    DocSection("Vertical Divider") {
        DemoBox {
            Row(modifier = Modifier.height(24.dp)) {
                AEText("Part A")
                AEVerticalDivider(modifier = Modifier.padding(horizontal = AETheme.spacing.md))
                AEText("Part B")
            }
        }
    }
}

@Composable
private fun DividerUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AEDivider()
AEVerticalDivider()
            """.trimIndent()
        )
    }
}

@Composable
private fun DividerApiTab() {
    DocSection("API Reference (AEDivider / AEVerticalDivider)") {
        PropsTable(
            listOf(
                PropInfo("color", "Color", "AETheme.colors.border", "Divider stroke line color."),
                PropInfo("thickness", "Dp", "1.dp", "Thickness/width of the line.")
            )
        )
    }
}
