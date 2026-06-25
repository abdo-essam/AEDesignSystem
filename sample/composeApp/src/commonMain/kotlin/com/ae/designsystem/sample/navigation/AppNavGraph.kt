package com.ae.designsystem.sample.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object RoutePaths {
    const val HOME = "home"
    const val COMPONENTS = "components"
    const val FOUNDATION = "foundation"
    const val WHY_AE = "why-ae"
}

@Serializable
sealed class AppNavGraph {
    @Serializable
    @SerialName(RoutePaths.HOME)
    data object HomeRoute : AppNavGraph()

    @Serializable
    @SerialName(RoutePaths.COMPONENTS)
    data object ComponentsCatalogRoute : AppNavGraph()

    @Serializable
    @SerialName(RoutePaths.FOUNDATION)
    data object FoundationRoute : AppNavGraph()

    @Serializable
    @SerialName(RoutePaths.WHY_AE)
    data object WhyAERoute : AppNavGraph()
}
