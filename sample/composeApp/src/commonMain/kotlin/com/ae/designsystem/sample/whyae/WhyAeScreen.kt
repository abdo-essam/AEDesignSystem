@file:OptIn(ExperimentalLayoutApi::class)

package com.ae.designsystem.sample.whyae

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.components.ui.card.AECard
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIconToken
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.examples.CardContent
import com.ae.designsystem.sample.examples.CardHeader
import com.ae.designsystem.sample.utils.WindowSizeClass

@Composable
fun WhyAeScreen() {
    val scrollState = rememberScrollState()

    BoxWithConstraints(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        contentAlignment = Alignment.TopCenter,
    ) {
        val sizeClass = WindowSizeClass.fromWidth(maxWidth)
        val horizontalPadding =
            when (sizeClass) {
                WindowSizeClass.Compact  -> AETheme.spacing.md
                WindowSizeClass.Medium   -> AETheme.spacing.lg
                WindowSizeClass.Expanded -> AETheme.spacing.lg
            }

        Column(
            modifier =
                Modifier
                    .widthIn(max = 860.dp)
                    .fillMaxWidth()
                    .padding(horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // ── Hero ──
            Spacer(Modifier.height(AETheme.spacing.xxxl))

            AEBadge(label = "Manifesto", variant = AEBadgeVariant.Outline)

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text = "Why AEDesignSystem",
                style = AETheme.typography.displayLarge.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.sm))

            AEText(
                text =
                    "For teams who ship products with a strong visual identity " +
                        "across Android, iOS, and Desktop \u2014 without looking like a Google app.",
                style = AETheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                color = AETheme.colors.textMuted,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.xxxl))

            // ── Section 1: Design Philosophy ──
            SectionAEBadge("Design Philosophy")

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text = "A design system with convictions",
                style = AETheme.typography.headingLarge.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text =
                    "AEDesignSystem is not a theme layer on top of Material3. " +
                        "It is built from scratch on Compose Foundation \u2014 " +
                        "every color token, every animation curve, every interaction pattern " +
                        "was chosen deliberately, not inherited.",
                style = AETheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = AETheme.colors.textMuted,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.xl))

            PhilosophyGrid(sizeClass)

            Spacer(Modifier.height(AETheme.spacing.xxxl))
            AEDivider(modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(AETheme.spacing.xxxl))

            // ── Section 2: Why Not Material3 ──
            SectionAEBadge("The M3 Problem")

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text = "Material3 is great \u2014 until it isn't",
                style = AETheme.typography.headingLarge.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text =
                    "M3 has dozens of engineers behind it. " +
                        "It is optimized for one thing: making Android apps fast. " +
                        "But if you are building a product with its own identity " +
                        "across multiple platforms, M3 becomes the constraint, not the tool.",
                style = AETheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = AETheme.colors.textMuted,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.xl))

            PainPointsList()

            Spacer(Modifier.height(AETheme.spacing.xxxl))
            AEDivider(modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(AETheme.spacing.xxxl))

            // ── Section 3: What AEDesignSystem Does Differently ──
            SectionAEBadge("The AEDesignSystem Answer")

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text = "Built different, on purpose",
                style = AETheme.typography.headingLarge.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.xl))

            DifferentiatorsList()

            Spacer(Modifier.height(AETheme.spacing.xxxl))
            AEDivider(modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(AETheme.spacing.xxxl))

            // ── Section 4: Accessibility ──
            SectionAEBadge("Engineering Depth")

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text = "Accessibility is not an afterthought",
                style = AETheme.typography.headingLarge.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text =
                    "Every interactive component in AEDesignSystem has been audited against " +
                        "WCAG 2.2 Level AA. Not partially \u2014 fully. " +
                        "Roles, keyboard navigation, focus trapping, screen reader labels, " +
                        "48dp touch targets, progress semantics. " +
                        "The kind of work M3 does with dozens of engineers \u2014 we do too.",
                style = AETheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = AETheme.colors.textMuted,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.xl))

            AccessibilityGrid(sizeClass)

            Spacer(Modifier.height(AETheme.spacing.xxxl))
            AEDivider(modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(AETheme.spacing.xxxl))

            // ── Section 5: Who This Is For ──
            AEText(
                text =
                    "AEDesignSystem is for teams who want to move fast " +
                        "and look like themselves.",
                style = AETheme.typography.headingMedium.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.md))

            AEText(
                text =
                    "Material3 is for teams who want to move fast " +
                        "and look like Android.",
                style = AETheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = AETheme.colors.textMuted,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.sm))

            AEText(
                text = "Different jobs. Different tools. Choose yours.",
                style = AETheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = AETheme.colors.textMuted,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(AETheme.spacing.xxxl))
            Spacer(Modifier.height(AETheme.spacing.xxxl))
        }
    }
}

// ── Helpers ──

@Composable
private fun SectionAEBadge(label: String) {
    AEBadge(label = label, variant = AEBadgeVariant.Outline)
}

@Composable
private fun PhilosophyGrid(sizeClass: WindowSizeClass) {
    val cards =
        listOf(
            PhilosophyItem(
                icon = AEIcons.Settings,
                title = "Spring physics over easing curves",
                body =
                    "M3 hardcodes cubic-bezier easing that feels mechanical. " +
                        "AEDesignSystem defaults to spring animations \u2014 they handle interruptions " +
                        "gracefully and feel alive. Every spring parameter is a theme token " +
                        "you control.",
            ),
            PhilosophyItem(
                icon = AEIcons.Eye,
                title = "Semantic tokens over tonal palettes",
                body =
                    "M3's tonal palette system (primary, primaryContainer, onPrimaryContainer) " +
                        "bleeds Google's design language into your product. " +
                        "AEDesignSystem uses 31 semantic tokens with on* naming \u2014 " +
                        "your palette, your brand, zero M3 residue.",
            ),
            PhilosophyItem(
                icon = AEIcons.Copy,
                title = "Copy-paste over package dependency",
                body =
                    "Every M3 update is a potential breaking change to your UI. " +
                        "With AEDesignSystem, the component is yours the moment you add it. " +
                        "Modify it, extend it, delete half of it. No upstream surprises.",
            ),
            PhilosophyItem(
                icon = AEIcons.Star,
                title = "Platform-native over Android-first",
                body =
                    "M3 was designed for phones. On desktop, hover states are wrong. " +
                        "On iOS, scroll physics feel foreign. " +
                        "AEDesignSystem on compose.foundation is genuinely cross-platform \u2014 " +
                        "hover, density, and motion adapt to where your app runs.",
            ),
        )

    val columns = if (sizeClass == WindowSizeClass.Compact) 1 else 2

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
        maxItemsInEachRow = columns,
        modifier = Modifier.fillMaxWidth(),
    ) {
        cards.forEach { item ->
            PhilosophyAECard(
                item = item,
                modifier =
                    Modifier.weight(1f),
            )
        }
    }
}

private data class PhilosophyItem(
    val icon: AEIconToken,
    val title: String,
    val body: String,
)

@Composable
private fun PhilosophyAECard(
    item: PhilosophyItem,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(AETheme.radius.md)
    AECard(modifier = modifier) {
        CardHeader {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier =
                        Modifier
                            .size(36.dp)
                            .clip(shape)
                            .background(AETheme.colors.accentMuted)
                            .padding(AETheme.spacing.sm),
                    contentAlignment = Alignment.Center,
                ) {
                    AEIcon(
                        token = item.icon,
                        contentDescription = null,
                        tint = AETheme.colors.accent,
                        size = 18.dp,
                    )
                }
                Spacer(Modifier.width(AETheme.spacing.sm))
                AEText(
                    text = item.title,
                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                )
            }
        }
        CardContent {
            AEText(
                text = item.body,
                style = AETheme.typography.bodySmall,
                color = AETheme.colors.textMuted,
            )
        }
    }
}

@Composable
private fun PainPointsList() {
    val painPoints =
        listOf(
            PainPoint(
                number = "01",
                title = "Branding lock-in",
                body =
                    "M3's tonal palette system bleeds through everything. " +
                        "70%+ of Compose apps look identifiably \"Material\" even after extensive theming. " +
                        "Your product ends up looking like a Google app wearing a costume.",
            ),
            PainPoint(
                number = "02",
                title = "Cross-platform mismatch",
                body =
                    "M3 is Android-first. On Desktop, ripple effects feel wrong. " +
                        "On iOS, the scroll physics and density are foreign. " +
                        "M3 can't be platform-native without enormous custom work \u2014 " +
                        "work that defeats the purpose of using it.",
            ),
            PainPoint(
                number = "03",
                title = "Motion you can't escape",
                body =
                    "M3 hardcodes ripple indicators, state layers, and transition specifications. " +
                        "Want spring-based interactions? You have to fight the framework. " +
                        "Want to remove ripple? Prepare for unexpected visual breakage.",
            ),
            PainPoint(
                number = "04",
                title = "Update roulette",
                body =
                    "Every M3 version bump is a gamble. Token renames, behavior changes, " +
                        "deprecated APIs \u2014 your UI breaks because of someone else's roadmap. " +
                        "You never truly own your components.",
            ),
        )

    Column(
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
        modifier = Modifier.fillMaxWidth(),
    ) {
        painPoints.forEach { point ->
            PainPointRow(point)
        }
    }
}

private data class PainPoint(
    val number: String,
    val title: String,
    val body: String,
)

@Composable
private fun PainPointRow(point: PainPoint) {
    val shape = RoundedCornerShape(AETheme.radius.md)
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .border(1.dp, AETheme.colors.border, shape)
                .clip(shape)
                .padding(AETheme.spacing.lg),
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
    ) {
        AEText(
            text = point.number,
            style = AETheme.typography.headingLarge.copy(fontWeight = FontWeight.Bold),
            color = AETheme.colors.accent,
        )
        Column {
            AEText(
                text = point.title,
                style = TextStyle(fontWeight = FontWeight.SemiBold),
            )
            Spacer(Modifier.height(AETheme.spacing.xs))
            AEText(
                text = point.body,
                style = AETheme.typography.bodySmall,
                color = AETheme.colors.textMuted,
            )
        }
    }
}

@Composable
private fun DifferentiatorsList() {
    val items =
        listOf(
            Differentiator(
                title = "Zero Material3 dependency",
                body =
                    "Built entirely on compose.foundation. No M3 tokens, no M3 behaviors, " +
                        "no M3 opinions leaking into your product. Every pixel is intentional.",
            ),
            Differentiator(
                title = "5 palettes \u00d7 7 accents \u00d7 5 style presets",
                body =
                    "175 unique theme combinations out of the box. " +
                        "Or build your own from scratch \u2014 every token is overridable. " +
                        "Your app should look like your brand, not our defaults.",
            ),
            Differentiator(
                title = "Spring physics as a first-class token",
                body =
                    "AE Motion gives you spring stiffness, damping, and duration as theme tokens. " +
                        "Switch from snappy to playful with a single preset. " +
                        "No fighting the framework to get the motion you want.",
            ),
            Differentiator(
                title = "Copy-paste ownership via CLI + registry",
                body =
                    "aedsui add button copies the source into your project. " +
                        "It\u2019s your code now. Modify it, extend it, delete half. " +
                        "Auto-resolved dependencies mean no broken imports.",
            ),
            Differentiator(
                title = "True multiplatform",
                body =
                    "Android, iOS, Desktop (JVM), and Web (WasmJs). " +
                        "Same components, same tokens, same behavior. " +
                        "Hover states on desktop, touch targets on mobile, " +
                        "platform-appropriate density everywhere.",
            ),
        )

    Column(
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items.forEach { item ->
            DifferentiatorRow(item)
        }
    }
}

private data class Differentiator(
    val title: String,
    val body: String,
)

@Composable
private fun DifferentiatorRow(item: Differentiator) {
    val shape = RoundedCornerShape(AETheme.radius.md)
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(AETheme.colors.accentMuted, shape)
                .clip(shape)
                .padding(AETheme.spacing.lg),
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
        verticalAlignment = Alignment.Top,
    ) {
        AEIcon(
            token = AEIcons.Check,
            contentDescription = null,
            tint = AETheme.colors.accent,
            size = 20.dp,
        )
        Column {
            AEText(
                text = item.title,
                style = TextStyle(fontWeight = FontWeight.SemiBold),
            )
            Spacer(Modifier.height(AETheme.spacing.xs))
            AEText(
                text = item.body,
                style = AETheme.typography.bodySmall,
                color = AETheme.colors.textMuted,
            )
        }
    }
}

@Composable
private fun AccessibilityGrid(sizeClass: WindowSizeClass) {
    val items =
        listOf(
            A11yItem("Semantic roles", "Every interactive component declares its role \u2014 Button, Checkbox, Switch, Tab, DropdownList."),
            A11yItem("Keyboard navigation", "Focus trapping in dialogs, Escape to close overlays, arrow keys in menus and sliders."),
            A11yItem("Screen reader labels", "contentDescription, stateDescription, paneTitle, heading semantics, liveRegion on alerts."),
            A11yItem(
                "48dp touch targets",
                "minTouchTarget modifier on every interactive element. Expands hit area without changing visuals.",
            ),
            A11yItem(
                "Progress semantics",
                "progressBarRangeInfo on Progress, Spinner, and Slider. Screen readers announce values natively.",
            ),
            A11yItem(
                "Form error states",
                "isError + error() semantics on Input, Textarea, Select. Destructive border on validation failure.",
            ),
        )

    val columns = if (sizeClass == WindowSizeClass.Compact) 1 else 2

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
        verticalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
        maxItemsInEachRow = columns,
        modifier = Modifier.fillMaxWidth(),
    ) {
        items.forEach { item ->
            A11yAECard(
                item = item,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

private data class A11yItem(
    val title: String,
    val body: String,
)

@Composable
private fun A11yAECard(
    item: A11yItem,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(AETheme.radius.md)
    Box(
        modifier =
            modifier
                .border(1.dp, AETheme.colors.border, shape)
                .clip(shape)
                .padding(AETheme.spacing.lg),
    ) {
        Column {
            AEText(
                text = item.title,
                style = TextStyle(fontWeight = FontWeight.SemiBold),
            )
            Spacer(Modifier.height(AETheme.spacing.xs))
            AEText(
                text = item.body,
                style = AETheme.typography.bodySmall,
                color = AETheme.colors.textMuted,
            )
        }
    }
}
