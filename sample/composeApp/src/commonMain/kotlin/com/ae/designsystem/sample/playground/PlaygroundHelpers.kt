package com.ae.designsystem.sample.playground

import androidx.compose.runtime.Composable
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme

/** Reusable section label for playground control panels. */
@Composable
internal fun ControlLabel(text: String) {
    AEText(
        text  = text,
        style = AETheme.typography.labelMedium,
        color = AETheme.colors.textMuted,
    )
}

/** Registry of all available playground components. Extend here to add more. */
internal fun playgroundItems(): List<PlaygroundItem> = listOf(
    ButtonPlayground(),
    TextFieldPlayground(),
    CheckboxPlayground(),
    CardPlayground(),
)
