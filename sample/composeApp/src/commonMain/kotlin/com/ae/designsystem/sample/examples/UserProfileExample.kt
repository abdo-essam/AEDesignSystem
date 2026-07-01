package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.ae.designsystem.components.ui.avatar.AEAvatar
import com.ae.designsystem.components.ui.avatar.AEAvatarSize
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun UserProfileExample() {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkMode by remember { mutableStateOf(false) }
    var newsletterEnabled by remember { mutableStateOf(true) }

    Card {
        CardHeader {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
            ) {
                AEAvatar(initials = "JD", size = AEAvatarSize.Large)
                Text(
                    text = "Jane Doe",
                    variant = TextVariant.H4,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "jane.doe@example.com",
                    variant = TextVariant.Muted,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Separator()

        CardContent {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
                PreferenceRow(
                    label = "Notifications",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                )
                PreferenceRow(
                    label = "Dark Mode",
                    checked = darkMode,
                    onCheckedChange = { darkMode = it },
                )
                PreferenceRow(
                    label = "Newsletter",
                    checked = newsletterEnabled,
                    onCheckedChange = { newsletterEnabled = it },
                )
            }
        }

        CardFooter {
            Button(
                text = "Edit Profile",
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun PreferenceRow(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            variant = TextVariant.Small,
            color = AETheme.colors.textMuted,
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}
