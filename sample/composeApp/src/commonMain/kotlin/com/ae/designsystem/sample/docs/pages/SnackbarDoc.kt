package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.snackbar.AESnackbarHost
import com.ae.designsystem.components.ui.snackbar.AESnackbarVariant
import com.ae.designsystem.components.ui.snackbar.rememberSnackbarState
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme
import kotlinx.coroutines.launch

@Composable
fun SnackbarDoc() {
    ComponentPageHeader(
        name = "Snackbar",
        description = "Bottom-overlay message alert with optional action triggers.",
    )

    ComponentFamily(
        related = ComponentFamilies.MESSAGING,
        currentId = "snackbar",
    )

    TabbedDocPage(
        overview = { SnackbarOverviewTab() },
        usage = { SnackbarUsageTab() },
        api = { SnackbarApiTab() },
    )
}

@Composable
private fun SnackbarOverviewTab() {
    DocSection("Live Preview") {
        val snackbarState = rememberSnackbarState()
        val scope = rememberCoroutineScope()

        DemoBox {
            Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
                AEButton(onClick = {
                    scope.launch {
                        snackbarState.show("Success message triggered!", variant = AESnackbarVariant.Success)
                    }
                }) {
                    AEText("Show Success Snackbar")
                }

                AEButton(onClick = {
                    scope.launch {
                        snackbarState.show("Warning details occurred", variant = AESnackbarVariant.Warning)
                    }
                }) {
                    AEText("Show Warning")
                }
            }
        }

        AESnackbarHost(state = snackbarState)
    }
}

@Composable
private fun SnackbarUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
val state = rememberSnackbarState()
val scope = rememberCoroutineScope()

AESnackbarHost(state = state)

// Trigger:
scope.launch {
    state.show("Action Complete", variant = AESnackbarVariant.Success)
}
            """.trimIndent()
        )
    }
}

@Composable
private fun SnackbarApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("state", "AESnackbarState", "required", "Hoisted state tracking visibilities and actions."),
                PropInfo("modifier", "Modifier", "Modifier", "Host positioning layout wrapper constraints.")
            )
        )
    }
}
