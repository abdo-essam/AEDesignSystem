package com.ae.designsystem.sample.examples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun FileExplorerExample() {
    var searchQuery by remember { mutableStateOf("") }

    Card(
        modifier = Modifier.fillMaxWidth(),
        label = "File Browser",
    ) {
        CardHeader {
            Text(text = "Project Files", variant = TextVariant.H4)
        }

        Spacer(Modifier.height(AETheme.spacing.md))

        CardContent {
            Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
                FileRow(
                    icon = AEIcons.Edit,
                    iconDesc = "Kotlin file",
                    name = "App.kt",
                    size = "12 KB",
                )
                FileRow(
                    icon = AEIcons.Settings,
                    iconDesc = "Build file",
                    name = "build.gradle.kts",
                    size = "4 KB",
                )
                FileRow(
                    icon = AEIcons.Download,
                    iconDesc = "Readme file",
                    name = "README.md",
                    size = "8 KB",
                )
            }
        }

        Separator()

        CardFooter {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Input(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = "Search files…",
                    label = "Search",
                    modifier = Modifier.weight(1f),
                )
                IconButton(
                    icon = AEIcons.Search,
                    contentDescription = "Search files",
                    onClick = { },
                )
            }

            Spacer(Modifier.height(AETheme.spacing.sm))

            Text(text = "3 files", variant = TextVariant.Muted)
        }
    }
}

@Composable
private fun FileRow(
    icon: AEIconToken,
    iconDesc: String,
    name: String,
    size: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AEIcon(
            token = icon,
            contentDescription = iconDesc,
            tint = AETheme.colors.textMuted,
            size = 16.dp,
        )
        Text(
            text = name,
            variant = TextVariant.P,
            modifier = Modifier.weight(1f),
        )
        Text(text = size, variant = TextVariant.Muted)
    }
}
