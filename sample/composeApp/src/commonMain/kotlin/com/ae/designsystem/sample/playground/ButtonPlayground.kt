package com.ae.designsystem.sample.playground

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

/** Playground for [AEButton] — toggles variant, size, loading, icon, and disabled state. */
internal class ButtonPlayground : PlaygroundItem {

    override val title = "Button"

    // Internal controls state — owned by this implementation
    private var variant by mutableStateOf(AEButtonVariant.Filled)
    private var size    by mutableStateOf(AEButtonSize.Medium)
    private var enabled by mutableStateOf(true)
    private var showIcon by mutableStateOf(false)

    @Composable
    override fun Controls() {
        val colors  = AETheme.colors
        val spacing = AETheme.spacing

        Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
            ControlLabel("Variant")
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                modifier = Modifier.horizontalScroll(rememberScrollState()),
            ) {
                AEButtonVariant.entries.forEach { v ->
                    AEChip(label = v.name, selected = v == variant, onClick = { variant = v })
                }
            }

            ControlLabel("Size")
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                AEButtonSize.entries.forEach { s ->
                    AEChip(label = s.name, selected = s == size, onClick = { size = s })
                }
            }

            AESwitch(checked = enabled,  onCheckedChange = { enabled  = it }, label = "Enabled")
            AESwitch(checked = showIcon, onCheckedChange = { showIcon = it }, label = "Show Icon")
        }
    }

    @Composable
    override fun Preview() {
        val colors = AETheme.colors

        AEButton(onClick = {}, variant = variant, size = size, enabled = enabled) {
            if (showIcon) {
                AEIcon(AEIcons.Star, tint = colors.textOnAccent, size = 16.dp)
                Spacer(Modifier.width(6.dp))
            }
            AEText("Button", color = colors.textOnAccent)
        }
    }
}
