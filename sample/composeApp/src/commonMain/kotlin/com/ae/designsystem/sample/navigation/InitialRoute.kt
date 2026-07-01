package com.ae.designsystem.sample.navigation

/** Returns the initial nav route based on the current URL/hash (platform-specific). */
expect fun resolveInitialRoute(): AppNavGraph

/** Updates the address bar / history to reflect the current docs page (platform-specific). */
expect fun updateDocsHash(pageId: String)
