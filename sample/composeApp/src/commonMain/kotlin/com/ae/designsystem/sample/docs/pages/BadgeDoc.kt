package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun BadgeDoc() {
    ComponentPageHeader(
        name = "Badge",
        description = "Small labeled indicator for counts, status, and tags.",
    )

    ComponentFamily(
        related = ComponentFamilies.MESSAGING,
        currentId = "badge",
    )

    TabbedDocPage(
        overview = { BadgeOverviewTab() },
        usage = { BadgeUsageTab() },
        api = { BadgeApiTab() },
    )
}

@Composable
private fun BadgeOverviewTab() {
    DocSection("Variants") {
        DemoBox {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)
            ) {
                AEBadge(label = "Default", variant = AEBadgeVariant.Default)
                AEBadge(label = "Destructive", variant = AEBadgeVariant.Destructive)
                AEBadge(label = "Success", variant = AEBadgeVariant.Success)
                AEBadge(label = "Warning", variant = AEBadgeVariant.Warning)
                AEBadge(label = "Info", variant = AEBadgeVariant.Info)
                AEBadge(label = "Outline", variant = AEBadgeVariant.Outline)
            }
        }
    }
}

@Composable
private fun BadgeUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AEBadge(label = "New")
AEBadge(label = "Error", variant = AEBadgeVariant.Destructive)
AEBadge(label = "Success", variant = AEBadgeVariant.Success)
            """.trimIndent()
        )
    }

    DocSection("Guidelines") {
        DoAndDont(
            doContent = {
                AEBadge(label = "Active", variant = AEBadgeVariant.Success)
            },
            doDescription = "Use correct status color codes to denote state.",
            dontContent = {
                AEBadge(label = "Delete", variant = AEBadgeVariant.Success)
            },
            dontDescription = "Avoid confusing semantic colors (e.g. green for negative delete actions).",
        )
    }
}

@Composable
private fun BadgeApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("label", "String", "required", "Text string shown inside the badge."),
                PropInfo("variant", "AEBadgeVariant", "Default", "Semantic color style (Default, Destructive, Success, Warning, Info, Outline).")
            )
        )
    }
}
