package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.bottomsheet.AEBottomSheet
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun BottomSheetDoc() {
    ComponentPageHeader(
        name = "BottomSheet",
        description = "Bottom sheet component that slides up from the bottom with a scrim overlay.",
    )

    ComponentFamily(
        related = ComponentFamilies.DIALOGS,
        currentId = "bottomsheet",
    )

    TabbedDocPage(
        overview = { BottomSheetOverviewTab() },
        usage = { BottomSheetUsageTab() },
        api = { BottomSheetApiTab() },
    )
}

@Composable
private fun BottomSheetOverviewTab() {
    DocSection("Live Preview") {
        var showSheet by remember { mutableStateOf(false) }

        DemoBox {
            AEButton(onClick = { showSheet = true }) {
                AEText("Show Bottom Sheet")
            }
        }

        AEBottomSheet(
            visible = showSheet,
            onDismiss = { showSheet = false },
            title = "Filter Options",
        ) {
            Column(modifier = Modifier.padding(AETheme.spacing.md)) {
                AEText("Select an option below to filter search results.", color = AETheme.colors.textSecondary)
                Spacer(Modifier.height(AETheme.spacing.md))
                AEButton(onClick = { showSheet = false }, modifier = Modifier.fillMaxWidth()) {
                    AEText("Apply Filters")
                }
            }
        }
    }
}

@Composable
private fun BottomSheetUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
var showSheet by remember { mutableStateOf(false) }

AEBottomSheet(
    visible = showSheet,
    onDismiss = { showSheet = false },
    title = "Options",
) {
    // Sheet content here
}
            """.trimIndent()
        )
    }
}

@Composable
private fun BottomSheetApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("visible", "Boolean", "required", "Controls showing/hiding the bottom sheet."),
                PropInfo("onDismiss", "() -> Unit", "required", "Triggered on background scrim click or back button."),
                PropInfo("title", "String?", "null", "Optional header title string."),
                PropInfo("scrimColor", "Color", "Black copy(0.4f)", "Background overlay tint color."),
                PropInfo("content", "@Composable ColumnScope.() -> Unit", "required", "Sheet body content slot.")
            )
        )
    }
}
