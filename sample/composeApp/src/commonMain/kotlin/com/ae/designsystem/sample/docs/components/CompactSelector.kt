package com.ae.designsystem.sample.docs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import org.jetbrains.compose.resources.stringResource
import com.ae.designsystem.sample.docs.catalog.ComponentEntry
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun CompactSelector(
    entries: List<ComponentEntry>,
    selectedId: String,
    onSelect: (String) -> Unit,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
    ) {
        entries.forEach { entry ->
            CompactChip(
                text = stringResource(entry.nameRes),
                isActive = entry.id == selectedId,
                onClick = { onSelect(entry.id) },
            )
        }
    }
}

@Composable
private fun CompactChip(
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
                horizontal = AETheme.spacing.sm,
                vertical = AETheme.spacing.xs,
            ),
    ) {
        BasicText(
            text = text,
            style = AETheme.typography.bodySmall.copy(color = fg),
        )
    }
}
