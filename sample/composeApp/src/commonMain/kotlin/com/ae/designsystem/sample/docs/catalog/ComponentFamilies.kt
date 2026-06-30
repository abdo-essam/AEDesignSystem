package com.ae.designsystem.sample.docs.catalog

/**
 * Pre-defined component families for the "See also" chip row.
 *
 * Each list contains (componentId, displayName) pairs.
 * Components in the same family share related functionality
 * and are displayed together in [ComponentFamily].
 */
object ComponentFamilies {
    /** Button family */
    val BUTTONS: List<Pair<String, String>> =
        listOf(
            "button" to "Button",
        )

    /** Dialogs & Sheets */
    val DIALOGS: List<Pair<String, String>> =
        listOf(
            "dialog" to "Dialog",
            "bottomsheet" to "BottomSheet",
        )

    /** Popups */
    val POPUPS: List<Pair<String, String>> =
        listOf(
            "tooltip" to "Tooltip",
            "dropdown" to "Dropdown Menu",
        )

    /** Inputs */
    val TEXT_INPUTS: List<Pair<String, String>> =
        listOf(
            "textfield" to "TextField",
        )

    /** Selection Controls */
    val SELECTION: List<Pair<String, String>> =
        listOf(
            "checkbox" to "Checkbox",
            "radio" to "RadioButton",
            "switch" to "Switch",
        )

    /** Navigation components */
    val NAVIGATION: List<Pair<String, String>> =
        listOf(
            "tabs" to "Tabs",
            "navigationbar" to "NavigationBar",
            "topappbar" to "TopAppBar",
        )

    /** Loading indicators */
    val LOADING: List<Pair<String, String>> =
        listOf(
            "progress" to "Progress",
            "skeleton" to "Skeleton",
        )

    /** Messaging & Feedback */
    val MESSAGING: List<Pair<String, String>> =
        listOf(
            "snackbar" to "Snackbar",
            "badge" to "Badge",
        )
}
