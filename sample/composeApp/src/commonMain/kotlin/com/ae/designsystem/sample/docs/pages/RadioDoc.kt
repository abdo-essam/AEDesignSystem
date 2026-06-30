package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.radio.AERadioButton
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun RadioDoc() {
    ComponentPageHeader(
        name = "RadioButton",
        description = "Mutually exclusive selection button control.",
    )

    ComponentFamily(
        related = ComponentFamilies.SELECTION,
        currentId = "radio",
    )

    TabbedDocPage(
        overview = { RadioOverviewTab() },
        usage = { RadioUsageTab() },
        api = { RadioApiTab() },
    )
}

@Composable
private fun RadioOverviewTab() {
    DocSection("Radio Button Group") {
        var selectedOption by remember { mutableStateOf("Option A") }

        DemoBox {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
                AERadioButton(selected = selectedOption == "Option A", onClick = { selectedOption = "Option A" }, label = "Option A")
                AERadioButton(selected = selectedOption == "Option B", onClick = { selectedOption = "Option B" }, label = "Option B")
                AERadioButton(selected = false, onClick = {}, enabled = false, label = "Disabled Option")
            }
        }
    }
}

@Composable
private fun RadioUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
var selectedOption by remember { mutableStateOf("A") }

AERadioButton(
    selected = selectedOption == "A",
    onClick = { selectedOption = "A" },
    label = "Choice A"
)
            """.trimIndent()
        )
    }
}

@Composable
private fun RadioApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("selected", "Boolean", "required", "State indicating active choice."),
                PropInfo("onClick", "() -> Unit", "required", "Action performed when radio button is tapped."),
                PropInfo("label", "String?", "null", "Text label rendered beside circular marker."),
                PropInfo("enabled", "Boolean", "true", "Controls interactive state.")
            )
        )
    }
}
