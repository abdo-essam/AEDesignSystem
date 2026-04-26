package com.ae.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Tabs — horizontal navigation with animated indicator.
 *
 * ```
 * var selectedTab by remember { mutableIntStateOf(0) }
 * AETabs(
 *     tabs = listOf("Overview", "Tokens", "Components"),
 *     selectedIndex = selectedTab,
 *     onTabSelected = { selectedTab = it },
 * )
 * ```
 *
 * @param tabs List of tab labels.
 * @param selectedIndex Currently selected tab index.
 * @param onTabSelected Callback with new index.
 */
@Composable
public fun AETabs(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val motion = AETheme.motion

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.background),
        ) {
            tabs.forEachIndexed { index, label ->
                val isSelected = index == selectedIndex
                val textColor by animateColorAsState(
                    targetValue = if (isSelected) colors.accent else colors.textMuted,
                    animationSpec = tween(motion.durationFast),
                    label = "tabTextColor",
                )
                val indicatorColor by animateColorAsState(
                    targetValue = if (isSelected) colors.accent else Color.Transparent,
                    animationSpec = tween(motion.durationMedium),
                    label = "tabIndicator",
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable(
                            indication = null,
                            interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                            role = Role.Tab,
                            onClick = { onTabSelected(index) },
                        )
                        .semantics {
                            role = Role.Tab
                            selected = isSelected
                        }
                        .drawBehind {
                            // Bottom indicator line
                            drawRect(
                                color = indicatorColor,
                                topLeft = Offset(0f, size.height - 2.dp.toPx()),
                                size = androidx.compose.ui.geometry.Size(size.width, 2.dp.toPx()),
                            )
                        }
                        .padding(vertical = spacing.md, horizontal = spacing.sm),
                    contentAlignment = Alignment.Center,
                ) {
                    AEText(
                        text = label,
                        style = if (isSelected) AETheme.typography.labelLarge else AETheme.typography.labelMedium,
                        color = textColor,
                    )
                }
            }
        }

        // Full-width bottom border
        AEDivider()
    }
}
