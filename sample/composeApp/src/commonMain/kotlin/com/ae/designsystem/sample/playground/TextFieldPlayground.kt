package com.ae.designsystem.sample.playground

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

/** Playground for [AETextField] — toggles state, icon, and enabled. */
internal class TextFieldPlayground : PlaygroundItem {

    override val title = "TextField"

    private var text    by mutableStateOf("")
    private var state   by mutableStateOf(AETextFieldState.Default)
    private var enabled by mutableStateOf(true)
    private var showIcon by mutableStateOf(true)

    @Composable
    override fun Controls() {
        val spacing = AETheme.spacing

        Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
            ControlLabel("State")
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                AETextFieldState.entries.forEach { s ->
                    AEChip(label = s.name, selected = s == state, onClick = { state = s })
                }
            }

            AESwitch(checked = enabled,  onCheckedChange = { enabled  = it }, label = "Enabled")
            AESwitch(checked = showIcon, onCheckedChange = { showIcon = it }, label = "Leading Icon")
        }
    }

    @Composable
    override fun Preview() {
        AETextField(
            value          = text,
            onValueChange  = { text = it },
            label          = "Email",
            placeholder    = "you@example.com",
            leadingIcon    = if (showIcon) AEIcons.User else null,
            state          = state,
            enabled        = enabled,
            supportingText = when (state) {
                AETextFieldState.Success -> "Looks good!"
                AETextFieldState.Error   -> "Please enter a valid email."
                else                     -> null
            },
            modifier = androidx.compose.ui.Modifier.fillMaxWidth(0.85f),
        )
    }
}
