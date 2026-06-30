package com.ae.designsystem.sample.docs.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.bottomsheet.AEBottomSheet
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.navigationbar.AENavigationBar
import com.ae.designsystem.components.ui.navigationbar.AENavItem
import com.ae.designsystem.components.ui.tabs.AETabs
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.components.ui.topappbar.AETopAppBar
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme
import androidx.compose.ui.unit.dp

@Composable
internal fun NavigationCard(modifier: Modifier = Modifier) {
    val spacing = AETheme.spacing
    var selectedTab by remember { mutableIntStateOf(0) }
    var showSheet   by remember { mutableStateOf(false) }
    var currentNav  by remember { mutableIntStateOf(0) }

    if (showSheet) {
        SortSheet(onDismiss = { showSheet = false })
    }

    AECard(modifier = modifier, header = { AEText("Navigation & Overlays", style = AETheme.typography.headingSmall) }) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xl)) {
            TabsGroup(selectedTab = selectedTab, onSelect = { selectedTab = it })
            AEDivider()
            BottomSheetTrigger(onOpen = { showSheet = true })
            AEDivider()
            TopAppBarGroup()
            AEDivider()
            NavBarGroup(currentNav = currentNav, onSelect = { currentNav = it })
        }
    }
}

@Composable
private fun SortSheet(onDismiss: () -> Unit) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    AEBottomSheet(visible = true, onDismiss = onDismiss, title = "Sort Options") {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            listOf("Name A–Z", "Name Z–A", "Newest first").forEach { opt ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.md),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    AEIcon(AEIcons.ChevronRight, tint = colors.textMuted, size = 16.dp)
                    AEText(opt, style = AETheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
private fun TabsGroup(selectedTab: Int, onSelect: (Int) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Tabs", style = AETheme.typography.headingSmall)
        AETabs(tabs = listOf("Overview", "Tokens", "Components"), selectedIndex = selectedTab, onTabSelected = onSelect)
    }
}

@Composable
private fun BottomSheetTrigger(onOpen: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Bottom Sheet", style = AETheme.typography.headingSmall)
        AEButton(onClick = onOpen, variant = AEButtonVariant.Outlined) {
            AEIcon(AEIcons.ChevronUp, tint = AETheme.colors.accent, size = 16.dp)
            Spacer(Modifier.width(AETheme.spacing.sm))
            AEText("Show Bottom Sheet", color = AETheme.colors.accent)
        }
    }
}

@Composable
private fun TopAppBarGroup() {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md)) {
        AEText("Top App Bar", style = AETheme.typography.headingSmall)
        AETopAppBar(title = "Settings", elevated = true)
        AETopAppBar(
            title = "Profile",
            navigationIcon = AEIcons.ArrowLeft,
            onNavigationClick = {},
        )
    }
}

@Composable
private fun NavBarGroup(currentNav: Int, onSelect: (Int) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(AETheme.spacing.sm)) {
        AEText("Navigation Bar", style = AETheme.typography.headingSmall)
        AENavigationBar(
            items = listOf(
                AENavItem(AEIcons.Home,   "Home",    currentNav == 0),
                AENavItem(AEIcons.Search, "Explore", currentNav == 1),
                AENavItem(AEIcons.Heart,  "Saved",   currentNav == 2, badge = 3),
                AENavItem(AEIcons.User,   "Profile", currentNav == 3),
            ),
            onItemSelected = onSelect,
        )
    }
}
