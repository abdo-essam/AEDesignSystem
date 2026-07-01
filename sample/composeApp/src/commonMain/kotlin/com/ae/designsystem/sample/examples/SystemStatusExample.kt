package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.components.ui.progress.AELinearProgress
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun SystemStatusExample() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        label = "System Status",
    ) {
        CardHeader {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "System Status", variant = TextVariant.H4)
                Spinner(size = SpinnerSize.Sm)
            }
        }

        CardContent {
            StatusRow(name = "API Server", badge = "Operational", badgeVariant = AEBadgeVariant.Success)
            Spacer(modifier = Modifier.height(AETheme.spacing.sm))
            StatusRow(name = "Database", badge = "Healthy", badgeVariant = AEBadgeVariant.Success)
            Spacer(modifier = Modifier.height(AETheme.spacing.sm))
            StatusRow(name = "CDN", badge = "Degraded", badgeVariant = AEBadgeVariant.Warning)
            Spacer(modifier = Modifier.height(AETheme.spacing.md))
            Text(
                text = "99.87% uptime over the last 90 days",
                variant = TextVariant.Small,
                color = AETheme.colors.textMuted,
            )
            Spacer(modifier = Modifier.height(AETheme.spacing.xs))
            AELinearProgress(
                progress = 0.87f,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun StatusRow(
    name: String,
    badge: String,
    badgeVariant: AEBadgeVariant,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = name, variant = TextVariant.P)
        Badge(text = badge, variant = badgeVariant.toCompatVariant())
    }
}

private fun AEBadgeVariant.toCompatVariant(): BadgeVariant = when (this) {
    AEBadgeVariant.Default -> BadgeVariant.Default
    AEBadgeVariant.Success -> BadgeVariant.Success
    AEBadgeVariant.Warning -> BadgeVariant.Warning
    AEBadgeVariant.Info -> BadgeVariant.Info
    AEBadgeVariant.Destructive -> BadgeVariant.Destructive
    AEBadgeVariant.Outline -> BadgeVariant.Outline
}
