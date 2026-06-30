package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.chip.AEChip
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun ChipDoc() {
    ComponentPageHeader(
        name = "Chip",
        description = "Compact interactive tag supporting leading/trailing icons and selection state.",
    )

    TabbedDocPage(
        overview = { ChipOverviewTab() },
        usage = { ChipUsageTab() },
        api = { ChipApiTab() },
    )
}

@Composable
private fun ChipOverviewTab() {
    DocSection("Chips Showcase") {
        var selected1 by remember { mutableStateOf(false) }
        var selected2 by remember { mutableStateOf(true) }

        DemoBox {
            Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
                AEChip(label = "Star", leadingIcon = AEIcons.Star, selected = selected1, onClick = { selected1 = !selected1 })
                AEChip(label = "Selected", selected = selected2, onClick = { selected2 = !selected2 })
                AEChip(label = "Deletable", trailingIcon = AEIcons.Close, onClick = {})
            }
        }
    }
}

@Composable
private fun ChipUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AEChip(
    label = "Tag",
    leadingIcon = AEIcons.Star,
    onClick = { }
)
            """.trimIndent()
        )
    }
}

@Composable
private fun ChipApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("label", "String", "required", "Text string label of the chip."),
                PropInfo("selected", "Boolean", "false", "Denotes toggle/active selection state."),
                PropInfo("onClick", "(() -> Unit)?", "null", "Optional callback when the chip is tapped."),
                PropInfo("leadingIcon", "AEIconToken?", "null", "Icon token displayed in front of label."),
                PropInfo("trailingIcon", "AEIconToken?", "null", "Icon token displayed after label."),
                PropInfo("enabled", "Boolean", "true", "Controls interactive states.")
            )
        )
    }
}
