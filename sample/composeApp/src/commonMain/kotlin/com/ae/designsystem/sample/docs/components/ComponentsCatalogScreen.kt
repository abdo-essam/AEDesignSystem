@file:OptIn(ExperimentalLayoutApi::class)

package com.ae.designsystem.sample.docs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.catalog_no_results
import aedesignsystem.sample.composeapp.generated.resources.catalog_search_placeholder
import aedesignsystem.sample.composeapp.generated.resources.catalog_subtitle
import aedesignsystem.sample.composeapp.generated.resources.catalog_title
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.components.ui.textfield.AETextField
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.catalog.ComponentCategory
import com.ae.designsystem.sample.docs.catalog.ComponentEntry
import com.ae.designsystem.sample.docs.catalog.ComponentRegistry
import com.ae.designsystem.foundation.theme.AETheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun ComponentsCatalogScreen(onComponentClick: (String) -> Unit) {
    val allEntries = remember { ComponentRegistry.entries }
    val totalCount = remember { allEntries.size }

    var query by remember { mutableStateOf("") }

    val filteredGrouped by remember(query) {
        derivedStateOf {
            val q = query.trim().lowercase()
            val filtered =
                if (q.isEmpty()) {
                    allEntries
                } else {
                    allEntries.filter { entry ->
                        entry.rawName.lowercase().contains(q) ||
                            entry.rawDescription.lowercase().contains(q)
                    }
                }
            ComponentCategory.entries
                .associateWith { cat -> filtered.filter { it.category == cat } }
                .filterValues { it.isNotEmpty() }
        }
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 1120.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    horizontal = AETheme.spacing.xl,
                    vertical = AETheme.spacing.xxl,
                ),
        ) {
            // ── Header ──
            AEText(
                text = stringResource(Res.string.catalog_title),
                style = AETheme.typography.displayLarge,
            )
            Spacer(Modifier.height(AETheme.spacing.sm))
            AEText(
                text = stringResource(Res.string.catalog_subtitle, totalCount),
                style = AETheme.typography.bodyMedium,
                color = AETheme.colors.textMuted,
            )

            Spacer(Modifier.height(AETheme.spacing.lg))

            // ── Search ──
            AETextField(
                value = query,
                onValueChange = { query = it },
                placeholder = stringResource(Res.string.catalog_search_placeholder),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.xl))
            AEDivider()
            Spacer(Modifier.height(AETheme.spacing.xxl))

            // ── Category Sections ──
            if (filteredGrouped.isEmpty()) {
                AEText(
                    text = stringResource(Res.string.catalog_no_results, query),
                    style = AETheme.typography.bodyMedium,
                    color = AETheme.colors.textMuted,
                )
            } else {
                filteredGrouped.entries.forEachIndexed { index, (category, entries) ->
                    CategorySection(
                        category = category,
                        entries = entries,
                        onComponentClick = onComponentClick,
                    )

                    if (index < filteredGrouped.size - 1) {
                        Spacer(Modifier.height(AETheme.spacing.xxl))
                    }
                }
            }

            Spacer(Modifier.height(AETheme.spacing.xxxl))
        }
    }
}

@Composable
private fun CategorySection(
    category: ComponentCategory,
    entries: List<ComponentEntry>,
    onComponentClick: (String) -> Unit,
) {
    AEText(
        text = stringResource(category.labelRes),
        style = AETheme.typography.headingLarge,
    )
    Spacer(Modifier.height(AETheme.spacing.xs))
    AEText(
        text = "${entries.size} components",
        style = AETheme.typography.bodySmall,
        color = AETheme.colors.textMuted,
    )
    Spacer(Modifier.height(AETheme.spacing.lg))

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val columns =
            when {
                maxWidth >= 900.dp -> 3
                maxWidth >= 540.dp -> 2
                else -> 1
            }
        val gap = AETheme.spacing.md

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(gap),
            verticalArrangement = Arrangement.spacedBy(gap),
            maxItemsInEachRow = columns,
            modifier = Modifier.fillMaxWidth(),
        ) {
            entries.forEach { entry ->
                ComponentCard(
                    entry = entry,
                    onClick = { onComponentClick(entry.id) },
                    modifier = Modifier.weight(1f),
                )
            }

            val remainder = entries.size % columns
            if (remainder != 0) {
                repeat(columns - remainder) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ComponentCard(
    entry: ComponentEntry,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val bgColor = if (isHovered) {
        AETheme.colors.backgroundSecondary
    } else {
        AETheme.colors.surface
    }

    val borderColor = if (isHovered) {
        AETheme.colors.accent.copy(alpha = 0.4f)
    } else {
        AETheme.colors.border
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(AETheme.radius.lg))
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(AETheme.radius.lg),
            )
            .background(bgColor, RoundedCornerShape(AETheme.radius.lg))
            .hoverable(interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(AETheme.spacing.lg),
    ) {
        AEText(
            text = stringResource(entry.nameRes),
            style = AETheme.typography.headingSmall,
        )

        Spacer(Modifier.height(AETheme.spacing.xs))

        AEText(
            text = stringResource(entry.descriptionRes),
            style = AETheme.typography.bodyMedium,
            color = AETheme.colors.textMuted,
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        AEBadge(
            label = stringResource(entry.category.labelRes),
            variant = AEBadgeVariant.Outline,
        )
    }
}
