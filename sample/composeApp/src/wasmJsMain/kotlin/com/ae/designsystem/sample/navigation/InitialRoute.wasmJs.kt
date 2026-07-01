package com.ae.designsystem.sample.navigation

import kotlinx.browser.window

actual fun resolveInitialRoute(): AppNavGraph {
    val hash = window.location.hash
        .removePrefix("#")
        .trimStart('/')

    return when {
        hash.isEmpty() || hash == RoutePaths.HOME -> {
            AppNavGraph.HomeRoute
        }

        hash == RoutePaths.COMPONENTS -> {
            AppNavGraph.ComponentsCatalogRoute
        }

        hash == RoutePaths.CREATE -> {
            AppNavGraph.CreatorRoute
        }

        hash == RoutePaths.WHY_AE -> {
            AppNavGraph.WhyAERoute
        }

        hash.startsWith(RoutePaths.DOCS) -> {
            val guideId = extractParam(hash, "guideId")
            val componentId = extractParam(hash, "componentId")
            AppNavGraph.DocsRoute(componentId = componentId, guideId = guideId)
        }

        else -> {
            AppNavGraph.HomeRoute
        }
    }
}

actual fun updateDocsHash(pageId: String) {
    val guidePageIds = com.ae.designsystem.sample.docs.catalog.guidePageIds
    val path = if (pageId in guidePageIds) {
        "docs?guideId=$pageId"
    } else {
        "docs?componentId=$pageId"
    }
    window.history.replaceState(null, "", "#$path")
}

private fun extractParam(hash: String, paramName: String): String? {
    val query = hash.substringAfter("?", "")
    if (query.isEmpty()) return null
    return query.split("&")
        .mapNotNull { part ->
            val split = part.split("=", limit = 2)
            if (split.size == 2) split[0] to split[1] else null
        }
        .firstOrNull { it.first == paramName }?.second
}
