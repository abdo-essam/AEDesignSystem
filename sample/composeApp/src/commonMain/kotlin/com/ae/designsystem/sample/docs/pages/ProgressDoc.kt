package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.progress.AELinearProgress
import com.ae.designsystem.components.ui.progress.AECircularProgress
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun ProgressDoc() {
    ComponentPageHeader(
        name = "Progress",
        description = "Linear and circular progress loading indicator.",
    )

    ComponentFamily(
        related = ComponentFamilies.LOADING,
        currentId = "progress",
    )

    TabbedDocPage(
        overview = { ProgressOverviewTab() },
        usage = { ProgressUsageTab() },
        api = { ProgressApiTab() },
    )
}

@Composable
private fun ProgressOverviewTab() {
    DocSection("Linear Progress") {
        DemoBox {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md), modifier = Modifier.fillMaxWidth()) {
                AELinearProgress(progress = 0.45f)
                AELinearProgress(progress = null) // indeterminate
            }
        }
    }

    DocSection("Circular Progress") {
        DemoBox {
            Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xl)) {
                AECircularProgress(progress = 0.7f)
                AECircularProgress(progress = null) // indeterminate spinner
            }
        }
    }
}

@Composable
private fun ProgressUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AELinearProgress(progress = 0.65f) // Determinate
AECircularProgress(progress = null) // Indeterminate spinner
            """.trimIndent()
        )
    }
}

@Composable
private fun ProgressApiTab() {
    DocSection("API Reference (AELinearProgress)") {
        PropsTable(
            listOf(
                PropInfo("progress", "Float?", "null", "Progress value 0.0 to 1.0. Null for indeterminate animation."),
                PropInfo("color", "Color", "AETheme.colors.accent", "Progress fill color."),
                PropInfo("trackColor", "Color", "AETheme.colors.border", "Track background bar color."),
                PropInfo("height", "Dp", "6.dp", "Thickness of progress bar.")
            )
        )
    }
}
