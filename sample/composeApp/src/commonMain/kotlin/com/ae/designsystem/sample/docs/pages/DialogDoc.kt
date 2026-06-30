package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.dialog.AEDialog
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun DialogDoc() {
    ComponentPageHeader(
        name = "Dialog",
        description = "Modal overlay blocking interaction with the main page layout.",
    )

    ComponentFamily(
        related = ComponentFamilies.DIALOGS,
        currentId = "dialog",
    )

    TabbedDocPage(
        overview = { DialogOverviewTab() },
        usage = { DialogUsageTab() },
        api = { DialogApiTab() },
    )
}

@Composable
private fun DialogOverviewTab() {
    DocSection("Live Preview") {
        var showDialog by remember { mutableStateOf(false) }

        DemoBox {
            AEButton(onClick = { showDialog = true }) {
                AEText("Show Modal Dialog")
            }
        }

        AEDialog(
            visible = showDialog,
            onDismiss = { showDialog = false },
            title = "Delete Project?",
            confirmButton = {
                AEButton(onClick = { showDialog = false }) {
                    AEText("Confirm")
                }
            },
            dismissButton = {
                AEButton(onClick = { showDialog = false }, variant = com.ae.designsystem.components.ui.button.AEButtonVariant.Ghost) {
                    AEText("Cancel")
                }
            }
        ) {
            AEText("This action will permanently delete your workspace file backups.", color = AETheme.colors.textSecondary)
        }
    }
}

@Composable
private fun DialogUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AEDialog(
    visible = showDialog,
    onDismiss = { showDialog = false },
    title = "Confirm Delete"
) {
    AEText("Are you sure?")
}
            """.trimIndent()
        )
    }
}

@Composable
private fun DialogApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("visible", "Boolean", "required", "Controls showing/hiding the overlay modal."),
                PropInfo("onDismiss", "() -> Unit", "required", "Triggered when dialog background is clicked."),
                PropInfo("title", "String?", "null", "Dialog title string."),
                PropInfo("dismissible", "Boolean", "true", "Controls whether clicking outside dismisses."),
                PropInfo("confirmButton", "@Composable () -> Unit?", "null", "Confirm button slot."),
                PropInfo("dismissButton", "@Composable () -> Unit?", "null", "Cancel/dismiss button slot."),
                PropInfo("content", "@Composable ColumnScope.() -> Unit", "required", "Dialog body content layout slot.")
            )
        )
    }
}
