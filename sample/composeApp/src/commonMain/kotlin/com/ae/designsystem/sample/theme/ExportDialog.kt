package com.ae.designsystem.sample.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.ThemeConfiguration

/**
 * Export dialog — displays the current [ThemeConfiguration] serialized
 * in the user-selected [ExportFormat], with format toggle tabs.
 *
 * @param config    Active theme configuration to export.
 * @param onDismiss Callback to close the dialog.
 */
@Composable
internal fun ExportDialog(
    config: ThemeConfiguration,
    onDismiss: () -> Unit,
) {
    var format by remember { mutableStateOf(ExportFormat.Kotlin) }
    val code = remember(config, format) { ThemeExporter.export(config, format) }

    AEDialog(
        visible  = true,
        onDismiss = onDismiss,
        title    = "Export Theme",
        confirmButton = { DismissButton(onDismiss) },
    ) {
        DialogContent(format = format, code = code, onFormatChange = { format = it })
    }
}

@Composable
private fun DismissButton(onDismiss: () -> Unit) {
    AEButton(onClick = onDismiss, variant = AEButtonVariant.Ghost, size = AEButtonSize.Small) {
        AEText("Close", color = AETheme.colors.textPrimary)
    }
}

@Composable
private fun DialogContent(
    format: ExportFormat,
    code: String,
    onFormatChange: (ExportFormat) -> Unit,
) {
    val spacing = AETheme.spacing
    val colors  = AETheme.colors

    Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
        // Format selector
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            ExportFormat.entries.forEach { f ->
                AEChip(label = f.label, selected = f == format, onClick = { onFormatChange(f) })
            }
        }

        // Code block
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(AETheme.radius.md))
                .background(colors.surface)
                .padding(spacing.md),
        ) {
            AEText(text = code, style = AETheme.typography.code, color = colors.textPrimary)
        }
    }
}
