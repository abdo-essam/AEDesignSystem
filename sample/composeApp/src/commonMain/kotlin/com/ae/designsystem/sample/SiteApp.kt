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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText
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
    val initialRoute = remember { com.ae.designsystem.sample.navigation.resolveInitialRoute() }
    val appViewModel: AppViewModel = viewModel { AppViewModel() }
    val appState by appViewModel.state.collectAsState()

    CompositionLocalProvider(
        LocalAppState provides appState,
    ) {
        val typography = com.ae.designsystem.foundation.typography.AETypography.default(
            com.ae.designsystem.sample.utils.ThemeUtils.getFontFamily()
        )
        AETheme(
            palette = AEPalette.Zinc,
            darkTheme = appState.isDark,
            typography = typography,
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
                    initialRoute = initialRoute,
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
    val uriHandler = LocalUriHandler.current

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val sizeClass = WindowSizeClass.fromWidth(maxWidth)
        val isCompact = sizeClass == WindowSizeClass.Compact

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                // ── Left: Logo + Nav links ────────────────────────
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(0.dp),
                ) {
                    // Logo
                    AEText(
                        text = "AEDesignSystem",
                        style = AETheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.SemiBold,
                        ),
                        modifier = Modifier.clickable {
                            navController.navigate(
                                com.ae.designsystem.sample.navigation.AppNavGraph.HomeRoute
                            ) {
                                popUpTo(navController.graph.id) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                    )

                    // Inline nav links — only on non-compact
                    if (!isCompact) {
                        Spacer(Modifier.width(24.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            items(NavEntry.getNavEntries()) { entry ->
                                val isActive = currentRoute.contains(entry.matchPrefix)
                                NavLink(
                                    label = entry.label,
                                    isActive = isActive,
                                    onClick = { navController.navigate(entry.route) },
                                )
                            }
                        }
                    }
                }

                // ── Right: GitHub + dark mode toggle ─────────────
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    if (!isCompact) {
                        // GitHub button — outlined like RikkaUI
                        AEButton(
                            onClick = { uriHandler.openUri("https://github.com/abdo-essam/AEDesignSystem") },
                            variant = AEButtonVariant.Outlined,
                            size = AEButtonSize.Small,
                        ) {
                            AEText("GitHub")
                        }
                    }

                    // Dark / light mode toggle icon
                    AEButton(
                        onClick = { onDarkChange(!isDark) },
                        variant = AEButtonVariant.Ghost,
                        size = AEButtonSize.Small,
                    ) {
                        AEIcon(if (isDark) AEIcons.Sun else AEIcons.Moon)
                    }

                    // Hamburger for compact
                    if (isCompact) {
                        AEButton(
                            onClick = { menuExpanded = !menuExpanded },
                            variant = AEButtonVariant.Ghost,
                            size = AEButtonSize.Small,
                        ) {
                            AEIcon(if (menuExpanded) AEIcons.Close else AEIcons.Menu)
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

/** Plain text nav link — matches RikkaUI's minimal header style. */
@Composable
private fun NavLink(
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(
                when {
                    isActive -> AETheme.colors.surfaceHover
                    isHovered -> AETheme.colors.surfaceHover
                    else -> androidx.compose.ui.graphics.Color.Transparent
                }
            )
            .hoverable(interactionSource)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        AEText(
            text = label,
            style = AETheme.typography.labelMedium.copy(
                fontWeight = if (isActive) FontWeight.Medium else FontWeight.Normal,
            ),
            color = if (isActive) AETheme.colors.textPrimary else AETheme.colors.textMuted,
        )
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
            .padding(horizontal = AETheme.spacing.lg, vertical = AETheme.spacing.sm),
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

    val bg = when {
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
            .padding(horizontal = AETheme.spacing.md, vertical = AETheme.spacing.sm),
    ) {
        AEText(
            text = label,
            style = if (isActive) AETheme.typography.labelLarge else AETheme.typography.bodyMedium,
            color = if (isActive) AETheme.colors.textPrimary else AETheme.colors.textMuted,
        )
    }
}
