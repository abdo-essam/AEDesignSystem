package com.ae.designsystem.sample.docs.sources

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.source_copied
import aedesignsystem.sample.composeapp.generated.resources.source_copy_button
import aedesignsystem.sample.composeapp.generated.resources.source_dependency
import aedesignsystem.sample.composeapp.generated.resources.source_file_label
import aedesignsystem.sample.composeapp.generated.resources.source_instruction
import aedesignsystem.sample.composeapp.generated.resources.source_no_source
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun SourceCodeViewer(componentId: String) {
    val sourceFiles = ComponentSources.sources[componentId]

    if (sourceFiles.isNullOrEmpty()) {
        BasicText(
            text = stringResource(Res.string.source_no_source),
            style = AETheme.typography.bodyMedium.copy(color = AETheme.colors.textMuted),
        )
        return
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
    ) {
        InstructionBanner()

        sourceFiles.forEach { file ->
            SourceFileBlock(
                fileName = file.name,
                content = file.content,
            )
        }
    }
}

@Composable
private fun InstructionBanner() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(AETheme.radius.md))
            .background(AETheme.colors.surfaceHover)
            .padding(AETheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
    ) {
        BasicText(
            text = stringResource(Res.string.source_instruction),
            style = AETheme.typography.bodySmall.copy(color = AETheme.colors.textPrimary),
        )

        Box(
            modifier = Modifier
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(AETheme.radius.sm))
                .background(AETheme.colors.surface)
                .padding(
                    horizontal = AETheme.spacing.sm,
                    vertical = AETheme.spacing.xs,
                ),
        ) {
            BasicText(
                text = stringResource(
                    Res.string.source_dependency,
                    "io.github.abdo-essam:ae-components:0.1.0"
                ),
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    color = AETheme.colors.textPrimary,
                ),
            )
        }
    }
}

@Composable
private fun SourceFileBlock(
    fileName: String,
    content: String,
) {
    val clipboardManager = LocalClipboardManager.current
    val scope = rememberCoroutineScope()
    var copied by remember { mutableStateOf(false) }

    val copyLabel = stringResource(Res.string.source_copy_button)
    val copiedLabel = stringResource(Res.string.source_copied)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(AETheme.radius.md))
            .background(AETheme.colors.backgroundSecondary),
    ) {
        // Header: file name + copy button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AETheme.colors.surfaceHover)
                .padding(
                    horizontal = AETheme.spacing.md,
                    vertical = AETheme.spacing.xs,
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicText(
                text = stringResource(Res.string.source_file_label, fileName),
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = AETheme.colors.textSecondary,
                ),
            )

            AEButton(
                onClick = {
                    clipboardManager.setText(AnnotatedString(content))
                    copied = true
                    scope.launch {
                        delay(2000)
                        copied = false
                    }
                },
                variant = if (copied) AEButtonVariant.Ghost else AEButtonVariant.Outlined,
                size = AEButtonSize.Small,
            ) {
                AEText(text = if (copied) copiedLabel else copyLabel)
            }
        }

        // Source code
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState())
                .padding(AETheme.spacing.md),
        ) {
            BasicText(
                text = content,
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    color = AETheme.colors.textPrimary,
                ),
                modifier = Modifier.widthIn(min = 800.dp),
            )
        }
    }
}
