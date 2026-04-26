# AEDesignSystem

> A zero-Material3, token-driven design system for **Compose Multiplatform**.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.abdo-essam/ae-foundation)](https://central.sonatype.com/search?q=io.github.abdo-essam)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue)](LICENSE)
[![Kotlin](https://img.shields.io/badge/kotlin-2.1.20-purple)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.7.3-blue)](https://www.jetbrains.com/lp/compose-multiplatform/)

---

## Platform Support

| Platform | Status |
|---|---|
| Android | ✅ |
| iOS (x64 / Arm64 / SimulatorArm64) | ✅ |
| Desktop (JVM) | ✅ |
| Web (wasmJs) | ✅ |
| Web (js) | ✅ |

---

## Philosophy

- **Zero Material3** — built entirely on `compose.foundation` + `compose.ui` + `compose.animation`
- **Token-driven** — every visual property (color, spacing, radius, elevation, motion) is a typed token
- **Slot-based API** — composable trailing lambdas for maximum flexibility
- **Swappable icon packs** — semantic `AEIconToken` decoupled from vector data
- **Accessible** — semantic roles, focus rings, content descriptions throughout
- **Font: Barlow** — bundled in compose resources, loaded at runtime

---

## Quick Start

### 1. Add dependency

```kotlin
// build.gradle.kts
commonMain.dependencies {
    implementation("io.github.abdo-essam:ae-foundation:0.1.0")
    implementation("io.github.abdo-essam:ae-components:0.1.0")
}
```

### 2. Wrap your app

```kotlin
@Composable
fun App() {
    AETheme(
        palette = AEPalette.Zinc,       // neutral grayscale
        accent  = AEAccent.Blue,        // brand color
        preset  = AEStylePreset.Default,
        darkTheme = isSystemInDarkTheme(),
    ) {
        // your content
    }
}
```

### 3. Use components

```kotlin
// Button
AEButton(onClick = { }) {
    AEIcon(AEIcons.Heart, tint = AETheme.colors.textOnAccent, size = 16.dp)
    Spacer(Modifier.width(8.dp))
    AEText("Like", color = AETheme.colors.textOnAccent)
}

// Text field
var text by remember { mutableStateOf("") }
AETextField(
    value = text,
    onValueChange = { text = it },
    label = "Email",
    leadingIcon = AEIcons.User,
    state = AETextFieldState.Success,
    supportingText = "Looks good!",
)

// Card with slots
AECard(
    header = { AEText("Title", style = AETheme.typography.headingSmall) },
    footer = { AEButton(onClick = {}) { AEText("Action") } },
) {
    AEText("Card body content", color = AETheme.colors.textSecondary)
}
```

---

## Theme System

### Palettes

4 neutral grayscale palettes — each provides full light + dark `AEColors`:

| Palette | Character |
|---------|-----------|
| `AEPalette.Zinc` | Pure, clean neutral grays |
| `AEPalette.Slate` | Cool blue-tinted neutrals |
| `AEPalette.Stone` | Warm earth-toned neutrals |
| `AEPalette.Neutral` | True neutral gray scale |

### Accents

6 brand color families:

```kotlin
AEAccent.Blue   // default
AEAccent.Violet
AEAccent.Rose
AEAccent.Emerald
AEAccent.Amber
AEAccent.Cyan
```

### Style Presets

Spatial personalities — adjust spacing, radius, and motion together:

| Preset | Feel |
|--------|------|
| `Default` | Balanced — 8dp base unit |
| `Sharp` | Tight radius, dense spacing |
| `Soft` | Large radius, generous spacing |
| `Compact` | Dense, space-efficient |
| `Expressive` | Roomy, bold radius |

### Full Override

```kotlin
AETheme(
    colors = myBrandColors,
    typography = AETypography.default(myFontFamily),
    spacing = AESpacing(base = 10.dp),
) { ... }
```

---

## Token Access

```kotlin
@Composable
fun MyComponent() {
    val colors    = AETheme.colors
    val typo      = AETheme.typography
    val spacing   = AETheme.spacing
    val radius    = AETheme.radius
    val elevation = AETheme.elevation
    val motion    = AETheme.motion
}
```

---

## Component Library

### Tier 1 — Primitives
| Component | Description |
|-----------|-------------|
| `AEButton` | 5 variants × 3 sizes, icon slots, disabled state |
| `AESurface` | Elevation-aware container |
| `AECard` | 3-slot (header / content / footer) |
| `AEText` | Typography scale aware |
| `AEDivider` | Horizontal / vertical rule |

### Tier 2 — Form & Feedback
| Component | Description |
|-----------|-------------|
| `AETextField` | Animated border, label, supporting text, icon slots |
| `AECheckbox` | Animated fill, check icon, `Role.Checkbox` |
| `AESwitch` | Sliding thumb animation |
| `AERadioButton` | Animated ring/dot, `Role.RadioButton` |
| `AESlider` | Drag + tap, animated fill |
| `AEBadge` | 6 variants (default/destructive/success/warning/info/outline) |
| `AEChip` | Selected state, icon slots |
| `AELinearProgress` | Determinate + indeterminate |
| `AECircularProgress` | Determinate + spinning arc |
| `AESkeleton` | Shimmer loading placeholder |
| `AEDialog` | Slot-based modal |
| `AETooltip` | Animated label in 4 positions |

### Tier 3 — Navigation & Layout
| Component | Description |
|-----------|-------------|
| `AEAvatar` | Initials / icon, online dot, 5 sizes |
| `AEAvatarGroup` | Overlapping stack with +N overflow |
| `AETabs` | Animated bottom indicator, `Role.Tab` |
| `AEBottomSheet` | Slide-up with scrim, drag handle, nav bar insets |

### Tier 4 — Scaffold & Navigation
| Component | Description |
|-----------|-------------|
| `AETopAppBar` | Status bar insets, nav icon, centered title, actions slot |
| `AENavigationBar` | Animated pill indicator, badge counts, nav bar insets, up to 5 items |
| `AESnackbarHost` + `AESnackbarState` | Hoistable coroutine-driven snackbar, 5 variants, action button |
| `AEDropdownMenu` | Animated expand/collapse popup, destructive items, icon slots |

---

## Icon System

Icons are **semantic tokens** — vector data is in swappable packs:

```kotlin
// Use a token
AEIcon(AEIcons.Heart, tint = AETheme.colors.accent, size = 20.dp)

// Swap the pack for a subtree
ProvideIconPack(MyCustomIconPack) {
    AEIcon(AEIcons.Heart) // draws from MyCustomIconPack
}

// Implement your own pack
class MyIconPack : AEIconPack {
    override fun resolve(token: AEIconToken): AEIconData = when (token.key) {
        "heart" -> AEIconData { /* custom path commands */ }
        else    -> AEDefaultIconPack.resolve(token)
    }
}
```

---

## Typography

All 12 type styles use **Barlow** by default (bundled TTF, 4 weights):

| Token | Size | Weight |
|-------|------|--------|
| `displayLarge` | 48sp | Bold |
| `displayMedium` | 36sp | Bold |
| `headingLarge` | 30sp | SemiBold |
| `headingMedium` | 24sp | SemiBold |
| `headingSmall` | 20sp | SemiBold |
| `bodyLarge` | 16sp | Normal |
| `bodyMedium` | 14sp | Normal |
| `bodySmall` | 12sp | Normal |
| `labelLarge` | 14sp | Medium |
| `labelMedium` | 12sp | Medium |
| `labelSmall` | 10sp | Medium |
| `code` | 13sp | Normal (monospace) |

---

## Sample Catalog App

Run the desktop catalog to explore every component interactively:

```bash
./gradlew :sample:composeApp:run
```

The catalog demonstrates live theme switching (palette, accent, preset, dark mode) for all components.

---

## Publishing (Maven Central)

Set secrets in `~/.gradle/gradle.properties` or CI environment:

```properties
signingInMemoryKeyId=<last 8 chars of key ID>
signingInMemoryKey=<armored PGP private key>
signingInMemoryKeyPassword=<key passphrase>
mavenCentralUsername=<Sonatype Central Portal username>
mavenCentralPassword=<Sonatype Central Portal password>
```

Then publish:

```bash
./gradlew publishAllPublicationsToMavenCentralRepository
```

---

## License

```
Copyright 2025 Abdo Essam

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0
```
