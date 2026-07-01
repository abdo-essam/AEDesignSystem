package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.avatar.AEAvatar
import com.ae.designsystem.components.ui.avatar.AEAvatarSize
import com.ae.designsystem.foundation.theme.AETheme

private data class FeedEntry(
    val initials: String,
    val action: String,
    val time: String,
)

private val feedEntries = listOf(
    FeedEntry("AL", "Alice pushed to main", "2m ago"),
    FeedEntry("BO", "Bob opened pull request #42", "15m ago"),
    FeedEntry("CA", "Carol commented on issue #8", "1h ago"),
    FeedEntry("DA", "Dave closed milestone v1.0", "3h ago"),
)

@Composable
fun ActivityFeedExample() {
    Card(label = "Recent Activity") {
        CardContent {
            Column(
                verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
                modifier = Modifier.fillMaxWidth(),
            ) {
                feedEntries.forEach { entry ->
                    ActivityRow(entry)
                }
            }
        }
    }
}

@Composable
private fun ActivityRow(entry: FeedEntry) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AEAvatar(
            initials = entry.initials,
            size = AEAvatarSize.Small,
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = entry.action, variant = TextVariant.P)
            Text(text = entry.time, variant = TextVariant.Muted)
        }
    }
}
