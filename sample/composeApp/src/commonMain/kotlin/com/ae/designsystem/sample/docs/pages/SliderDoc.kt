package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.slider.AESlider
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun SliderDoc() {
    ComponentPageHeader(
        name = "Slider",
        description = "Numerical range selection slider with custom track styling.",
    )

    TabbedDocPage(
        overview = { SliderOverviewTab() },
        usage = { SliderUsageTab() },
        api = { SliderApiTab() },
    )
}

@Composable
private fun SliderOverviewTab() {
    DocSection("Interactive Slider") {
        var sliderVal by remember { mutableStateOf(0.45f) }

        DemoBox {
            Column(modifier = Modifier.fillMaxWidth()) {
                AESlider(value = sliderVal, onValueChange = { sliderVal = it })
                Spacer(Modifier.height(AETheme.spacing.md))
                AEText("Current value: ${(sliderVal * 100).toInt()}%")
            }
        }
    }
}

@Composable
private fun SliderUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
var progress by remember { mutableStateOf(0.5f) }

AESlider(
    value = progress,
    onValueChange = { progress = it }
)
            """.trimIndent()
        )
    }
}

@Composable
private fun SliderApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("value", "Float", "required", "Numerical value between 0.0 and 1.0."),
                PropInfo("onValueChange", "(Float) -> Unit", "required", "Triggered continuously as the handle is dragged."),
                PropInfo("enabled", "Boolean", "true", "Controls drag interactions.")
            )
        )
    }
}
