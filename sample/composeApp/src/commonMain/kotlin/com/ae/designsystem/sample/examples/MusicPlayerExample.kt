package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.progress.AELinearProgress
import com.ae.designsystem.components.ui.slider.AESlider
import com.ae.designsystem.components.ui.switch.AESwitch
import com.ae.designsystem.components.ui.text.AEText
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun MusicPlayerExample() {
    var isPlaying by remember { mutableStateOf(false) }
    var volume by remember { mutableFloatStateOf(0.7f) }
    var shuffleOn by remember { mutableStateOf(false) }
    var repeatOn by remember { mutableStateOf(true) }

    AECard(
        header = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AEText("Now Playing", style = AETheme.typography.headingSmall)
                AEBadge("HD Audio", variant = AEBadgeVariant.Outline)
            }
        },
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
            ) {
                AEText("Composing Harmony", style = AETheme.typography.headingMedium)
                AEText("AEDesignSystem", color = AETheme.colors.textMuted)
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
            ) {
                AELinearProgress(progress = 0.65f)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    AEText("2:15", color = AETheme.colors.textMuted, style = AETheme.typography.labelSmall)
                    AEText("3:30", color = AETheme.colors.textMuted, style = AETheme.typography.labelSmall)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    AETheme.spacing.sm,
                    Alignment.CenterHorizontally,
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AEButton(
                    onClick = { },
                    variant = AEButtonVariant.Ghost,
                    size = AEButtonSize.Small,
                ) {
                    AEIcon(AEIcons.ArrowLeft, size = 20.dp)
                }
                AEButton(onClick = { isPlaying = !isPlaying }, size = AEButtonSize.Medium) {
                    AEIcon(if (isPlaying) AEIcons.Pause else AEIcons.Play, size = 24.dp)
                }
                AEButton(
                    onClick = { },
                    variant = AEButtonVariant.Ghost,
                    size = AEButtonSize.Small,
                ) {
                    AEIcon(AEIcons.ArrowRight, size = 20.dp)
                }
            }

            Spacer(modifier = Modifier.height(AETheme.spacing.xs))

            Column(
                verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
            ) {
                AEText("Volume", style = AETheme.typography.labelMedium, color = AETheme.colors.textMuted)
                AESlider(value = volume, onValueChange = { volume = it })
            }

            AEDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xl),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AEText("Shuffle", style = AETheme.typography.labelSmall)
                    AESwitch(checked = shuffleOn, onCheckedChange = { shuffleOn = it })
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AEText("Repeat", style = AETheme.typography.labelSmall)
                    AESwitch(checked = repeatOn, onCheckedChange = { repeatOn = it })
                }
            }
        }
    }
}
