package com.ae.designsystem.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ae.designsystem.components.AEButton
import com.ae.designsystem.components.AEButtonSize
import com.ae.designsystem.components.AEButtonVariant
import com.ae.designsystem.components.AEDivider
import com.ae.designsystem.components.AEText
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.app.AppAction
import com.ae.designsystem.sample.app.AppViewModel
import com.ae.designsystem.sample.app.LocalAppState
import com.ae.designsystem.sample.navigation.AppNavigation
import com.ae.designsystem.sample.navigation.NavEntry
import com.ae.designsystem.sample.utils.WindowSizeClass

@Composable
fun SiteApp() {
    val navController = rememberNavController()
    val appViewModel: AppViewModel = viewModel { AppViewModel() }
    val appState by appViewModel.state.collectAsState()

    CompositionLocalProvider(
        LocalAppState provides appState,
    ) {
        AETheme(
            palette = AEPalette.Zinc,
            darkTheme = appState.isDark,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().background(AETheme.colors.background),
            ) {
                TopNavBar(
                    navController = navController,
                    isDark = appState.isDark,
                    onDarkChange = { appViewModel.onAction(AppAction.SetDarkMode(it)) },
                )

                AEDivider()

                AppNavigation(
                    navController = navController,
                )
            }
        }
    }
}

@Composable
private fun TopNavBar(
    navController: NavHostController,
    isDark: Boolean,
    onDarkChange: (Boolean) -> Unit,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: ""
    var menuExpanded by remember { mutableStateOf(false) }

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val sizeClass = WindowSizeClass.fromWidth(maxWidth)
        val isCompact = sizeClass == WindowSizeClass.Compact

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = AETheme.spacing.lg,
                        vertical = AETheme.spacing.sm,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
                ) {
                    AEText(
                        text = "AEDesignSystem",
                        style = AETheme.typography.headingMedium,
                    )

                    if (!isCompact) {
                        Spacer(Modifier.width(AETheme.spacing.md))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
                        ) {
                            items(NavEntry.getNavEntries()) { entry ->
                                val isActive = currentRoute.contains(entry.matchPrefix)
                                AEButton(
                                    onClick = { navController.navigate(entry.route) },
                                    variant = if (isActive) AEButtonVariant.Filled else AEButtonVariant.Ghost,
                                    size = AEButtonSize.Small,
                                ) {
                                    AEText(text = entry.label)
                                }
                            }
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
                ) {
                    AEButton(
                        onClick = { onDarkChange(!isDark) },
                        variant = AEButtonVariant.Ghost,
                        size = AEButtonSize.Small,
                    ) {
                        AEIcon(
                            if (isDark) AEIcons.Sun else AEIcons.Moon,
                        )
                    }

                    if (isCompact) {
                        AEButton(
                            onClick = { menuExpanded = !menuExpanded },
                            variant = AEButtonVariant.Ghost,
                            size = AEButtonSize.Small,
                        ) {
                            AEIcon(
                                if (menuExpanded) AEIcons.Close else AEIcons.Menu,
                            )
                        }
                    }
                }
            }

            if (isCompact && menuExpanded) {
                MobileMenu(
                    navController = navController,
                    currentRoute = currentRoute,
                    onNavigate = { menuExpanded = false },
                )
            }
        }
    }
}

@Composable
private fun MobileMenu(
    navController: NavHostController,
    currentRoute: String,
    onNavigate: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AETheme.colors.surface)
            .padding(
                horizontal = AETheme.spacing.lg,
                vertical = AETheme.spacing.sm,
            ),
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
    ) {
        NavEntry.getNavEntries().forEach { entry ->
            val isActive = currentRoute.contains(entry.matchPrefix)
            MobileMenuItem(
                label = entry.label,
                isActive = isActive,
                onClick = {
                    navController.navigate(entry.route)
                    onNavigate()
                },
            )
        }

        AEDivider()
        Spacer(Modifier.height(AETheme.spacing.xs))
    }
}

@Composable
private fun MobileMenuItem(
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val bg =
        when {
            isActive -> AETheme.colors.surfaceHover
            isHovered -> AETheme.colors.surfaceHover
            else -> AETheme.colors.surface
        }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AETheme.radius.md))
            .background(bg)
            .hoverable(interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(
                horizontal = AETheme.spacing.md,
                vertical = AETheme.spacing.sm,
            ),
    ) {
        AEText(
            text = label,
            style = if (isActive) AETheme.typography.labelLarge else AETheme.typography.bodyMedium,
            color = if (isActive) AETheme.colors.textPrimary else AETheme.colors.textMuted,
        )
    }
}
