package com.ae.designsystem.sample.docs.components.cards

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.chip.AEChip
import com.ae.designsystem.components.ui.dialog.AEDialog
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.progress.AELinearProgress
import com.ae.designsystem.components.ui.progress.AECircularProgress
import com.ae.designsystem.components.ui.skeleton.AESkeletonListItem
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme

@Composable
internal fun FeedbackCard(modifier: Modifier = Modifier) {
    val spacing = AETheme.spacing
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        FeedbackDialog(onDismiss = { showDialog = false })
    }

    AECard(modifier = modifier, header = { AEText("Feedback & Status", style = AETheme.typography.headingSmall) }) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xl)) {
            BadgesGroup()
            AEDivider()
            ChipsGroup()
            AEDivider()
            ProgressGroup()
            AEDivider()
            SkeletonGroup()
            AEDivider()
            DialogTrigger(onOpen = { showDialog = true })
        }
    }
}

@Composable
private fun FeedbackDialog(onDismiss: () -> Unit) {
    val colors = AETheme.colors
    AEDialog(
        visible  = true,
        onDismiss = onDismiss,
        title    = "Confirm Action",
        confirmButton = {
            AEButton(onClick = onDismiss, variant = AEButtonVariant.Destructive, size = AEButtonSize.Small) {
                AEText("Delete", color = colors.destructiveText)
            }
        },
        dismissButton = {
            AEButton(onClick = onDismiss, variant = AEButtonVariant.Ghost, size = AEButtonSize.Small) {
                AEText("Cancel", color = colors.textPrimary)
            }
        },
    ) {
        AEText("This action cannot be undone.", color = colors.textSecondary)
    }
}

@Composable
private fun BadgesGroup() {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Badges", style = AETheme.typography.headingSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm), modifier = Modifier.horizontalScroll(rememberScrollState())) {
            AEBadge("Default")
            AEBadge("Destructive", variant = AEBadgeVariant.Destructive)
            AEBadge("Success",     variant = AEBadgeVariant.Success)
            AEBadge("Warning",     variant = AEBadgeVariant.Warning)
            AEBadge("Info",        variant = AEBadgeVariant.Info)
            AEBadge("Outline",     variant = AEBadgeVariant.Outline)
        }
    }
}

@Composable
private fun ChipsGroup() {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Chips", style = AETheme.typography.headingSmall)
        Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
            AEChip("Kotlin")
            AEChip("Selected", selected = true)
        }
    }
}

@Composable
private fun ProgressGroup() {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md)) {
        AEText("Progress", style = AETheme.typography.headingSmall)
        AELinearProgress(progress = 0.65f)
        AELinearProgress(progress = null)
        Row(horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xl), verticalAlignment = Alignment.CenterVertically) {
            AECircularProgress(progress = 0.7f)
            AECircularProgress(progress = null)
        }
    }
}

@Composable
private fun SkeletonGroup() {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md)) {
        AEText("Skeleton", style = AETheme.typography.headingSmall)
        repeat(2) { AESkeletonListItem() }
    }
}

@Composable
private fun DialogTrigger(onOpen: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Dialog", style = AETheme.typography.headingSmall)
        AEButton(onClick = onOpen, variant = AEButtonVariant.Outlined) {
            AEText("Open Dialog", color = AETheme.colors.accent)
        }
    }
}
