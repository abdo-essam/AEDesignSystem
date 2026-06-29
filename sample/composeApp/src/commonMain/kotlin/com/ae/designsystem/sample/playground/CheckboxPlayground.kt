package com.ae.designsystem.sample.playground

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.theme.AETheme

/** Playground for [AECheckbox] — toggles checked, enabled, and label. */
internal class CheckboxPlayground : PlaygroundItem {

    override val title = "Checkbox"

    private var checked by mutableStateOf(true)
    private var enabled by mutableStateOf(true)

    @Composable
    override fun Controls() {
        val spacing = AETheme.spacing

        Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
            AESwitch(checked = checked, onCheckedChange = { checked = it }, label = "Checked")
            AESwitch(checked = enabled, onCheckedChange = { enabled = it }, label = "Enabled")
        }
    }

    @Composable
    override fun Preview() {
        AECheckbox(
            checked       = checked,
            onCheckedChange = { checked = it },
            label         = "Accept terms and conditions",
            enabled       = enabled,
        )
    }
}
