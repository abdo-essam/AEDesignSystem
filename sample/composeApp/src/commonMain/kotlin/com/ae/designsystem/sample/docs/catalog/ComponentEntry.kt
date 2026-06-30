package com.ae.designsystem.sample.docs.catalog

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.category_data_display
import aedesignsystem.sample.composeapp.generated.resources.category_feedback
import aedesignsystem.sample.composeapp.generated.resources.category_forms
import aedesignsystem.sample.composeapp.generated.resources.category_layout
import aedesignsystem.sample.composeapp.generated.resources.category_navigation
import aedesignsystem.sample.composeapp.generated.resources.category_overlays

enum class ComponentCategory(
    val labelRes: StringResource,
) {
    Layout(Res.string.category_layout),
    Forms(Res.string.category_forms),
    DataDisplay(Res.string.category_data_display),
    Feedback(Res.string.category_feedback),
    Overlays(Res.string.category_overlays),
    Navigation(Res.string.category_navigation),
}

data class ComponentEntry(
    val id: String,
    val rawName: String,
    val rawDescription: String,
    val nameRes: StringResource,
    val descriptionRes: StringResource,
    val category: ComponentCategory,
    val content: @Composable () -> Unit,
)
