package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun CardDoc() {
    ComponentPageHeader(
        name = "Card",
        description = "Container component with border, surface shading, and structured header/footer layouts.",
    )

    TabbedDocPage(
        overview = { CardOverviewTab() },
        usage = { CardUsageTab() },
        api = { CardApiTab() },
    )
}

@Composable
private fun CardOverviewTab() {
    DocSection("Standard Card") {
        DemoBox {
            AECard(
                header = { AEText("Card Title", style = AETheme.typography.headingSmall) },
                footer = { AEText("Card Footer Info", color = AETheme.colors.textMuted, style = AETheme.typography.bodySmall) },
                modifier = Modifier.fillMaxWidth()
            ) {
                AEText("This is the main body content of the card component.", color = AETheme.colors.textSecondary)
            }
        }
    }
}

@Composable
private fun CardUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AECard(
    header = { AEText("Header") },
    footer = { AEText("Footer") }
) {
    AEText("Body Content")
}
            """.trimIndent()
        )
    }
}

@Composable
private fun CardApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("color", "Color", "AETheme.colors.surface", "Background fill color of card."),
                PropInfo("shape", "Shape", "RoundedCornerShape(radius.lg)", "Corner rounding path."),
                PropInfo("elevation", "Dp", "0.dp", "Drop shadow depth."),
                PropInfo("header", "@Composable () -> Unit?", "null", "Top header layout slot."),
                PropInfo("footer", "@Composable () -> Unit?", "null", "Bottom footer layout slot."),
                PropInfo("content", "@Composable ColumnScope.() -> Unit", "required", "Card body content layout slot.")
            )
        )
    }
}
