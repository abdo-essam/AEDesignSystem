package com.ae.designsystem.sample.docs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.source_tab_code
import aedesignsystem.sample.composeapp.generated.resources.source_tab_docs
import com.ae.designsystem.sample.docs.catalog.ComponentRegistry
import com.ae.designsystem.sample.docs.sources.SourceCodeViewer
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun PageContent(
    selectedId: String,
    registry: ComponentRegistry,
) {
    Column(modifier = Modifier.widthIn(max = 900.dp)) {
        val entry = registry.findById(selectedId)
        if (entry != null) {
            var showCode by remember(selectedId) { mutableStateOf(false) }

            Row(
                modifier = Modifier.padding(bottom = AETheme.spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
            ) {
                SourceTab(
                    text = stringResource(Res.string.source_tab_docs),
                    isActive = !showCode,
                    onClick = { showCode = false },
                )
                SourceTab(
                    text = stringResource(Res.string.source_tab_code),
                    isActive = showCode,
                    onClick = { showCode = true },
                )
            }

            if (showCode) {
                SourceCodeViewer(componentId = entry.id)
            } else {
                entry.content()
            }
        }
    }
}

@Composable
private fun SourceTab(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val bg = when {
        isActive -> AETheme.colors.accent
        isHovered -> AETheme.colors.surfaceHover
        else -> AETheme.colors.surface
    }
    val fg = if (isActive) {
        AETheme.colors.textOnAccent
    } else {
        AETheme.colors.textPrimary
    }

    Box(
        modifier = Modifier
            .hoverable(interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .background(bg, androidx.compose.foundation.shape.RoundedCornerShape(AETheme.radius.md))
            .padding(
                horizontal = AETheme.spacing.md,
                vertical = AETheme.spacing.xs,
            ),
    ) {
        BasicText(
            text = text,
            style = AETheme.typography.bodySmall.copy(
                color = fg,
                fontWeight = FontWeight.Medium
            ),
        )
    }
}
