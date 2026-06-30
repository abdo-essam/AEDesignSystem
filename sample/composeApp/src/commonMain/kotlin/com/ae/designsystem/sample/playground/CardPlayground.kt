package com.ae.designsystem.sample.playground

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.switch.AESwitch
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme

/** Playground for [AECard] — toggles header, footer, and content text. */
internal class CardPlayground : PlaygroundItem {

    override val title = "Card"

    private var showHeader by mutableStateOf(true)
    private var showFooter by mutableStateOf(true)

    @Composable
    override fun Controls() {
        val spacing = AETheme.spacing

        Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
            AESwitch(checked = showHeader, onCheckedChange = { showHeader = it }, label = "Show Header")
            AESwitch(checked = showFooter, onCheckedChange = { showFooter = it }, label = "Show Footer")
        }
    }

    @Composable
    override fun Preview() {
        val colors = AETheme.colors

        AECard(
            header = if (showHeader) ({
                AEText("Card Title", style = AETheme.typography.headingSmall)
            }) else null,
            footer = if (showFooter) ({
                AEButton(onClick = {}, size = AEButtonSize.Small) {
                    AEText("Action", color = colors.textOnAccent)
                }
            }) else null,
        ) {
            AEText("Card body content goes here.", color = colors.textSecondary)
        }
    }
}
