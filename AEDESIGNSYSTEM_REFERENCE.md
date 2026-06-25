# AEDesignSystem API Reference

Generated from KDoc + function signatures in source code.

## components

### AEAvatar.kt

#### AEAvatar

Avatar — circular user identity component with fallback initials or icon.

```kotlin
@Composable fun AEAvatar(modifier: Modifier = Modifier, initials: String? = null, size: AEAvatarSize = AEAvatarSize.Medium, backgroundColor: Color = AETheme.colors.accentMuted, contentColor: Color = AETheme.colors.accent, borderColor: Color? = null, showOnlineStatus: Boolean = false, isOnline: Boolean = true, shape: Shape = CircleShape,)
```

Parameters:
- `modifier: Modifier = Modifier`
- `initials: String? = null`
- `size: AEAvatarSize = AEAvatarSize.Medium`
- `backgroundColor: Color = AETheme.colors.accentMuted`
- `contentColor: Color = AETheme.colors.accent`
- `borderColor: Color? = null`
- `showOnlineStatus: Boolean = false`
- `isOnline: Boolean = true`
- `shape: Shape = CircleShape`

#### AEAvatarGroup

Avatar group — overlapping stack of [AEAvatar]s.

```kotlin
@Composable fun AEAvatarGroup(avatars: List<String>, modifier: Modifier = Modifier, size: AEAvatarSize = AEAvatarSize.Small, maxVisible: Int = 4,)
```

Parameters:
- `avatars: List<String>`
- `modifier: Modifier = Modifier`
- `size: AEAvatarSize = AEAvatarSize.Small`
- `maxVisible: Int = 4`

### AEBadge.kt

#### AEBadge

Badge — small labeled indicator for counts, status, and tags.

```kotlin
@Composable fun AEBadge(label: String, modifier: Modifier = Modifier, variant: AEBadgeVariant = AEBadgeVariant.Default,)
```

Parameters:
- `label: String`
- `modifier: Modifier = Modifier`
- `variant: AEBadgeVariant = AEBadgeVariant.Default`

### AEBottomSheet.kt

#### AEBottomSheet

Bottom sheet — slides up from the bottom with a scrim overlay.

```kotlin
@Composable fun AEBottomSheet(visible: Boolean, onDismiss: () -> Unit, modifier: Modifier = Modifier, title: String? = null, dismissible: Boolean = true, content: @Composable ColumnScope.() -> Unit,)
```

Parameters:
- `visible: Boolean` — Whether the sheet is shown.
- `onDismiss: () -> Unit` — Called when the user taps the scrim or swipes down.
- `modifier: Modifier = Modifier`
- `title: String? = null` — Optional title string in the sheet header.
- `dismissible: Boolean = true` — Whether the scrim tap dismisses the sheet.
- `content: ColumnScope.() -> Unit` — Sheet body content slot.

### AEButton.kt

#### AEButton

Core button component — slot-based, token-driven, zero Material3.

```kotlin
@Composable fun AEButton(onClick: () -> Unit, modifier: Modifier = Modifier, variant: AEButtonVariant = AEButtonVariant.Filled, size: AEButtonSize = AEButtonSize.Medium, enabled: Boolean = true, interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }, content: @Composable RowScope.() -> Unit,)
```

Parameters:
- `onClick: () -> Unit` — Callback when clicked.
- `modifier: Modifier = Modifier` — Modifier for the button root.
- `variant: AEButtonVariant = AEButtonVariant.Filled` — Visual variant ([AEButtonVariant.Filled] by default).
- `size: AEButtonSize = AEButtonSize.Medium` — Size preset ([AEButtonSize.Medium] by default).
- `enabled: Boolean = true` — Whether the button is interactive.
- `interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }` — Interaction source for state tracking.
- `content: RowScope.() -> Unit` — Slot-based content — typically text, icons, or both.

### AECard.kt

#### AECard

Card component with 3 optional slots: header, content, footer.

```kotlin
@Composable fun AECard(modifier: Modifier = Modifier, color: Color = AETheme.colors.surface, shape: Shape = RoundedCornerShape(AETheme.radius.lg), elevation: Dp = AETheme.elevation.sm, header: (@Composable () -> Unit)? = null, footer: (@Composable () -> Unit)? = null, content: @Composable ColumnScope.() -> Unit,)
```

Parameters:
- `modifier: Modifier = Modifier` — Modifier for the card root.
- `color: Color = AETheme.colors.surface` — Background color.
- `shape: Shape = RoundedCornerShape(AETheme.radius.lg)` — Corner shape.
- `elevation: Dp = AETheme.elevation.sm` — Shadow elevation.
- `header: (() -> Unit)? = null` — Optional header slot, rendered above content.
- `footer: (() -> Unit)? = null` — Optional footer slot, rendered below content.
- `content: ColumnScope.() -> Unit` — Main content slot.

### AECheckbox.kt

#### AECheckbox

Checkbox component — token-driven, zero Material3.

```kotlin
@Composable fun AECheckbox(checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier, label: String? = null, enabled: Boolean = true, interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },)
```

Parameters:
- `checked: Boolean`
- `onCheckedChange: (Boolean) -> Unit`
- `modifier: Modifier = Modifier`
- `label: String? = null`
- `enabled: Boolean = true`
- `interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }`

### AEChip.kt

#### AEChip

Chip — compact interactive tag with optional leading/trailing icon.

```kotlin
@Composable fun AEChip(label: String, modifier: Modifier = Modifier, selected: Boolean = false, onClick: (() -> Unit)? = null, leadingIcon: AEIconToken? = null, trailingIcon: AEIconToken? = null, enabled: Boolean = true, interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },)
```

Parameters:
- `label: String`
- `modifier: Modifier = Modifier`
- `selected: Boolean = false`
- `onClick: (() -> Unit)? = null`
- `leadingIcon: AEIconToken? = null`
- `trailingIcon: AEIconToken? = null`
- `enabled: Boolean = true`
- `interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }`

### AEDialog.kt

#### AEDialog

Modal dialog — slot-based with header, content, and action slots.

```kotlin
@Composable fun AEDialog(visible: Boolean, onDismiss: () -> Unit, modifier: Modifier = Modifier, title: String? = null, dismissible: Boolean = true, confirmButton: (@Composable () -> Unit)? = null, dismissButton: (@Composable () -> Unit)? = null, content: @Composable ColumnScope.() -> Unit,)
```

Parameters:
- `visible: Boolean` — Whether the dialog is shown.
- `onDismiss: () -> Unit` — Called when the user dismisses (backdrop tap or back).
- `modifier: Modifier = Modifier`
- `title: String? = null` — Optional title string rendered in the header.
- `dismissible: Boolean = true` — Whether backdrop tap / back gesture dismisses the dialog.
- `confirmButton: (() -> Unit)? = null` — Slot for the primary action button.
- `dismissButton: (() -> Unit)? = null` — Slot for the secondary/cancel button.
- `content: ColumnScope.() -> Unit` — Main body content slot.

### AEDivider.kt

#### AEDivider

Horizontal divider line using theme border color.

```kotlin
@Composable fun AEDivider(modifier: Modifier = Modifier, color: Color = AETheme.colors.border, thickness: Dp = 1.dp,)
```

Parameters:
- `modifier: Modifier = Modifier` — Modifier for the divider.
- `color: Color = AETheme.colors.border` — Divider color. Defaults to [AETheme.colors.border].
- `thickness: Dp = 1.dp` — Line thickness.

#### AEVerticalDivider

Vertical divider line using theme border color.

```kotlin
@Composable fun AEVerticalDivider(modifier: Modifier = Modifier, color: Color = AETheme.colors.border, thickness: Dp = 1.dp,)
```

Parameters:
- `modifier: Modifier = Modifier` — Modifier for the divider.
- `color: Color = AETheme.colors.border` — Divider color. Defaults to [AETheme.colors.border].
- `thickness: Dp = 1.dp` — Line thickness.

### AEDropdownMenu.kt

#### AEDropdownMenu

Dropdown menu — anchored popup with a list of [AEDropdownItem]s.

```kotlin
@Composable fun AEDropdownMenu(expanded: Boolean, onDismiss: () -> Unit, items: List<AEDropdownItem>, modifier: Modifier = Modifier,)
```

Parameters:
- `expanded: Boolean` — Whether the menu is shown.
- `onDismiss: () -> Unit` — Called when the user taps outside.
- `items: List<AEDropdownItem>` — List of menu items to display.
- `modifier: Modifier = Modifier`

### AENavigationBar.kt

#### AENavigationBar

Bottom navigation bar — up to 5 destinations with animated indicator pill.

```kotlin
@Composable fun AENavigationBar(items: List<AENavItem>, onItemSelected: (index: Int) -> Unit, modifier: Modifier = Modifier,)
```

Parameters:
- `items: List<AENavItem>`
- `onItemSelected: (index: Int) -> Unit`
- `modifier: Modifier = Modifier`

### AEProgress.kt

#### AECircularProgress

Circular progress indicator.

```kotlin
@Composable fun AECircularProgress(progress: Float? = null, modifier: Modifier = Modifier, size: Dp = 40.dp, strokeWidth: Dp = 4.dp, color: Color = AETheme.colors.accent, trackColor: Color = AETheme.colors.border,)
```

Parameters:
- `progress: Float? = null`
- `modifier: Modifier = Modifier`
- `size: Dp = 40.dp`
- `strokeWidth: Dp = 4.dp`
- `color: Color = AETheme.colors.accent`
- `trackColor: Color = AETheme.colors.border`

#### AELinearProgress

Linear progress indicator.

```kotlin
@Composable fun AELinearProgress(progress: Float? = null, modifier: Modifier = Modifier, color: Color = AETheme.colors.accent, trackColor: Color = AETheme.colors.border, height: Dp = 6.dp,)
```

Parameters:
- `progress: Float? = null`
- `modifier: Modifier = Modifier`
- `color: Color = AETheme.colors.accent`
- `trackColor: Color = AETheme.colors.border`
- `height: Dp = 6.dp`

### AERadioButton.kt

#### AERadioButton

Radio button — for use inside a mutually exclusive group.

```kotlin
@Composable fun AERadioButton(selected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier, label: String? = null, enabled: Boolean = true, interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },)
```

Parameters:
- `selected: Boolean`
- `onClick: () -> Unit`
- `modifier: Modifier = Modifier`
- `label: String? = null`
- `enabled: Boolean = true`
- `interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }`

### AESkeleton.kt

#### AESkeleton

Skeleton loading placeholder — shimmer animation on a rounded block.

```kotlin
@Composable fun AESkeleton(modifier: Modifier = Modifier, shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(AETheme.radius.md),)
```

Parameters:
- `modifier: Modifier = Modifier` — Size/layout of the skeleton block.
- `shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(AETheme.radius.md)` — Corner shape. Defaults to [AETheme.radius.md] rounded.

#### AESkeletonListItem

Pre-built skeleton row with avatar + text lines — common loading pattern.

```kotlin
@Composable fun AESkeletonListItem(modifier: Modifier = Modifier,)
```

Parameters:
- `modifier: Modifier = Modifier`

### AESlider.kt

#### AESlider

Horizontal slider — drag-based value input, zero Material3.

```kotlin
@Composable fun AESlider(value: Float, onValueChange: (Float) -> Unit, modifier: Modifier = Modifier, valueRange: ClosedFloatingPointRange<Float> = 0f..1f, enabled: Boolean = true,)
```

Parameters:
- `value: Float` — Current value in [valueRange].
- `onValueChange: (Float) -> Unit` — Callback with updated value.
- `modifier: Modifier = Modifier`
- `valueRange: ClosedFloatingPointRange<Float> = 0f..1f` — Allowed value range. Defaults to 0f..1f.
- `enabled: Boolean = true` — Whether the slider is interactive.

### AESnackbar.kt

#### AESnackbarHost

Host that renders the snackbar when [state] has a visible message.

```kotlin
@Composable fun AESnackbarHost(state: AESnackbarState, modifier: Modifier = Modifier,)
```

Parameters:
- `state: AESnackbarState`
- `modifier: Modifier = Modifier`

#### rememberSnackbarState

Creates and remembers an [AESnackbarState].

```kotlin
@Composable fun rememberSnackbarState()
```

### AESurface.kt

#### AESurface

Base surface component — a styled container using theme tokens.

```kotlin
@Composable fun AESurface(modifier: Modifier = Modifier, color: Color = AETheme.colors.surface, shape: Shape = RoundedCornerShape(AETheme.radius.md), elevation: Dp = AETheme.elevation.none, content: @Composable BoxScope.() -> Unit,)
```

Parameters:
- `modifier: Modifier = Modifier` — Modifier for the surface root.
- `color: Color = AETheme.colors.surface` — Background color. Defaults to [AETheme.colors.surface].
- `shape: Shape = RoundedCornerShape(AETheme.radius.md)` — Corner shape. Defaults to [AETheme.radius.md] rounded corners.
- `elevation: Dp = AETheme.elevation.none` — Shadow elevation. Defaults to no elevation.
- `content: BoxScope.() -> Unit` — Composable content slot.

### AESwitch.kt

#### AESwitch

Toggle switch — animated thumb slide, token-driven.

```kotlin
@Composable fun AESwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier, label: String? = null, enabled: Boolean = true, interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },)
```

Parameters:
- `checked: Boolean`
- `onCheckedChange: (Boolean) -> Unit`
- `modifier: Modifier = Modifier`
- `label: String? = null`
- `enabled: Boolean = true`
- `interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }`

### AETabs.kt

#### AETabs

Tabs — horizontal navigation with animated indicator.

```kotlin
@Composable fun AETabs(tabs: List<String>, selectedIndex: Int, onTabSelected: (Int) -> Unit, modifier: Modifier = Modifier,)
```

Parameters:
- `tabs: List<String>` — List of tab labels.
- `selectedIndex: Int` — Currently selected tab index.
- `onTabSelected: (Int) -> Unit` — Callback with new index.
- `modifier: Modifier = Modifier`

### AEText.kt

#### AEText

Text component applying AEDesignSystem typography tokens.

```kotlin
@Composable fun AEText(text: String, modifier: Modifier = Modifier, style: TextStyle = AETheme.typography.bodyMedium, color: Color = AETheme.colors.textPrimary, maxLines: Int = Int.MAX_VALUE, overflow: TextOverflow = TextOverflow.Clip,)
```

Parameters:
- `text: String` — The text string to render.
- `modifier: Modifier = Modifier` — Modifier for the text element.
- `style: TextStyle = AETheme.typography.bodyMedium` — Typography style. Defaults to [AETheme.typography.bodyMedium].
- `color: Color = AETheme.colors.textPrimary` — Text color override. If provided, merges with [style].
- `maxLines: Int = Int.MAX_VALUE` — Maximum lines before truncation. [Int.MAX_VALUE] for unlimited.
- `overflow: TextOverflow = TextOverflow.Clip` — How to handle text overflow.

### AETextField.kt

#### AETextField

Single-line text input field — slot-based, zero Material3.

```kotlin
@Composable fun AETextField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier, label: String? = null, placeholder: String? = null, supportingText: String? = null, leadingIcon: AEIconToken? = null, trailingIcon: AEIconToken? = null, trailingContent: (@Composable () -> Unit)? = null, state: AETextFieldState = AETextFieldState.Default, enabled: Boolean = true, singleLine: Boolean = true, maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE, keyboardOptions: KeyboardOptions = KeyboardOptions.Default, keyboardActions: KeyboardActions = KeyboardActions.Default, visualTransformation: VisualTransformation = VisualTransformation.None, interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },)
```

Parameters:
- `value: String`
- `onValueChange: (String) -> Unit`
- `modifier: Modifier = Modifier`
- `label: String? = null`
- `placeholder: String? = null`
- `supportingText: String? = null`
- `leadingIcon: AEIconToken? = null`
- `trailingIcon: AEIconToken? = null`
- `trailingContent: (() -> Unit)? = null`
- `state: AETextFieldState = AETextFieldState.Default`
- `enabled: Boolean = true`
- `singleLine: Boolean = true`
- `maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE`
- `keyboardOptions: KeyboardOptions = KeyboardOptions.Default`
- `keyboardActions: KeyboardActions = KeyboardActions.Default`
- `visualTransformation: VisualTransformation = VisualTransformation.None`
- `interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }`

### AETooltip.kt

#### AETooltip

Tooltip — displays a contextual label above/below/beside its content.

```kotlin
@Composable fun AETooltip(tooltip: String, modifier: Modifier = Modifier, position: AETooltipPosition = AETooltipPosition.Top, content: @Composable () -> Unit,)
```

Parameters:
- `tooltip: String` — The tooltip label string.
- `modifier: Modifier = Modifier`
- `position: AETooltipPosition = AETooltipPosition.Top` — Where the tooltip appears relative to [content].
- `content: () -> Unit` — The anchor composable that triggers the tooltip on hover.

### AETopAppBar.kt

#### AETopAppBar

Top app bar — slot-based with navigation icon, title, and actions row.

```kotlin
@Composable fun AETopAppBar(title: String, modifier: Modifier = Modifier, navigationIcon: AEIconToken? = null, onNavigationClick: (() -> Unit)? = null, actions: (@Composable RowScope.() -> Unit)? = null, elevated: Boolean = false,)
```

Parameters:
- `title: String` — Title string shown in the center/start of the bar.
- `modifier: Modifier = Modifier`
- `navigationIcon: AEIconToken? = null` — Optional leading icon token (e.g. back arrow).
- `onNavigationClick: (() -> Unit)? = null` — Called when [navigationIcon] is tapped.
- `actions: (RowScope.() -> Unit)? = null` — Slot for trailing action buttons.
- `elevated: Boolean = false` — Whether to draw a shadow beneath the bar.

## foundation

### AEAccessibility.kt

#### rememberAutoFocusRequester

Creates a [FocusRequester] that automatically requests focus when the composable enters composition.

```kotlin
@Composable fun rememberAutoFocusRequester()
```

### AEIcon.kt

#### AEIcon

Renders an icon from the current [AEIconPack].

```kotlin
@Composable fun AEIcon(token: AEIconToken, modifier: Modifier = Modifier, contentDescription: String? = null, tint: Color = AETheme.colors.textPrimary, size: Dp = 24.dp,)
```

Parameters:
- `token: AEIconToken` — The semantic icon token to render.
- `modifier: Modifier = Modifier` — Modifier for the icon container.
- `contentDescription: String? = null` — Accessibility description. Pass `null` for decorative icons.
- `tint: Color = AETheme.colors.textPrimary` — Color tint applied to the icon. Defaults to [AETheme.colors.textPrimary].
- `size: Dp = 24.dp` — Icon size in dp. Defaults to 24dp.

### AETheme.kt

#### AETheme

AEDesignSystem theme entry point.

```kotlin
@Composable fun AETheme(palette: AEPalette = AEPalette.Zinc, accent: AEAccent = AEAccent.Blue, preset: AEStylePreset = AEStylePreset.Default, darkTheme: Boolean = isSystemInDarkTheme(), iconPack: AEIconPack = AEIconPack.Default, colors: AEColors = palette.toColors(accent, darkTheme), typography: AETypography = AETypography.default(AEFontFamily.barlow()), spacing: AESpacing = preset.toSpacing(), radius: AERadius = preset.toRadius(), elevation: AEElevation = AEElevation(), motion: AEMotion = preset.toMotion(), content: @Composable () -> Unit,)
```

Parameters:
- `palette: AEPalette = AEPalette.Zinc`
- `accent: AEAccent = AEAccent.Blue`
- `preset: AEStylePreset = AEStylePreset.Default`
- `darkTheme: Boolean = isSystemInDarkTheme()`
- `iconPack: AEIconPack = AEIconPack.Default`
- `colors: AEColors = palette.toColors(accent, darkTheme)`
- `typography: AETypography = AETypography.default(AEFontFamily.barlow())`
- `spacing: AESpacing = preset.toSpacing()`
- `radius: AERadius = preset.toRadius()`
- `elevation: AEElevation = AEElevation()`
- `motion: AEMotion = preset.toMotion()`
- `content: () -> Unit`

### ProvideIconPack.kt

#### ProvideIconPack

Scoped icon pack override.

```kotlin
@Composable fun ProvideIconPack(iconPack: AEIconPack, content: @Composable () -> Unit,)
```

Parameters:
- `iconPack: AEIconPack`
- `content: () -> Unit`

