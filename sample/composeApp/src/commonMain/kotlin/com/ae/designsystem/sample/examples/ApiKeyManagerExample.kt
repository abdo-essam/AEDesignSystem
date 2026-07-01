package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun ApiKeyManagerExample() {
    Card {
        CardHeader {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "API Keys", variant = TextVariant.H4)
                Button(text = "+ New Key", onClick = { }, size = ButtonSize.Sm)
            }
        }

        CardContent {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs)) {
                KeyEntry(label = "Production", maskedKey = "sk-prod-••••••1f4a")
                KeyEntry(label = "Development", maskedKey = "sk-dev-••••••9c2b")
            }
        }

        Separator()

        Alert(variant = AlertVariant.Destructive) {
            AlertTitle(text = "Rate Limited", variant = AlertVariant.Destructive)
            AlertDescription(text = "90% of your monthly quota has been used.", variant = AlertVariant.Destructive)
        }
    }
}

@Composable
private fun KeyEntry(
    label: String,
    maskedKey: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = label, variant = TextVariant.P)
        Spacer(modifier = Modifier.weight(1f))
        Kbd(text = maskedKey)
        IconButton(
            icon = AEIcons.Copy,
            contentDescription = "Copy $label",
            onClick = { },
        )
    }
}
