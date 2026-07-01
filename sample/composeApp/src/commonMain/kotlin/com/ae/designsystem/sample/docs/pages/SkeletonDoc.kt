package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.skeleton.AESkeleton
import com.ae.designsystem.components.ui.skeleton.AESkeletonListItem
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun SkeletonDoc() {
    ComponentPageHeader(
        name = "Skeleton",
        description = "Pulsing/shimmering skeleton loading placeholders.",
    )

    ComponentFamily(
        related = ComponentFamilies.LOADING,
        currentId = "skeleton",
    )

    TabbedDocPage(
        overview = { SkeletonOverviewTab() },
        usage = { SkeletonUsageTab() },
        api = { SkeletonApiTab() },
    )
}

@Composable
private fun SkeletonOverviewTab() {
    DocSection("Basic Skeleton Blocks") {
        DemoBox {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md), modifier = Modifier.fillMaxWidth()) {
                AESkeleton(modifier = Modifier.fillMaxWidth().height(16.dp))
                AESkeleton(modifier = Modifier.width(120.dp).height(16.dp))
            }
        }
    }

    DocSection("Skeleton List Item Preset") {
        DemoBox {
            AESkeletonListItem()
        }
    }
}

@Composable
private fun SkeletonUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AESkeleton(modifier = Modifier.size(48.dp))
AESkeletonListItem()
            """.trimIndent()
        )
    }
}

@Composable
private fun SkeletonApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("modifier", "Modifier", "required", "Size and positioning constraints."),
                PropInfo("shape", "Shape", "RoundedCornerShape(radius.md)", "Rounding clip shape.")
            )
        )
    }
}
