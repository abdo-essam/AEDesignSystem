package com.ae.designsystem.sample.app

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppState: ProvidableCompositionLocal<AppState> =
    staticCompositionLocalOf { error("LocalAppState not provided") }
