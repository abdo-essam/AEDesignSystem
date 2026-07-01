package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.avatar.AEAvatar
import com.ae.designsystem.components.ui.avatar.AEAvatarSize
import com.ae.designsystem.components.ui.checkbox.AECheckbox
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun TaskBoardExample() {
    var task1Checked by remember { mutableStateOf(false) }
    var task2Checked by remember { mutableStateOf(false) }
    var task3Checked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        label = "Task Board",
    ) {
        CardHeader {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Sprint Tasks", variant = TextVariant.H4)
                Badge(text = "3 active", variant = BadgeVariant.Default)
            }
        }

        CardContent {
            Column(
                verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AECheckbox(
                        checked = task1Checked,
                        onCheckedChange = { task1Checked = it },
                        label = "Design token system",
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Badge(text = "High", variant = BadgeVariant.Destructive, size = BadgeSize.Sm)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AECheckbox(
                        checked = task2Checked,
                        onCheckedChange = { task2Checked = it },
                        label = "Write unit tests",
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Badge(text = "Medium", variant = BadgeVariant.Info, size = BadgeSize.Sm)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AECheckbox(
                        checked = task3Checked,
                        onCheckedChange = { task3Checked = it },
                        label = "Update documentation",
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Badge(text = "Low", variant = BadgeVariant.Outline, size = BadgeSize.Sm)
                }
            }
        }

        Separator()

        CardFooter {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AEAvatar(initials = "AK", size = AEAvatarSize.Small)
                Text(
                    text = "Assigned to you",
                    variant = TextVariant.Small,
                    color = AETheme.colors.textMuted,
                    modifier = Modifier.weight(1f),
                )
                Button(text = "View Board", onClick = { }, variant = ButtonVariant.Ghost)
            }
        }
    }
}
