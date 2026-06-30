package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun ButtonDoc() {
    ComponentPageHeader(
        name = "Button",
        description = "Core button component — slot-based, token-driven, zero Material3.",
    )

    ComponentFamily(
        related = ComponentFamilies.BUTTONS,
        currentId = "button",
    )

    TabbedDocPage(
        overview = { ButtonOverviewTab() },
        usage = { ButtonUsageTab() },
        api = { ButtonApiTab() },
    )
}

@Composable
private fun ButtonOverviewTab() {
    DocSection("Variants") {
        DemoBox {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)
            ) {
                AEButton(onClick = {}, variant = AEButtonVariant.Filled) { AEText("Filled") }
                AEButton(onClick = {}, variant = AEButtonVariant.Outlined) { AEText("Outlined") }
                AEButton(onClick = {}, variant = AEButtonVariant.Ghost) { AEText("Ghost") }
                AEButton(onClick = {}, variant = AEButtonVariant.Destructive) { AEText("Destructive") }
                AEButton(onClick = {}, variant = AEButtonVariant.Link) { AEText("Link") }
            }
        }
    }

    DocSection("Sizes") {
        DemoBox {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md)
            ) {
                AEButton(onClick = {}, size = AEButtonSize.Small) { AEText("Small") }
                AEButton(onClick = {}, size = AEButtonSize.Medium) { AEText("Medium") }
                AEButton(onClick = {}, size = AEButtonSize.Large) { AEText("Large") }
            }
        }
    }
}

@Composable
private fun ButtonUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AEButton(onClick = { }) {
    AEText("Submit")
}
AEButton(onClick = { }, variant = AEButtonVariant.Outlined) {
    AEText("Cancel")
}
            """.trimIndent()
        )
    }
}

@Composable
private fun ButtonApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("onClick", "() -> Unit", "required", "Action executed on button click."),
                PropInfo("variant", "AEButtonVariant", "Filled", "Visual variant (Filled, Outlined, Ghost, Destructive, Link)."),
                PropInfo("size", "AEButtonSize", "Medium", "Height and padding preset (Small, Medium, Large)."),
                PropInfo("enabled", "Boolean", "true", "Controls whether the button is interactive."),
                PropInfo("content", "@Composable RowScope.() -> Unit", "required", "Button label or icon slot.")
            )
        )
    }
}
