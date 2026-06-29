package com.ae.designsystem.sample.docs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.docs.components.cards.*

/**
 * Components screen — aggregates all showcase cards.
 * Each card is fully isolated; scroll is global to the screen.
 */
@Composable
internal fun ComponentsScreen(modifier: Modifier = Modifier) {
    val colors  = AETheme.colors
    val spacing = AETheme.spacing

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
            .verticalScroll(rememberScrollState())
            .padding(spacing.xl),
        verticalArrangement = Arrangement.spacedBy(spacing.xxl),
    ) {
        ButtonsCard()
        InputsCard()
        FeedbackCard()
        NavigationCard()
        SurfacesCard()
        Spacer(Modifier.height(spacing.xxxl))
    }
}
