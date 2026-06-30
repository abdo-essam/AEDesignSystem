package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.checkbox.AECheckbox
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun CheckboxDoc() {
    ComponentPageHeader(
        name = "Checkbox",
        description = "Standard binary toggle control with custom theme coloring.",
    )

    ComponentFamily(
        related = ComponentFamilies.SELECTION,
        currentId = "checkbox",
    )

    TabbedDocPage(
        overview = { CheckboxOverviewTab() },
        usage = { CheckboxUsageTab() },
        api = { CheckboxApiTab() },
    )
}

@Composable
private fun CheckboxOverviewTab() {
    DocSection("Basic Checkbox") {
        var checked1 by remember { mutableStateOf(false) }
        var checked2 by remember { mutableStateOf(true) }

        DemoBox {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
                AECheckbox(checked = checked1, onCheckedChange = { checked1 = it }, label = "Accept terms")
                AECheckbox(checked = checked2, onCheckedChange = { checked2 = it }, label = "Subscribe to newsletter")
                AECheckbox(checked = false, onCheckedChange = {}, enabled = false, label = "Disabled Checkbox")
            }
        }
    }
}

@Composable
private fun CheckboxUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
var isChecked by remember { mutableStateOf(false) }

AECheckbox(
    checked = isChecked,
    onCheckedChange = { isChecked = it },
    label = "Enable feature"
)
            """.trimIndent()
        )
    }
}

@Composable
private fun CheckboxApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("checked", "Boolean", "required", "State indicating if checkbox is filled."),
                PropInfo("onCheckedChange", "(Boolean) -> Unit", "required", "Triggered when clicked to toggle checked state."),
                PropInfo("label", "String?", "null", "Optional text label beside the checkbox."),
                PropInfo("enabled", "Boolean", "true", "Controls if the checkbox accepts pointer hover and clicks.")
            )
        )
    }
}
