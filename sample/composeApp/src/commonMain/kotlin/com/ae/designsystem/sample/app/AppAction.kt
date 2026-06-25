package com.ae.designsystem.sample.app

sealed interface AppAction {
    data class SetDarkMode(val isDark: Boolean) : AppAction
    data object ToggleDarkMode : AppAction
}
