package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.surface.AESurface
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun SurfaceDoc() {
    ComponentPageHeader(
        name = "Surface",
        description = "Core building block representing a stylized layout surface block.",
    )

    TabbedDocPage(
        overview = { SurfaceOverviewTab() },
        usage = { SurfaceUsageTab() },
        api = { SurfaceApiTab() },
    )
}

@Composable
private fun SurfaceOverviewTab() {
    DocSection("Stylized Layout Surfaces") {
        DemoBox {
            Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md)) {
                AESurface(
                    modifier = Modifier.size(100.dp),
                    color = AETheme.colors.backgroundSecondary
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        AEText("Box A")
                    }
                }
                AESurface(
                    modifier = Modifier.size(100.dp),
                    color = AETheme.colors.surfaceHover
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                        AEText("Box B")
                    }
                }
            }
        }
    }
}

@Composable
private fun SurfaceUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AESurface(
    modifier = Modifier.padding(16.dp),
    color = AETheme.colors.card
) {
    AEText("Inside Surface")
}
            """.trimIndent()
        )
    }
}

@Composable
private fun SurfaceApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("modifier", "Modifier", "Modifier", "Surface layout constraints."),
                PropInfo("shape", "Shape", "RoundedCornerShape(radius.md)", "Corner clip border shape."),
                PropInfo("color", "Color", "AETheme.colors.card", "Background fill color."),
                PropInfo("elevation", "Dp", "0.dp", "Drop shadow depth.")
            )
        )
    }
}
