<div align="center">
<br/>
<img src="https://img.shields.io/badge/AE-DesignSystem-white?style=for-the-badge&labelColor=18181b&color=fafafa" height="40"/>
<br/>
<br/>

# AEDesignSystem

**A zero-Material3, token-driven design system for Compose Multiplatform.**

*Beautiful, custom-crafted component primitives. Zero Material3. Full ownership.*

<br/>

<p>
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-Multiplatform-a503fc?logo=kotlin&logoColor=white&style=for-the-badge"/>
  <img alt="Compose Multiplatform" src="https://img.shields.io/static/v1?style=for-the-badge&message=Compose+Multiplatform&color=4285F4&logo=Jetpack+Compose&logoColor=FFFFFF&label="/>
  <img alt="No Material3" src="https://img.shields.io/badge/No%20Material3-Foundation%20Only-18181b?style=for-the-badge"/>
</p>
<p>
  <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.abdo-essam/ae-foundation?style=for-the-badge&logo=apachemaven&logoColor=white&label=Maven%20Central&color=a503fc"/>
  <img alt="License" src="https://img.shields.io/badge/license-Apache%202.0-blue?style=for-the-badge"/>
</p>

<br/>
</div>

---

## Philosophy

Every Compose Multiplatform developer faces a compromise: fight Material3's rigid design guidelines, or build every component from scratch. 

**AEDesignSystem** is the solution:
- **Zero Material3** — Built entirely on `compose.foundation`, `compose.ui`, and `compose.animation`.
- **Token-driven Design** — Every spacing, color, radius, elevation, and motion value is a strictly typed token.
- **Slot-based API** — Composables use trailing lambdas for custom inner content, offering full layout flexibility.
- **Decoupled Swappable Icons** — Semantic `AEIconToken` is completely decoupled from vector data.
- **Accessibly Built** — Focus rings, semantic roles, and content descriptions are built-in defaults.
- **Custom Fonts** — Includes the **Barlow** font preset pre-packaged in Compose resources and loaded at runtime.

---

## Quick Start

### 1. Add Dependencies

Add the foundation and component libraries to your `commonMain` dependencies:

```kotlin
// build.gradle.kts
dependencies {
    implementation("io.github.abdo-essam:ae-foundation:0.1.0") // Token system and core engine
    implementation("io.github.abdo-essam:ae-components:0.1.0") // Full component catalog
}
```

### 2. Wrap Your App

Initialize the theme context in your entry point:

```kotlin
@Composable
fun App() {
    AETheme(
        palette = AEPalette.Zinc,       // Zinc, Slate, Stone, Neutral
        accent  = AEAccent.Blue,        // Blue, Violet, Rose, Emerald, Amber, Cyan
        preset  = AEStylePreset.Default, // Default, Sharp, Soft, Compact, Expressive
        darkTheme = isSystemInDarkTheme(),
    ) {
        // App content goes here
    }
}
```

### 3. Use Components

```kotlin
// Button with leading icon
AEButton(onClick = { }) {
    AEIcon(AEIcons.Heart, tint = AETheme.colors.textOnAccent, size = 16.dp)
    Spacer(Modifier.width(8.dp))
    AEText("Like", color = AETheme.colors.textOnAccent)
}

// Text Field with validation states
var text by remember { mutableStateOf("") }
AETextField(
    value = text,
    onValueChange = { text = it },
    label = "Email",
    leadingIcon = AEIcons.User,
    state = AETextFieldState.Success,
    supportingText = "Looks good!",
)

// Clean Card with slot API
AECard(
    header = { AEText("Header Title", style = AETheme.typography.headingSmall) },
    footer = { AEButton(onClick = {}) { AEText("Action Button") } },
) {
    AEText("Body content goes here.", color = AETheme.colors.textSecondary)
}
```

---

## Theme System

### Palettes
AEDesignSystem features 4 neutral base palettes, each supplying complete Light and Dark variants:

| Palette | Visual Character |
|:---|:---|
| `AEPalette.Zinc` | Clean, modern grayscale palette |
| `AEPalette.Slate` | Grays with cool, subtle blue undertones |
| `AEPalette.Stone` | Warm, organic, earthy neutral grays |
| `AEPalette.Neutral` | True, mathematically neutral scale grays |

### Accents
6 dynamic accent color schemes for brand identity:
*   `AEAccent.Blue` (Default)
*   `AEAccent.Violet`
*   `AEAccent.Rose`
*   `AEAccent.Emerald`
*   `AEAccent.Amber`
*   `AEAccent.Cyan`

### Spatial Style Presets
Change spacing, corner radius, and motion characteristics with a single preset:

| Preset | Spatial Feel |
|:---|:---|
| `Default` | Balanced proportions (8dp base unit) |
| `Sharp` | Minimal radius, dense compact spacing |
| `Soft` | High border-radius values, generous margins |
| `Compact` | Extremely space-efficient for information-heavy apps |
| `Expressive` | Roomy layout, bold curves |

---

## Token Access API

Access styling tokens programmatically inside any composable:

```kotlin
@Composable
fun MyCustomComponent() {
    val colors    = AETheme.colors       // 31 semantic color tokens
    val typo      = AETheme.typography   // Typography scale (12 type styles)
    val spacing   = AETheme.spacing      // Margins and paddings
    val radius    = AETheme.radius       // Corner shape values
    val elevation = AETheme.elevation    // Shadows and layer depths
    val motion    = AETheme.motion       // Animations and curves
}
```

---

## Component Catalog

### Tier 1 — Core Primitives
*   `AEButton` — 5 variants × 3 sizes, trailing/leading icon slots.
*   `AESurface` — Elevation-aware, shape-styled containers.
*   `AECard` — Modular structure with `header`, `content`, and `footer` slots.
*   `AEText` — Layout typography wrapper mapped to the theme type-scale.
*   `AEDivider` — Clean separators (horizontal or vertical).

### Tier 2 — Form Controls & Indicators
*   `AETextField` — Animated border focus, placeholder support, helper text.
*   `AECheckbox` — Custom checked/unchecked states with semantic accessibility.
*   `AESwitch` — Smooth sliding track switch.
*   `AERadioButton` — Dot selection with animation.
*   `AESlider` — Drag & tap gesture tracks with instant feedback.
*   `AEBadge` — 6 preset variants (default, success, info, warning, destructive, outline).
*   `AEChip` — Clickable chip with selectable status indicators.
*   `AELinearProgress` / `AECircularProgress` — Progress indicators.
*   `AESkeleton` — Shimmer-effect skeleton loader.
*   `AEDialog` — Popup container for modal presentation.
*   `AETooltip` — Animated labels matching 4 cardinal directions.

### Tier 3 — Complex Layouts & Navigation
*   `AEAvatar` — Initials or remote image placeholders with status badges.
*   `AEAvatarGroup` — Clean stack representation with numeric overflow counters.
*   `AETabs` — Navigation tab lists with horizontal track slider animations.
*   `AEBottomSheet` — Sheet panel with swipe-down dismissal and navigation inset support.

### Tier 4 — Layout Shells & Overlay Menus
*   `AETopAppBar` — Header shell matching status-bar heights.
*   `AENavigationBar` — Footer navigation shell with interactive pill highlights.
*   `AESnackbarHost` / `AESnackbarState` — Coroutine-managed notifications.
*   `AEDropdownMenu` — Dynamic context popups with icon and divider separators.

---

## Swappable Icon System

Icons are represented as semantic tokens. You can swap entire vector icon implementations for an entire subtree without modifying the component code:

```kotlin
// Render an icon token
AEIcon(AEIcons.Heart, tint = AETheme.colors.accent, size = 20.dp)

// Swap the mapping provider for local branches
ProvideIconPack(MyCustomIconPack) {
    AEIcon(AEIcons.Heart) // Resolves using MyCustomIconPack paths!
}
```

---

## Interactive Sample Catalog

Run the interactive desktop sandbox to preview components and test themes in real-time:

```bash
./gradlew :sample:composeApp:run
```

---

## Maven Central Publishing

Configure local variables in your `~/.gradle/gradle.properties`:

```properties
signingInMemoryKeyId=<GPG key ID>
signingInMemoryKey=<PGP private key>
signingInMemoryKeyPassword=<Passphrase>
mavenCentralUsername=<Sonatype Central Username>
mavenCentralPassword=<Sonatype Central Password>
```

Publish all Multiplatform artifacts:

```bash
./gradlew publishAllPublicationsToMavenCentralRepository
```

---

## License

```
Copyright 2026 Abdo Essam

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0
```
