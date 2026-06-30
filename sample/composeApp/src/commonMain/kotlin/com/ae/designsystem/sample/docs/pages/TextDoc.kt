package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun TextDoc() {
    ComponentPageHeader(
        name = "Text",
        description = "Component wrapper applying AEDesignSystem typography tokens.",
    )

    TabbedDocPage(
        overview = { TextOverviewTab() },
        usage = { TextUsageTab() },
        api = { TextApiTab() },
    )
}

@Composable
private fun TextOverviewTab() {
    DocSection("Typography Tokens") {
        DemoBox {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md), modifier = Modifier.fillMaxWidth()) {
                AEText("Display Large", style = AETheme.typography.displayLarge)
                AEText("Display Medium", style = AETheme.typography.displayMedium)
                AEText("Heading Large", style = AETheme.typography.headingLarge)
                AEText("Heading Medium", style = AETheme.typography.headingMedium)
                AEText("Heading Small", style = AETheme.typography.headingSmall)
                AEText("Body Large", style = AETheme.typography.bodyLarge)
                AEText("Body Medium", style = AETheme.typography.bodyMedium)
                AEText("Body Small", style = AETheme.typography.bodySmall)
                AEText("Label Large", style = AETheme.typography.labelLarge)
                AEText("Label Medium", style = AETheme.typography.labelMedium)
                AEText("Label Small", style = AETheme.typography.labelSmall)
                AEText("println(\"Code Monospace\")", style = AETheme.typography.code)
            }
        }
    }
}

@Composable
private fun TextUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AEText("Hello World")
AEText("Header", style = AETheme.typography.headingMedium)
AEText("Muted caption", color = AETheme.colors.textMuted, style = AETheme.typography.bodySmall)
            """.trimIndent()
        )
    }
}

@Composable
private fun TextApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("text", "String", "required", "String content to render."),
                PropInfo("style", "TextStyle", "AETheme.typography.bodyMedium", "Typography style preset."),
                PropInfo("color", "Color", "AETheme.colors.textPrimary", "Color override merged into style."),
                PropInfo("maxLines", "Int", "Int.MAX_VALUE", "Controls maximum line wrapping limit."),
                PropInfo("overflow", "TextOverflow", "Clip", "How to clip truncated overflow text.")
            )
        )
    }
}
