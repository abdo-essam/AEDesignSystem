package com.ae.designsystem.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.*
import com.ae.designsystem.foundation.color.AEAccent
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.foundation.tokens.AEStylePreset
import kotlinx.coroutines.launch

@Composable
fun CatalogApp() {
    var palette by remember { mutableStateOf(AEPalette.Zinc) }
    var accent by remember { mutableStateOf(AEAccent.Blue) }
    var preset by remember { mutableStateOf(AEStylePreset.Default) }
    var darkMode by remember { mutableStateOf(true) }
    val snackbarState = rememberSnackbarState()
    val scope = rememberCoroutineScope()

    AETheme(
        palette = palette,
        accent = accent,
        preset = preset,
        darkTheme = darkMode,
    ) {
        val colors = AETheme.colors
        val spacing = AETheme.spacing

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background)
                    .verticalScroll(rememberScrollState())
                    .padding(spacing.xl),
                verticalArrangement = Arrangement.spacedBy(spacing.xxl),
            ) {
                CatalogHeader(darkMode = darkMode, onToggleDark = { darkMode = !darkMode })
                ThemeControlsSection(palette, accent, preset, { palette = it }, { accent = it }, { preset = it })
                AEDivider()
                ButtonsSection()
                AEDivider()
                FormInputsSection()
                AEDivider()
                FeedbackSection()
                AEDivider()
                Tier3Section()
                AEDivider()
                Tier4Section(scope = scope, snackbarState = snackbarState)
                AEDivider()
                CardsSection()
                AEDivider()
                TypographySection()
                AEDivider()
                IconsSection()
                AEDivider()
                ColorsSection()
                Spacer(Modifier.height(spacing.xxxl))
            }

            // Snackbar host — floats above scroll content
            AESnackbarHost(
                state = snackbarState,
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

// ── Header ──────────────────────────────────────────────────────

@Composable
private fun CatalogHeader(darkMode: Boolean, onToggleDark: () -> Unit) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            AEText("AEDesignSystem", style = AETheme.typography.displayMedium, color = colors.accent)
            AEText("Component Catalog · v0.1.0", style = AETheme.typography.bodyMedium, color = colors.textMuted)
        }
        AEButton(onClick = onToggleDark, variant = AEButtonVariant.Ghost) {
            AEIcon(if (darkMode) AEIcons.Sun else AEIcons.Moon, tint = colors.textPrimary, size = 20.dp)
        }
    }
}

// ── Theme Controls ───────────────────────────────────────────────

@Composable
private fun ThemeControlsSection(
    palette: AEPalette, accent: AEAccent, preset: AEStylePreset,
    onPalette: (AEPalette) -> Unit, onAccent: (AEAccent) -> Unit, onPreset: (AEStylePreset) -> Unit,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    SectionTitle("Theme Controls")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
            AEText("Palette", style = AETheme.typography.labelMedium, color = colors.textMuted)
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                AEPalette.entries.forEach { p ->
                    AEChip(label = p.name, selected = p == palette, onClick = { onPalette(p) })
                }
            }
            AEText("Accent", style = AETheme.typography.labelMedium, color = colors.textMuted)
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                AEAccent.entries.forEach { a ->
                    Box(
                        modifier = Modifier.size(32.dp).clip(CircleShape).background(a.primary)
                            .then(if (a == accent) Modifier.border(2.dp, colors.textPrimary, CircleShape) else Modifier)
                            .clickable { onAccent(a) },
                    )
                }
            }
            AEText("Style Preset", style = AETheme.typography.labelMedium, color = colors.textMuted)
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                modifier = Modifier.horizontalScroll(rememberScrollState()),
            ) {
                AEStylePreset.entries.forEach { s ->
                    AEChip(label = s.name, selected = s == preset, onClick = { onPreset(s) })
                }
            }
        }
    }
}

// ── Buttons ──────────────────────────────────────────────────────

@Composable
private fun ButtonsSection() {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    SectionTitle("Buttons")

    AEText("Variants", style = AETheme.typography.labelMedium, color = colors.textMuted)
    Spacer(Modifier.height(spacing.sm))
    Row(horizontalArrangement = Arrangement.spacedBy(spacing.md), modifier = Modifier.horizontalScroll(rememberScrollState())) {
        AEButton(onClick = {}) { AEText("Filled", color = colors.textOnAccent, style = AETheme.typography.labelLarge) }
        AEButton(onClick = {}, variant = AEButtonVariant.Outlined) { AEText("Outlined", color = colors.accent, style = AETheme.typography.labelLarge) }
        AEButton(onClick = {}, variant = AEButtonVariant.Ghost) { AEText("Ghost", color = colors.textPrimary, style = AETheme.typography.labelLarge) }
        AEButton(onClick = {}, variant = AEButtonVariant.Destructive) { AEText("Destructive", color = colors.destructiveText, style = AETheme.typography.labelLarge) }
        AEButton(onClick = {}, variant = AEButtonVariant.Link) { AEText("Link", color = colors.accent, style = AETheme.typography.labelLarge) }
    }

    Spacer(Modifier.height(spacing.lg))
    AEText("Sizes", style = AETheme.typography.labelMedium, color = colors.textMuted)
    Spacer(Modifier.height(spacing.sm))
    Row(horizontalArrangement = Arrangement.spacedBy(spacing.md), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.horizontalScroll(rememberScrollState())) {
        AEButton(onClick = {}, size = AEButtonSize.Small) { AEText("Small", color = colors.textOnAccent, style = AETheme.typography.labelMedium) }
        AEButton(onClick = {}, size = AEButtonSize.Medium) { AEText("Medium", color = colors.textOnAccent, style = AETheme.typography.labelLarge) }
        AEButton(onClick = {}, size = AEButtonSize.Large) { AEText("Large", color = colors.textOnAccent, style = AETheme.typography.labelLarge) }
    }

    Spacer(Modifier.height(spacing.lg))
    AEText("With Icons", style = AETheme.typography.labelMedium, color = colors.textMuted)
    Spacer(Modifier.height(spacing.sm))
    Row(horizontalArrangement = Arrangement.spacedBy(spacing.md), modifier = Modifier.horizontalScroll(rememberScrollState())) {
        AEButton(onClick = {}) {
            AEIcon(AEIcons.Heart, tint = colors.textOnAccent, size = 16.dp)
            Spacer(Modifier.width(spacing.xs))
            AEText("Like", color = colors.textOnAccent, style = AETheme.typography.labelLarge)
        }
        AEButton(onClick = {}, variant = AEButtonVariant.Outlined) {
            AEIcon(AEIcons.Download, tint = colors.accent, size = 16.dp)
            Spacer(Modifier.width(spacing.xs))
            AEText("Download", color = colors.accent, style = AETheme.typography.labelLarge)
        }
        AEButton(onClick = {}, variant = AEButtonVariant.Destructive) {
            AEIcon(AEIcons.Delete, tint = colors.destructiveText, size = 16.dp)
            Spacer(Modifier.width(spacing.xs))
            AEText("Delete", color = colors.destructiveText, style = AETheme.typography.labelLarge)
        }
    }

    Spacer(Modifier.height(spacing.lg))
    AEText("Disabled", style = AETheme.typography.labelMedium, color = colors.textMuted)
    Spacer(Modifier.height(spacing.sm))
    Row(horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
        AEButton(onClick = {}, enabled = false) { AEText("Disabled", color = colors.textMuted, style = AETheme.typography.labelLarge) }
        AEButton(onClick = {}, enabled = false, variant = AEButtonVariant.Outlined) { AEText("Disabled", color = colors.textMuted, style = AETheme.typography.labelLarge) }
    }
}

// ── Form Inputs ──────────────────────────────────────────────────

@Composable
private fun FormInputsSection() {
    val colors = AETheme.colors
    val spacing = AETheme.spacing

    SectionTitle("Form Inputs")

    var text by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checked1 by remember { mutableStateOf(true) }
    var checked2 by remember { mutableStateOf(false) }
    var switchOn by remember { mutableStateOf(true) }
    var radioSel by remember { mutableStateOf("Option A") }
    var sliderVal by remember { mutableFloatStateOf(0.4f) }

    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xl)) {

            // TextFields
            AEText("Text Fields", style = AETheme.typography.headingSmall)
            AETextField(value = text, onValueChange = { text = it }, label = "Username", placeholder = "Enter username", leadingIcon = AEIcons.User)
            AETextField(value = password, onValueChange = { password = it }, label = "Email", placeholder = "you@example.com", leadingIcon = AEIcons.Search, state = AETextFieldState.Success, supportingText = "Looks good!")
            AETextField(value = "", onValueChange = {}, label = "Error Field", placeholder = "Invalid input", state = AETextFieldState.Error, supportingText = "This field is required")
            AETextField(value = "Disabled", onValueChange = {}, label = "Disabled", enabled = false)

            AEDivider()

            // Checkboxes
            AEText("Checkboxes", style = AETheme.typography.headingSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.xl)) {
                AECheckbox(checked = checked1, onCheckedChange = { checked1 = it }, label = "Accept terms")
                AECheckbox(checked = checked2, onCheckedChange = { checked2 = it }, label = "Subscribe")
                AECheckbox(checked = true, onCheckedChange = {}, label = "Disabled", enabled = false)
            }

            AEDivider()

            // Switches
            AEText("Switches", style = AETheme.typography.headingSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.xl)) {
                AESwitch(checked = switchOn, onCheckedChange = { switchOn = it }, label = "Notifications")
                AESwitch(checked = false, onCheckedChange = {}, label = "Disabled", enabled = false)
            }

            AEDivider()

            // Radio buttons
            AEText("Radio Buttons", style = AETheme.typography.headingSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.xl)) {
                listOf("Option A", "Option B", "Option C").forEach { opt ->
                    AERadioButton(selected = radioSel == opt, onClick = { radioSel = opt }, label = opt)
                }
            }

            AEDivider()

            // Slider
            AEText("Slider  ·  ${(sliderVal * 100).toInt()}%", style = AETheme.typography.headingSmall)
            AESlider(value = sliderVal, onValueChange = { sliderVal = it })
        }
    }
}

// ── Feedback Components ──────────────────────────────────────────

@Composable
private fun FeedbackSection() {
    val colors = AETheme.colors
    val spacing = AETheme.spacing

    SectionTitle("Feedback & Status")

    var showDialog by remember { mutableStateOf(false) }

    AEDialog(
        visible = showDialog,
        onDismiss = { showDialog = false },
        title = "Confirm Action",
        confirmButton = {
            AEButton(onClick = { showDialog = false }, variant = AEButtonVariant.Destructive, size = AEButtonSize.Small) {
                AEText("Delete", color = colors.destructiveText, style = AETheme.typography.labelLarge)
            }
        },
        dismissButton = {
            AEButton(onClick = { showDialog = false }, variant = AEButtonVariant.Ghost, size = AEButtonSize.Small) {
                AEText("Cancel", color = colors.textPrimary, style = AETheme.typography.labelLarge)
            }
        },
    ) {
        AEText("This action cannot be undone. Are you sure you want to continue?", color = colors.textSecondary)
    }

    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xl)) {

            // Badges
            AEText("Badges", style = AETheme.typography.headingSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm), modifier = Modifier.horizontalScroll(rememberScrollState())) {
                AEBadge("Default")
                AEBadge("Destructive", variant = AEBadgeVariant.Destructive)
                AEBadge("Success", variant = AEBadgeVariant.Success)
                AEBadge("Warning", variant = AEBadgeVariant.Warning)
                AEBadge("Info", variant = AEBadgeVariant.Info)
                AEBadge("Outline", variant = AEBadgeVariant.Outline)
            }

            AEDivider()

            // Chips
            AEText("Chips", style = AETheme.typography.headingSmall)
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm), modifier = Modifier.horizontalScroll(rememberScrollState())) {
                AEChip("Kotlin")
                AEChip("Selected", selected = true)
                AEChip("With Icon", leadingIcon = AEIcons.Star)
                AEChip("Close", trailingIcon = AEIcons.Close, onClick = {})
            }

            AEDivider()

            // Progress
            AEText("Progress", style = AETheme.typography.headingSmall)
            Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                AEText("Linear — 65%", style = AETheme.typography.labelMedium, color = colors.textMuted)
                AELinearProgress(progress = 0.65f)
                AEText("Linear — indeterminate", style = AETheme.typography.labelMedium, color = colors.textMuted)
                AELinearProgress(progress = null)
                Row(horizontalArrangement = Arrangement.spacedBy(spacing.xl), verticalAlignment = Alignment.CenterVertically) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                        AECircularProgress(progress = 0.7f)
                        AEText("70%", style = AETheme.typography.labelSmall, color = colors.textMuted)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
                        AECircularProgress(progress = null)
                        AEText("Loading", style = AETheme.typography.labelSmall, color = colors.textMuted)
                    }
                }
            }

            AEDivider()

            // Skeleton
            AEText("Skeleton", style = AETheme.typography.headingSmall)
            Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
                repeat(3) { AESkeletonListItem() }
            }

            AEDivider()

            // Dialog trigger
            AEText("Dialog", style = AETheme.typography.headingSmall)
            AEButton(onClick = { showDialog = true }, variant = AEButtonVariant.Outlined) {
                AEText("Open Dialog", color = colors.accent, style = AETheme.typography.labelLarge)
            }
        }
    }
}

// ── Tier 3: Avatar · Tabs · BottomSheet ─────────────────────────

@Composable
private fun Tier3Section() {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    var selectedTab by remember { mutableIntStateOf(0) }
    var showSheet by remember { mutableStateOf(false) }

    AEBottomSheet(
        visible = showSheet,
        onDismiss = { showSheet = false },
        title = "Sort Options",
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            listOf("Name A–Z", "Name Z–A", "Newest first", "Oldest first").forEach { opt ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(spacing.md),
                ) {
                    AEIcon(AEIcons.ChevronRight, tint = colors.textMuted, size = 16.dp)
                    AEText(opt, style = AETheme.typography.bodyMedium)
                }
            }
        }
    }

    // ── Avatar ──
    SectionTitle("Avatar")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.xl)) {
            // Sizes
            AEText("Sizes", style = AETheme.typography.labelMedium, color = colors.textMuted)
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.lg),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AEAvatarSize.entries.forEach { sz ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(spacing.xs),
                    ) {
                        AEAvatar(initials = "AE", size = sz)
                        AEText(sz.name, style = AETheme.typography.labelSmall, color = colors.textMuted)
                    }
                }
            }
            AEDivider()
            // States
            AEText("Variants", style = AETheme.typography.labelMedium, color = colors.textMuted)
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.lg), verticalAlignment = Alignment.CenterVertically) {
                AEAvatar(initials = "AB", size = AEAvatarSize.Large, showOnlineStatus = true, isOnline = true)
                AEAvatar(initials = "CD", size = AEAvatarSize.Large, showOnlineStatus = true, isOnline = false)
                AEAvatar(size = AEAvatarSize.Large)
                AEAvatar(
                    initials = "ME",
                    size = AEAvatarSize.Large,
                    backgroundColor = colors.accentMuted,
                    contentColor = colors.accent,
                    borderColor = colors.accent,
                )
            }
            AEDivider()
            // Group
            AEText("Avatar Group", style = AETheme.typography.labelMedium, color = colors.textMuted)
            AEAvatarGroup(
                avatars = listOf("AE", "JD", "MK", "TS", "LR", "PQ"),
                maxVisible = 4,
                size = AEAvatarSize.Medium,
            )
        }
    }

    Spacer(Modifier.height(spacing.xl))

    // ── Tabs ──
    SectionTitle("Tabs")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.lg)) {
            AETabs(
                tabs = listOf("Overview", "Tokens", "Components", "Examples"),
                selectedIndex = selectedTab,
                onTabSelected = { selectedTab = it },
            )
            // Content for selected tab
            when (selectedTab) {
                0 -> AEText(
                    "Token-driven design system built on Compose Multiplatform with zero Material3 dependency.",
                    style = AETheme.typography.bodyMedium,
                    color = colors.textSecondary,
                )
                1 -> Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    AEBadge("Colors")
                    AEBadge("Typography", variant = AEBadgeVariant.Info)
                    AEBadge("Spacing", variant = AEBadgeVariant.Success)
                    AEBadge("Motion", variant = AEBadgeVariant.Warning)
                }
                2 -> Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    listOf("Button", "Card", "Input", "Avatar").forEach { c ->
                        AEChip(label = c, selected = c == "Button")
                    }
                }
                3 -> AEText(
                    "See the catalog sections above for live interactive examples of every component.",
                    style = AETheme.typography.bodyMedium,
                    color = colors.textSecondary,
                )
            }
        }
    }

    Spacer(Modifier.height(spacing.xl))

    // ── BottomSheet ──
    SectionTitle("Bottom Sheet")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText(
                "Slides up from the bottom with a scrim overlay, drag handle, and optional title.",
                style = AETheme.typography.bodyMedium,
                color = colors.textSecondary,
            )
            Spacer(Modifier.height(spacing.xs))
            AEButton(onClick = { showSheet = true }, variant = AEButtonVariant.Outlined) {
                AEIcon(AEIcons.ChevronUp, tint = colors.accent, size = 16.dp)
                Spacer(Modifier.width(spacing.sm))
                AEText("Show Bottom Sheet", color = colors.accent, style = AETheme.typography.labelLarge)
            }
        }
    }
}

// ── Cards ────────────────────────────────────────────────────────

@Composable
private fun CardsSection() {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    SectionTitle("Cards & Surfaces")
    Row(horizontalArrangement = Arrangement.spacedBy(spacing.lg), modifier = Modifier.horizontalScroll(rememberScrollState())) {
        AECard(modifier = Modifier.width(260.dp), header = { AEText("Basic Card", style = AETheme.typography.headingSmall) }) {
            AEText("Built on AESurface with optional header, content, and footer slots.", color = colors.textSecondary)
        }
        AECard(
            modifier = Modifier.width(260.dp),
            header = {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    AEIcon(AEIcons.Star, tint = colors.accent, size = 18.dp)
                    AEText("With Actions", style = AETheme.typography.headingSmall)
                }
            },
            footer = {
                Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                    AEButton(onClick = {}, variant = AEButtonVariant.Ghost, size = AEButtonSize.Small) { AEText("Cancel", color = colors.textPrimary, style = AETheme.typography.labelMedium) }
                    AEButton(onClick = {}, size = AEButtonSize.Small) { AEText("Confirm", color = colors.textOnAccent, style = AETheme.typography.labelMedium) }
                }
            },
        ) {
            AEText("Header icon, footer buttons — all via composable slots.", color = colors.textSecondary)
        }
        AECard(modifier = Modifier.width(260.dp)) {
            AEText("Elevation Levels", style = AETheme.typography.headingSmall)
            Spacer(Modifier.height(spacing.md))
            listOf("none" to AETheme.elevation.none, "sm" to AETheme.elevation.sm, "md" to AETheme.elevation.md, "lg" to AETheme.elevation.lg).forEach { (name, elev) ->
                AESurface(elevation = elev, modifier = Modifier.fillMaxWidth()) {
                    AEText("elevation.$name", modifier = Modifier.padding(spacing.sm), style = AETheme.typography.code, color = colors.textMuted)
                }
                Spacer(Modifier.height(spacing.xs))
            }
        }
    }
}

// ── Typography ───────────────────────────────────────────────────

@Composable
private fun TypographySection() {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val typo = AETheme.typography
    SectionTitle("Typography")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            listOf(
                "displayLarge" to typo.displayLarge, "displayMedium" to typo.displayMedium,
                "headingLarge" to typo.headingLarge, "headingMedium" to typo.headingMedium,
                "headingSmall" to typo.headingSmall, "bodyLarge" to typo.bodyLarge,
                "bodyMedium" to typo.bodyMedium, "bodySmall" to typo.bodySmall,
                "labelLarge" to typo.labelLarge, "labelMedium" to typo.labelMedium,
                "labelSmall" to typo.labelSmall, "code" to typo.code,
            ).forEach { (name, style) ->
                Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(spacing.lg)) {
                    AEText(name, style = typo.code, color = colors.textMuted, modifier = Modifier.width(110.dp))
                    AEText("AEDesignSystem", style = style)
                }
            }
        }
    }
}

// ── Icons ────────────────────────────────────────────────────────

@Composable
private fun IconsSection() {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius
    SectionTitle("Icons")
    val icons = listOf(
        "ArrowLeft" to AEIcons.ArrowLeft, "ArrowRight" to AEIcons.ArrowRight,
        "ArrowUp" to AEIcons.ArrowUp, "ArrowDown" to AEIcons.ArrowDown,
        "Menu" to AEIcons.Menu, "Close" to AEIcons.Close,
        "Search" to AEIcons.Search, "Home" to AEIcons.Home,
        "Add" to AEIcons.Add, "Edit" to AEIcons.Edit,
        "Delete" to AEIcons.Delete, "Share" to AEIcons.Share,
        "Download" to AEIcons.Download, "Upload" to AEIcons.Upload,
        "Check" to AEIcons.Check, "CheckCircle" to AEIcons.CheckCircle,
        "Warning" to AEIcons.Warning, "Error" to AEIcons.Error,
        "Info" to AEIcons.Info, "Heart" to AEIcons.Heart,
        "Star" to AEIcons.Star, "Eye" to AEIcons.Eye,
        "ChevronDown" to AEIcons.ChevronDown, "ChevronUp" to AEIcons.ChevronUp,
        "Settings" to AEIcons.Settings, "User" to AEIcons.User,
        "Calendar" to AEIcons.Calendar, "Moon" to AEIcons.Moon,
        "Sun" to AEIcons.Sun, "Refresh" to AEIcons.Refresh,
    )
    AECard {
        icons.chunked(6).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm), modifier = Modifier.horizontalScroll(rememberScrollState())) {
                row.forEach { (name, token) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(68.dp).clip(RoundedCornerShape(radius.md)).background(colors.surfaceHover).padding(spacing.sm),
                    ) {
                        AEIcon(token = token, tint = colors.textPrimary, size = 22.dp)
                        Spacer(Modifier.height(spacing.xxs))
                        AEText(name, style = AETheme.typography.labelSmall, color = colors.textMuted, maxLines = 1)
                    }
                }
            }
            Spacer(Modifier.height(spacing.sm))
        }
    }
}

// ── Colors ───────────────────────────────────────────────────────

@Composable
private fun ColorsSection() {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    val radius = AETheme.radius
    SectionTitle("Color Tokens")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.sm)) {
            listOf(
                "background" to colors.background, "backgroundSecondary" to colors.backgroundSecondary,
                "surface" to colors.surface, "surfaceHover" to colors.surfaceHover,
                "accent" to colors.accent, "accentHover" to colors.accentHover,
                "accentMuted" to colors.accentMuted, "textPrimary" to colors.textPrimary,
                "textSecondary" to colors.textSecondary, "textMuted" to colors.textMuted,
                "destructive" to colors.destructive, "success" to colors.success,
                "warning" to colors.warning, "info" to colors.info,
                "border" to colors.border, "ring" to colors.ring,
            ).forEach { (name, color) ->
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(spacing.md)) {
                    Box(
                        modifier = Modifier.size(26.dp).clip(RoundedCornerShape(radius.sm))
                            .background(color).border(1.dp, colors.border, RoundedCornerShape(radius.sm)),
                    )
                    AEText(name, style = AETheme.typography.code, color = colors.textSecondary)
                }
            }
        }
    }
}

// ── Tier 4: TopAppBar · NavBar · Snackbar · Dropdown ────────────

@Composable
private fun Tier4Section(
    scope: kotlinx.coroutines.CoroutineScope,
    snackbarState: AESnackbarState,
) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    var currentNav by remember { mutableIntStateOf(0) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    // ── TopAppBar ──
    SectionTitle("Top App Bar")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText("Standard", style = AETheme.typography.labelMedium, color = colors.textMuted)
            AETopAppBar(title = "Settings", elevated = true)
            Spacer(Modifier.height(spacing.xs))
            AEText("With navigation + actions", style = AETheme.typography.labelMedium, color = colors.textMuted)
            AETopAppBar(
                title = "My Profile",
                navigationIcon = AEIcons.ArrowLeft,
                onNavigationClick = {},
                actions = {
                    AEButton(onClick = {}, variant = AEButtonVariant.Ghost, size = AEButtonSize.Small) {
                        AEIcon(AEIcons.Edit, tint = colors.textPrimary, size = 18.dp)
                    }
                    AEButton(onClick = {}, variant = AEButtonVariant.Ghost, size = AEButtonSize.Small) {
                        AEIcon(AEIcons.MoreHorizontal, tint = colors.textPrimary, size = 18.dp)
                    }
                },
            )
        }
    }

    Spacer(Modifier.height(spacing.xl))

    // ── NavigationBar ──
    SectionTitle("Navigation Bar")
    AECard {
        AENavigationBar(
            items = listOf(
                AENavItem(AEIcons.Home,     "Home",    currentNav == 0),
                AENavItem(AEIcons.Search,   "Explore", currentNav == 1),
                AENavItem(AEIcons.Heart,    "Saved",   currentNav == 2, badge = 5),
                AENavItem(AEIcons.Calendar, "Events",  currentNav == 3),
                AENavItem(AEIcons.User,     "Profile", currentNav == 4),
            ),
            onItemSelected = { currentNav = it },
        )
    }

    Spacer(Modifier.height(spacing.xl))

    // ── Snackbar ──
    SectionTitle("Snackbar")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText(
                "Snackbars are hoisted via AESnackbarState and rendered by AESnackbarHost.",
                style = AETheme.typography.bodySmall,
                color = colors.textMuted,
            )
            Spacer(Modifier.height(spacing.xs))
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm), modifier = Modifier.horizontalScroll(rememberScrollState())) {
                AEButton(onClick = { scope.launch { snackbarState.show("Default message") } }, size = AEButtonSize.Small) {
                    AEText("Default", color = colors.textOnAccent, style = AETheme.typography.labelMedium)
                }
                AEButton(onClick = { scope.launch { snackbarState.show("Saved!", variant = AESnackbarVariant.Success) } }, size = AEButtonSize.Small, variant = AEButtonVariant.Outlined) {
                    AEText("Success", color = colors.accent, style = AETheme.typography.labelMedium)
                }
                AEButton(onClick = { scope.launch { snackbarState.show("Item deleted", actionLabel = "Undo", variant = AESnackbarVariant.Destructive) {} } }, size = AEButtonSize.Small, variant = AEButtonVariant.Destructive) {
                    AEText("Destructive + Undo", color = colors.destructiveText, style = AETheme.typography.labelMedium)
                }
                AEButton(onClick = { scope.launch { snackbarState.show("Warning: low storage", variant = AESnackbarVariant.Warning) } }, size = AEButtonSize.Small, variant = AEButtonVariant.Ghost) {
                    AEText("Warning", color = colors.textPrimary, style = AETheme.typography.labelMedium)
                }
            }
        }
    }

    Spacer(Modifier.height(spacing.xl))

    // ── DropdownMenu ──
    SectionTitle("Dropdown Menu")
    AECard {
        Column(verticalArrangement = Arrangement.spacedBy(spacing.md)) {
            AEText("Anchored popup with animated expand/collapse:", style = AETheme.typography.bodySmall, color = colors.textMuted)
            Spacer(Modifier.height(spacing.xs))
            Box {
                AEButton(onClick = { dropdownExpanded = !dropdownExpanded }, variant = AEButtonVariant.Outlined) {
                    AEText("Options", color = colors.accent, style = AETheme.typography.labelLarge)
                    Spacer(Modifier.width(spacing.xs))
                    AEIcon(AEIcons.ChevronDown, tint = colors.accent, size = 14.dp)
                }
                AEDropdownMenu(
                    expanded = dropdownExpanded,
                    onDismiss = { dropdownExpanded = false },
                    items = listOf(
                        AEDropdownItem("Edit", icon = AEIcons.Edit) { dropdownExpanded = false },
                        AEDropdownItem("Duplicate", icon = AEIcons.Copy) { dropdownExpanded = false },
                        AEDropdownItem("Share", icon = AEIcons.Share) { dropdownExpanded = false },
                        AEDropdownItem("Delete", icon = AEIcons.Delete, destructive = true) { dropdownExpanded = false },
                    ),
                )
            }
        }
    }
}

// ── Section title ────────────────────────────────────────────────

@Composable
private fun SectionTitle(title: String) {
    val colors = AETheme.colors
    val spacing = AETheme.spacing
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
        Box(modifier = Modifier.width(4.dp).height(22.dp).clip(RoundedCornerShape(2.dp)).background(colors.accent))
        AEText(title, style = AETheme.typography.headingMedium)
    }
}
