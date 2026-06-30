package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.switch.AESwitch
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun SwitchDoc() {
    ComponentPageHeader(
        name = "Switch",
        description = "Visual toggle switch control denoting checked active state.",
    )

    ComponentFamily(
        related = ComponentFamilies.SELECTION,
        currentId = "switch",
    )

    TabbedDocPage(
        overview = { SwitchOverviewTab() },
        usage = { SwitchUsageTab() },
        api = { SwitchApiTab() },
    )
}

@Composable
private fun SwitchOverviewTab() {
    DocSection("Switch Controls") {
        var checked1 by remember { mutableStateOf(false) }
        var checked2 by remember { mutableStateOf(true) }

        DemoBox {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
                AESwitch(checked = checked1, onCheckedChange = { checked1 = it }, label = "Wi-Fi Wireless")
                AESwitch(checked = checked2, onCheckedChange = { checked2 = it }, label = "Dark Mode Override")
                AESwitch(checked = false, onCheckedChange = {}, enabled = false, label = "Disabled Switch")
            }
        }
    }
}

@Composable
private fun SwitchUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
var isEnabled by remember { mutableStateOf(false) }

AESwitch(
    checked = isEnabled,
    onCheckedChange = { isEnabled = it },
    label = "Enable Setting"
)
            """.trimIndent()
        )
    }
}

@Composable
private fun SwitchApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("checked", "Boolean", "required", "State indicating active filled handle position."),
                PropInfo("onCheckedChange", "(Boolean) -> Unit", "required", "Triggered when clicked to toggle checked state."),
                PropInfo("label", "String?", "null", "Text label beside switch."),
                PropInfo("enabled", "Boolean", "true", "Controls interactive clicks.")
            )
        )
    }
}
