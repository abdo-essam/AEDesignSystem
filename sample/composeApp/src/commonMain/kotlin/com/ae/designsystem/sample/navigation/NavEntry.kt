package com.ae.designsystem.sample.navigation

import com.ae.designsystem.sample.navigation.AppNavGraph.ComponentsCatalogRoute
import com.ae.designsystem.sample.navigation.AppNavGraph.CreatorRoute
import com.ae.designsystem.sample.navigation.AppNavGraph.DocsRoute
import com.ae.designsystem.sample.navigation.AppNavGraph.HomeRoute
import com.ae.designsystem.sample.navigation.AppNavGraph.WhyAERoute

data class NavEntry(
    val label: String,
    val route: Any,
    val matchPrefix: String,
) {
    companion object {
        fun getNavEntries(): List<NavEntry> =
            listOf(
                NavEntry(
                    label = "Home",
                    route = HomeRoute,
                    matchPrefix = RoutePaths.HOME,
                ),
                NavEntry(
                    label = "Components",
                    route = ComponentsCatalogRoute,
                    matchPrefix = RoutePaths.COMPONENTS,
                ),
                NavEntry(
                    label = "Docs",
                    route = DocsRoute(),
                    matchPrefix = RoutePaths.DOCS,
                ),
                NavEntry(
                    label = "Why AE",
                    route = WhyAERoute,
                    matchPrefix = RoutePaths.WHY_AE,
                ),
                NavEntry(
                    label = "Theme Creator",
                    route = CreatorRoute,
                    matchPrefix = RoutePaths.CREATE,
                ),
            )
    }
}
