package com.ae.designsystem.sample.docs.catalog

import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.component_card_name
import aedesignsystem.sample.composeapp.generated.resources.component_card_desc
import aedesignsystem.sample.composeapp.generated.resources.component_separator_name
import aedesignsystem.sample.composeapp.generated.resources.component_separator_desc
import aedesignsystem.sample.composeapp.generated.resources.component_scaffold_name
import aedesignsystem.sample.composeapp.generated.resources.component_scaffold_desc
import aedesignsystem.sample.composeapp.generated.resources.component_button_name
import aedesignsystem.sample.composeapp.generated.resources.component_button_desc
import aedesignsystem.sample.composeapp.generated.resources.component_checkbox_name
import aedesignsystem.sample.composeapp.generated.resources.component_checkbox_desc
import aedesignsystem.sample.composeapp.generated.resources.component_radio_name
import aedesignsystem.sample.composeapp.generated.resources.component_radio_desc
import aedesignsystem.sample.composeapp.generated.resources.component_toggle_name
import aedesignsystem.sample.composeapp.generated.resources.component_toggle_desc
import aedesignsystem.sample.composeapp.generated.resources.component_slider_name
import aedesignsystem.sample.composeapp.generated.resources.component_slider_desc
import aedesignsystem.sample.composeapp.generated.resources.component_input_name
import aedesignsystem.sample.composeapp.generated.resources.component_input_desc
import aedesignsystem.sample.composeapp.generated.resources.component_badge_name
import aedesignsystem.sample.composeapp.generated.resources.component_badge_desc
import aedesignsystem.sample.composeapp.generated.resources.component_avatar_name
import aedesignsystem.sample.composeapp.generated.resources.component_avatar_desc
import aedesignsystem.sample.composeapp.generated.resources.component_text_name
import aedesignsystem.sample.composeapp.generated.resources.component_text_desc
import aedesignsystem.sample.composeapp.generated.resources.component_progress_name
import aedesignsystem.sample.composeapp.generated.resources.component_progress_desc
import aedesignsystem.sample.composeapp.generated.resources.component_skeleton_name
import aedesignsystem.sample.composeapp.generated.resources.component_skeleton_desc
import aedesignsystem.sample.composeapp.generated.resources.component_toast_name
import aedesignsystem.sample.composeapp.generated.resources.component_toast_desc
import aedesignsystem.sample.composeapp.generated.resources.component_dialog_name
import aedesignsystem.sample.composeapp.generated.resources.component_dialog_desc
import aedesignsystem.sample.composeapp.generated.resources.component_sheet_name
import aedesignsystem.sample.composeapp.generated.resources.component_sheet_desc
import aedesignsystem.sample.composeapp.generated.resources.component_tooltip_name
import aedesignsystem.sample.composeapp.generated.resources.component_tooltip_desc
import aedesignsystem.sample.composeapp.generated.resources.component_dropdown_menu_name
import aedesignsystem.sample.composeapp.generated.resources.component_dropdown_menu_desc
import aedesignsystem.sample.composeapp.generated.resources.component_tabs_name
import aedesignsystem.sample.composeapp.generated.resources.component_tabs_desc
import aedesignsystem.sample.composeapp.generated.resources.component_navigation_bar_name
import aedesignsystem.sample.composeapp.generated.resources.component_navigation_bar_desc
import aedesignsystem.sample.composeapp.generated.resources.component_top_app_bar_name
import aedesignsystem.sample.composeapp.generated.resources.component_top_app_bar_desc

import com.ae.designsystem.sample.docs.pages.*

object ComponentRegistry {
    val entries: List<ComponentEntry> =
        listOf(
            // ─── Layout ──────────────────────────────
            ComponentEntry(
                id = "card",
                rawName = "Card",
                rawDescription = "A container with border, shadow, and structured sections.",
                nameRes = Res.string.component_card_name,
                descriptionRes = Res.string.component_card_desc,
                category = ComponentCategory.Layout,
                content = { CardDoc() },
            ),
            ComponentEntry(
                id = "divider",
                rawName = "Divider",
                rawDescription = "A horizontal or vertical divider line.",
                nameRes = Res.string.component_separator_name,
                descriptionRes = Res.string.component_separator_desc,
                category = ComponentCategory.Layout,
                content = { DividerDoc() },
            ),
            ComponentEntry(
                id = "surface",
                rawName = "Surface",
                rawDescription = "Core stylized layout surface blocks.",
                nameRes = Res.string.component_scaffold_name,
                descriptionRes = Res.string.component_scaffold_desc,
                category = ComponentCategory.Layout,
                content = { SurfaceDoc() },
            ),
            // ─── Forms ───────────────────────────────
            ComponentEntry(
                id = "button",
                rawName = "Button",
                rawDescription = "Core button component — variant, size, and enabled states.",
                nameRes = Res.string.component_button_name,
                descriptionRes = Res.string.component_button_desc,
                category = ComponentCategory.Forms,
                content = { ButtonDoc() },
            ),
            ComponentEntry(
                id = "checkbox",
                rawName = "Checkbox",
                rawDescription = "Standard binary toggle control.",
                nameRes = Res.string.component_checkbox_name,
                descriptionRes = Res.string.component_checkbox_desc,
                category = ComponentCategory.Forms,
                content = { CheckboxDoc() },
            ),
            ComponentEntry(
                id = "radio",
                rawName = "RadioButton",
                rawDescription = "Mutually exclusive selection button control.",
                nameRes = Res.string.component_radio_name,
                descriptionRes = Res.string.component_radio_desc,
                category = ComponentCategory.Forms,
                content = { RadioDoc() },
            ),
            ComponentEntry(
                id = "switch",
                rawName = "Switch",
                rawDescription = "Visual toggle switch control.",
                nameRes = Res.string.component_toggle_name,
                descriptionRes = Res.string.component_toggle_desc,
                category = ComponentCategory.Forms,
                content = { SwitchDoc() },
            ),
            ComponentEntry(
                id = "slider",
                rawName = "Slider",
                rawDescription = "Numerical range selection slider.",
                nameRes = Res.string.component_slider_name,
                descriptionRes = Res.string.component_slider_desc,
                category = ComponentCategory.Forms,
                content = { SliderDoc() },
            ),
            ComponentEntry(
                id = "textfield",
                rawName = "TextField",
                rawDescription = "Standard text inputs with custom border and state highlights.",
                nameRes = Res.string.component_input_name,
                descriptionRes = Res.string.component_input_desc,
                category = ComponentCategory.Forms,
                content = { TextFieldDoc() },
            ),
            // ─── Data Display ────────────────────────
            ComponentEntry(
                id = "badge",
                rawName = "Badge",
                rawDescription = "Small labeled indicator for counts, status, and tags.",
                nameRes = Res.string.component_badge_name,
                descriptionRes = Res.string.component_badge_desc,
                category = ComponentCategory.DataDisplay,
                content = { BadgeDoc() },
            ),
            ComponentEntry(
                id = "avatar",
                rawName = "Avatar",
                rawDescription = "Circular user identity fallback circle.",
                nameRes = Res.string.component_avatar_name,
                descriptionRes = Res.string.component_avatar_desc,
                category = ComponentCategory.DataDisplay,
                content = { AvatarDoc() },
            ),
            ComponentEntry(
                id = "text",
                rawName = "Text",
                rawDescription = "Typography component applying project styling presets.",
                nameRes = Res.string.component_text_name,
                descriptionRes = Res.string.component_text_desc,
                category = ComponentCategory.DataDisplay,
                content = { TextDoc() },
            ),
            ComponentEntry(
                id = "chip",
                rawName = "Chip",
                rawDescription = "Compact interactive filter tags.",
                nameRes = Res.string.component_badge_name,
                descriptionRes = Res.string.component_badge_desc,
                category = ComponentCategory.DataDisplay,
                content = { ChipDoc() },
            ),
            // ─── Feedback ────────────────────────────
            ComponentEntry(
                id = "progress",
                rawName = "Progress",
                rawDescription = "Linear and circular determinate and indeterminate progress bars.",
                nameRes = Res.string.component_progress_name,
                descriptionRes = Res.string.component_progress_desc,
                category = ComponentCategory.Feedback,
                content = { ProgressDoc() },
            ),
            ComponentEntry(
                id = "skeleton",
                rawName = "Skeleton",
                rawDescription = "Shimmering loading block presets.",
                nameRes = Res.string.component_skeleton_name,
                descriptionRes = Res.string.component_skeleton_desc,
                category = ComponentCategory.Feedback,
                content = { SkeletonDoc() },
            ),
            ComponentEntry(
                id = "snackbar",
                rawName = "Snackbar",
                rawDescription = "Popup alert feedback banners.",
                nameRes = Res.string.component_toast_name,
                descriptionRes = Res.string.component_toast_desc,
                category = ComponentCategory.Feedback,
                content = { SnackbarDoc() },
            ),
            // ─── Overlays ────────────────────────────
            ComponentEntry(
                id = "dialog",
                rawName = "Dialog",
                rawDescription = "Modal overlay dialogue boxes.",
                nameRes = Res.string.component_dialog_name,
                descriptionRes = Res.string.component_dialog_desc,
                category = ComponentCategory.Overlays,
                content = { DialogDoc() },
            ),
            ComponentEntry(
                id = "bottomsheet",
                rawName = "BottomSheet",
                rawDescription = "Slide-up bottom overlay sheet layouts.",
                nameRes = Res.string.component_sheet_name,
                descriptionRes = Res.string.component_sheet_desc,
                category = ComponentCategory.Overlays,
                content = { BottomSheetDoc() },
            ),
            ComponentEntry(
                id = "tooltip",
                rawName = "Tooltip",
                rawDescription = "Hover informational tooltips.",
                nameRes = Res.string.component_tooltip_name,
                descriptionRes = Res.string.component_tooltip_desc,
                category = ComponentCategory.Overlays,
                content = { TooltipDoc() },
            ),
            ComponentEntry(
                id = "dropdown",
                rawName = "DropdownMenu",
                rawDescription = "Popups showing selectable menu actions.",
                nameRes = Res.string.component_dropdown_menu_name,
                descriptionRes = Res.string.component_dropdown_menu_desc,
                category = ComponentCategory.Overlays,
                content = { DropdownMenuDoc() },
            ),
            // ─── Navigation ──────────────────────────
            ComponentEntry(
                id = "tabs",
                rawName = "Tabs",
                rawDescription = "Horizontal navigation tabs.",
                nameRes = Res.string.component_tabs_name,
                descriptionRes = Res.string.component_tabs_desc,
                category = ComponentCategory.Navigation,
                content = { TabsDoc() },
            ),
            ComponentEntry(
                id = "navigationbar",
                rawName = "NavigationBar",
                rawDescription = "Bottom destination navigation bar layouts.",
                nameRes = Res.string.component_navigation_bar_name,
                descriptionRes = Res.string.component_navigation_bar_desc,
                category = ComponentCategory.Navigation,
                content = { NavigationBarDoc() },
            ),
            ComponentEntry(
                id = "topappbar",
                rawName = "TopAppBar",
                rawDescription = "Header bar navigation layouts.",
                nameRes = Res.string.component_top_app_bar_name,
                descriptionRes = Res.string.component_top_app_bar_desc,
                category = ComponentCategory.Navigation,
                content = { TopAppBarDoc() },
            ),
        )

    fun findById(id: String): ComponentEntry? = entries.find { it.id == id }

    fun groupedByCategory(): Map<ComponentCategory, List<ComponentEntry>> =
        ComponentCategory.entries
            .associateWith { cat ->
                entries.filter { it.category == cat }
            }.filterValues { it.isNotEmpty() }
}
