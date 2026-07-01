package com.ae.designsystem.sample.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.examples.*
import com.ae.designsystem.sample.utils.WindowSizeClass

@Composable
fun ExamplesGrid(sizeClass: WindowSizeClass) {
    val gap = AETheme.spacing.md

    when (sizeClass) {
        WindowSizeClass.Compact -> CompactGrid(gap)
        WindowSizeClass.Medium -> MediumGrid(gap)
        WindowSizeClass.Expanded -> ExpandedGrid(gap)
    }
}

@Composable
private fun CompactGrid(gap: Dp) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(gap),
    ) {
        MusicPlayerExample()
        TaskBoardExample()
        UserProfileExample()
        WeatherDashboardExample()
        FileExplorerExample()
        ApiKeyManagerExample()
        QuickNoteExample()
        FeedbackFormExample()
        SystemStatusExample()
        ActivityFeedExample()
    }
}

@Composable
private fun MediumGrid(gap: Dp) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(gap),
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(gap),
        ) {
            MusicPlayerExample()
            WeatherDashboardExample()
            QuickNoteExample()
            SystemStatusExample()
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(gap),
        ) {
            TaskBoardExample()
            FileExplorerExample()
            UserProfileExample()
            ApiKeyManagerExample()
            FeedbackFormExample()
            ActivityFeedExample()
        }
    }
}

@Composable
private fun ExpandedGrid(gap: Dp) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(gap),
    ) {
        Column(
            modifier = Modifier.weight(1.2f),
            verticalArrangement = Arrangement.spacedBy(gap),
        ) {
            MusicPlayerExample()
            WeatherDashboardExample()
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(gap),
        ) {
            TaskBoardExample()
            FileExplorerExample()
            QuickNoteExample()
            SystemStatusExample()
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(gap),
        ) {
            UserProfileExample()
            ApiKeyManagerExample()
            FeedbackFormExample()
            ActivityFeedExample()
        }
    }
}
