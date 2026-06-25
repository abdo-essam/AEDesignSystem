package com.ae.designsystem.sample.showcase

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ae.designsystem.sample.app.LocalAppState

@Composable
fun ShowcaseRoute(
    onNavigateToComponents: () -> Unit,
    viewModel: ShowcaseViewModel = viewModel { ShowcaseViewModel() },
) {
    val state by viewModel.state.collectAsState()
    val appState = LocalAppState.current

    ShowcaseScreen(
        state = state,
        isDark = appState.isDark,
        onAction = viewModel::onAction,
        onNavigateToComponents = onNavigateToComponents,
    )
}
