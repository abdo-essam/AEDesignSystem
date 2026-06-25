package com.ae.designsystem.sample.app

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _state = MutableStateFlow(AppState(isDark = true))
    val state: StateFlow<AppState> = _state.asStateFlow()

    fun onAction(action: AppAction) {
        when (action) {
            is AppAction.SetDarkMode -> reduce { copy(isDark = action.isDark) }
            is AppAction.ToggleDarkMode -> reduce { copy(isDark = !isDark) }
        }
    }

    private fun reduce(block: AppState.() -> AppState) {
        _state.update { it.block() }
    }
}
