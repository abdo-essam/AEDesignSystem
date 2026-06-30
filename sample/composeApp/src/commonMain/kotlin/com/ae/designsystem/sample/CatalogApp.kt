package com.ae.designsystem.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ae.designsystem.components.ui.snackbar.AESnackbarHost
import com.ae.designsystem.components.ui.tabs.AETabs
import com.ae.designsystem.components.ui.snackbar.rememberSnackbarState
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.docs.AboutScreen
import com.ae.designsystem.sample.docs.GettingStartedScreen
import com.ae.designsystem.sample.docs.components.ComponentsScreen
import com.ae.designsystem.sample.docs.foundation.FoundationScreen
import com.ae.designsystem.sample.playground.PlaygroundScreen
import com.ae.designsystem.sample.theme.ThemeScreen

/**
 * Root catalog shell.
 *
 * Single responsibilities:
 *  - Own [ThemeConfiguration] state — single source of truth for all theme knobs.
 *  - Own [CatalogDestination] navigation state.
 *  - Provide [AETheme] tokens to the entire tree.
 *  - Render [AETabs] for top-level destination switching.
 *  - Route to each screen via an exhaustive [when] expression.
 *  - Host [AESnackbarHost] floating above content.
 *
 * Everything else lives in its own package.
 */
@Composable
public fun CatalogApp() {
    var theme by remember { mutableStateOf(ThemeConfiguration()) }
    var destination by remember { mutableStateOf(CatalogDestination.GettingStarted) }
    val snackbarState = rememberSnackbarState()

    AETheme(
        palette   = theme.palette,
        accent    = theme.accent,
        preset    = theme.preset,
        darkTheme = theme.darkMode,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AETheme.colors.background),
        ) {
            CatalogScaffold(
                destination   = destination,
                theme         = theme,
                onDestination = { destination = it },
                onThemeChange = { theme = it },
            )
            AESnackbarHost(
                state    = snackbarState,
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

// ── Private scaffold — keeps CatalogApp under 20 lines of logic ─

@Composable
private fun CatalogScaffold(
    destination: CatalogDestination,
    theme: ThemeConfiguration,
    onDestination: (CatalogDestination) -> Unit,
    onThemeChange: (ThemeConfiguration) -> Unit,
) {
    val spacing = AETheme.spacing

    Column(modifier = Modifier.fillMaxSize()) {
        AETabs(
            tabs          = CatalogDestination.entries.map { it.label },
            selectedIndex = CatalogDestination.entries.indexOf(destination),
            onTabSelected = { onDestination(CatalogDestination.entries[it]) },
        )

        Box(modifier = Modifier.fillMaxSize().padding(bottom = spacing.xxxl)) {
            when (destination) {
                CatalogDestination.GettingStarted -> GettingStartedScreen()
                CatalogDestination.Foundation     -> FoundationScreen()
                CatalogDestination.Components     -> ComponentsScreen()
                CatalogDestination.Playground     -> PlaygroundScreen()
                CatalogDestination.Theme          -> ThemeScreen(
                    config        = theme,
                    onThemeChange = onThemeChange,
                )
                CatalogDestination.About          -> AboutScreen()
            }
        }
    }
}
