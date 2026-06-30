package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.textfield.AETextField
import com.ae.designsystem.components.ui.textfield.AETextFieldState
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun TextFieldDoc() {
    ComponentPageHeader(
        name = "TextField",
        description = "Standard textual input supporting icons, states, and helper text.",
    )

    ComponentFamily(
        related = ComponentFamilies.TEXT_INPUTS,
        currentId = "textfield",
    )

    TabbedDocPage(
        overview = { TextFieldOverviewTab() },
        usage = { TextFieldUsageTab() },
        api = { TextFieldApiTab() },
    )
}

@Composable
private fun TextFieldOverviewTab() {
    DocSection("TextField Showcase") {
        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("Pre-filled text value") }

        DemoBox {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md), modifier = Modifier.fillMaxWidth()) {
                AETextField(
                    value = text1,
                    onValueChange = { text1 = it },
                    label = "Username Input",
                    placeholder = "Enter username details...",
                    leadingIcon = AEIcons.User
                )

                AETextField(
                    value = text2,
                    onValueChange = { text2 = it },
                    label = "Status Feedback",
                    state = AETextFieldState.Success,
                    supportingText = "Value accepted successfully"
                )
            }
        }
    }
}

@Composable
private fun TextFieldUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
var username by remember { mutableStateOf("") }

AETextField(
    value = username,
    onValueChange = { username = it },
    label = "Username",
    placeholder = "Type here..."
)
            """.trimIndent()
        )
    }
}

@Composable
private fun TextFieldApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("value", "String", "required", "Current input value string."),
                PropInfo("onValueChange", "(String) -> Unit", "required", "Callback triggered on keystroke changes."),
                PropInfo("label", "String?", "null", "Top label string."),
                PropInfo("placeholder", "String?", "null", "Ghost placeholder string."),
                PropInfo("supportingText", "String?", "null", "Supporting description text shown below line."),
                PropInfo("state", "AETextFieldState", "Default", "Visual state (Default, Success, Error)."),
                PropInfo("enabled", "Boolean", "true", "Controls interactive keystroke events.")
            )
        )
    }
}
