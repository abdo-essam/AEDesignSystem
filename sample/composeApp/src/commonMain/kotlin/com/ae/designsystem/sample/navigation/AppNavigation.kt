package com.ae.designsystem.sample.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ae.designsystem.sample.showcase.ShowcaseRoute
import com.ae.designsystem.sample.CatalogApp

@Composable
fun AppNavigation(
    navController: NavHostController,
    initialRoute: AppNavGraph = AppNavGraph.HomeRoute,
) {
    NavHost(
        navController = navController,
        startDestination = AppNavGraph.HomeRoute,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable<AppNavGraph.HomeRoute> {
            ShowcaseRoute(
                onNavigateToComponents = { navController.navigate(AppNavGraph.ComponentsCatalogRoute) },
            )
        }

        composable<AppNavGraph.ComponentsCatalogRoute> {
            CatalogApp()
        }
    }
}
