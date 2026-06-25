package com.ae.designsystem.sample

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val body = document.body ?: return
    ComposeViewport(body) {
        val initialRoute = SiteRoute.fromHash(window.location.hash)
        SiteApp(
            initialRoute = initialRoute,
            onNavigate = { window.location.hash = it.hash }
        )
    }
}
