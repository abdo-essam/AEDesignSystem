package com.ae.designsystem.sample.navigation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object RoutePaths {
    const val HOME = "home"
    const val COMPONENTS = "components"
    const val DOCS = "docs"
    const val WHY_AE = "why-ae"
    const val CREATE = "create"
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
    @SerialName(RoutePaths.DOCS)
    data class DocsRoute(
        val componentId: String? = null,
        val guideId: String? = null,
    ) : AppNavGraph()

    @Serializable
    @SerialName(RoutePaths.WHY_AE)
    data object WhyAERoute : AppNavGraph()

    @Serializable
    @SerialName(RoutePaths.CREATE)
    data object CreatorRoute : AppNavGraph()
}
