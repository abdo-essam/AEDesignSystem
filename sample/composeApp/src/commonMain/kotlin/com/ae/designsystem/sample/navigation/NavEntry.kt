package com.ae.designsystem.sample.navigation

import com.ae.designsystem.sample.navigation.AppNavGraph.ComponentsCatalogRoute
import com.ae.designsystem.sample.navigation.AppNavGraph.HomeRoute

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
            )
    }
}
