package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.theming_accent_blue_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_accent_default_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_accent_green_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_accent_orange_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_accent_red_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_accent_rose_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_accent_violet_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_accent_yellow_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_accents_body
import aedesignsystem.sample.composeapp.generated.resources.theming_color_tokens_body
import aedesignsystem.sample.composeapp.generated.resources.theming_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_palette_gray_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_palette_neutral_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_palette_slate_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_palette_stone_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_palette_zinc_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_palettes_body
import aedesignsystem.sample.composeapp.generated.resources.theming_preset_aurora_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_preset_default_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_preset_nebula_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_preset_nova_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_preset_vega_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_quick_start_body
import aedesignsystem.sample.composeapp.generated.resources.theming_section_accent_colors
import aedesignsystem.sample.composeapp.generated.resources.theming_section_color_palettes
import aedesignsystem.sample.composeapp.generated.resources.theming_section_color_tokens
import aedesignsystem.sample.composeapp.generated.resources.theming_section_quick_start
import aedesignsystem.sample.composeapp.generated.resources.theming_section_style_presets
import aedesignsystem.sample.composeapp.generated.resources.theming_section_token_overrides
import aedesignsystem.sample.composeapp.generated.resources.theming_style_presets_body
import aedesignsystem.sample.composeapp.generated.resources.theming_subsection_border_colors
import aedesignsystem.sample.composeapp.generated.resources.theming_subsection_interaction_states
import aedesignsystem.sample.composeapp.generated.resources.theming_subsection_interactive_colors
import aedesignsystem.sample.composeapp.generated.resources.theming_subsection_inverse_colors
import aedesignsystem.sample.composeapp.generated.resources.theming_subsection_muted_colors
import aedesignsystem.sample.composeapp.generated.resources.theming_subsection_status_colors
import aedesignsystem.sample.composeapp.generated.resources.theming_subsection_surface_colors
import aedesignsystem.sample.composeapp.generated.resources.theming_subsection_tinted_surfaces
import aedesignsystem.sample.composeapp.generated.resources.theming_title
import aedesignsystem.sample.composeapp.generated.resources.theming_token_background_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_border_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_border_subtle_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_destructive_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_destructive_hover_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_destructive_pressed_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_destructive_tinted_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_inverse_surface_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_muted_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_background_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_destructive_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_destructive_tinted_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_inverse_surface_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_muted_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_primary_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_primary_tinted_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_secondary_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_success_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_surface_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_on_warning_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_overrides_body
import aedesignsystem.sample.composeapp.generated.resources.theming_token_primary_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_primary_hover_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_primary_pressed_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_primary_tinted_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_ring_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_scrim_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_secondary_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_secondary_hover_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_secondary_pressed_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_success_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_surface_desc
import aedesignsystem.sample.composeapp.generated.resources.theming_token_warning_desc
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.CodeBlock
import com.ae.designsystem.sample.docs.components.ComponentPageHeader
import com.ae.designsystem.sample.docs.components.DemoBox
import com.ae.designsystem.sample.docs.components.DocSection
import com.ae.designsystem.sample.docs.components.PropInfo
import com.ae.designsystem.sample.docs.components.PropsTable
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Theming documentation page.
 *
 * Covers AETheme setup, palettes, accents, style presets,
 * and individual token customization.
 */
@Composable
fun ThemingDoc() {
    ComponentPageHeader(
        name = stringResource(Res.string.theming_title),
        description = stringResource(Res.string.theming_desc),
    )

    DocSection(
        stringResource(Res.string.theming_section_quick_start),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.theming_quick_start_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
// Simplest setup — just pick a palette
AETheme(
    palette = AEPalette.Zinc,
    isDark = true,
) {
    // All components now use Zinc dark colors
}

// Full customization in one call
AETheme(
    palette = AEPalette.Slate,
    accent = AEAccentPreset.Blue,
    isDark = true,
    preset = AEStylePreset.Vega,
) {
    // Slate palette + blue accent + rounded style
}
            """.trimIndent(),
        )
    }

    DocSection(
        stringResource(Res.string.theming_section_color_palettes),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.theming_palettes_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "Zinc",
                        "AEPalette",
                        "Default",
                        stringResource(
                            Res.string.theming_palette_zinc_desc,
                        ),
                    ),
                    PropInfo(
                        "Slate",
                        "AEPalette",
                        "\u2014",
                        stringResource(
                            Res.string.theming_palette_slate_desc,
                        ),
                    ),
                    PropInfo(
                        "Stone",
                        "AEPalette",
                        "\u2014",
                        stringResource(
                            Res.string.theming_palette_stone_desc,
                        ),
                    ),
                    PropInfo(
                        "Gray",
                        "AEPalette",
                        "\u2014",
                        stringResource(
                            Res.string.theming_palette_gray_desc,
                        ),
                    ),
                    PropInfo(
                        "Neutral",
                        "AEPalette",
                        "\u2014",
                        stringResource(
                            Res.string.theming_palette_neutral_desc,
                        ),
                    ),
                ),
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
// Switch palettes
AETheme(palette = AEPalette.Slate, isDark = true) {
    // Cool blue-gray UI
}
            """.trimIndent(),
        )
    }

    DocSection(
        stringResource(Res.string.theming_section_accent_colors),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.theming_accents_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        DemoBox {
            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(
                        AETheme.spacing.sm,
                    ),
            ) {
                AccentSwatch(
                    stringResource(
                        Res.string.theming_accent_default_desc,
                    ).substringBefore("."),
                    AETheme.colors.accent,
                )
            }
        }

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
// Apply a blue accent on any palette
AETheme(
    palette = AEPalette.Zinc,
    accent = AEAccentPreset.Blue,
    isDark = true,
) { /* Zinc colors + blue primary */ }
            """.trimIndent(),
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "Default",
                        "AEAccentPreset",
                        "Yes",
                        stringResource(
                            Res.string.theming_accent_default_desc,
                        ),
                    ),
                    PropInfo(
                        "Blue",
                        "AEAccentPreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_accent_blue_desc,
                        ),
                    ),
                    PropInfo(
                        "Green",
                        "AEAccentPreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_accent_green_desc,
                        ),
                    ),
                    PropInfo(
                        "Orange",
                        "AEAccentPreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_accent_orange_desc,
                        ),
                    ),
                    PropInfo(
                        "Red",
                        "AEAccentPreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_accent_red_desc,
                        ),
                    ),
                    PropInfo(
                        "Rose",
                        "AEAccentPreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_accent_rose_desc,
                        ),
                    ),
                    PropInfo(
                        "Violet",
                        "AEAccentPreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_accent_violet_desc,
                        ),
                    ),
                    PropInfo(
                        "Yellow",
                        "AEAccentPreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_accent_yellow_desc,
                        ),
                    ),
                ),
        )
    }

    DocSection(
        stringResource(Res.string.theming_section_style_presets),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.theming_style_presets_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
// One-line style switch
AETheme(
    palette = AEPalette.Zinc,
    isDark = true,
    preset = AEStylePreset.Vega,
) { /* rounded, bouncy, spacious */ }
            """.trimIndent(),
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "Default",
                        "AEStylePreset",
                        "Yes",
                        stringResource(
                            Res.string.theming_preset_default_desc,
                        ),
                    ),
                    PropInfo(
                        "Nova",
                        "AEStylePreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_preset_nova_desc,
                        ),
                    ),
                    PropInfo(
                        "Vega",
                        "AEStylePreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_preset_vega_desc,
                        ),
                    ),
                    PropInfo(
                        "Aurora",
                        "AEStylePreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_preset_aurora_desc,
                        ),
                    ),
                    PropInfo(
                        "Nebula",
                        "AEStylePreset",
                        "\u2014",
                        stringResource(
                            Res.string.theming_preset_nebula_desc,
                        ),
                    ),
                ),
        )
    }

    DocSection(
        stringResource(Res.string.theming_section_token_overrides),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.theming_token_overrides_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
// Custom typography
val typography = aeTypography(
    fontFamily = myFont,
    scale = 1.1f,       // 10% larger
    h1Size = 48.sp,     // override specific sizes
)

// Custom spacing
val spacing = aeSpacing(base = 5.dp)
// Generates: xs=5, sm=10, md=15, lg=20, xl=30...

// Custom shapes
val shapes = aeShapes(radius = 16.dp)
// Generates: sm=8, md=16, lg=24, xl=32, full=999

// Custom motion
val motion = AEMotionPresets.snappy()
// Or: .playful(), .minimal()

// Mix palette convenience with manual tokens
val colors = AEPalette.Zinc.resolve(isDark = true)
AETheme(
    colors = colors,
    typography = typography,
    spacing = spacing,
    shapes = shapes,
    motion = motion,
) { /* ... */ }
            """.trimIndent(),
        )
    }

    DocSection(
        stringResource(Res.string.theming_section_color_tokens),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.theming_color_tokens_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        // ── Surface Colors ──

        AEText(
            text =
                stringResource(
                    Res.string.theming_subsection_surface_colors,
                ),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "background",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_background_desc,
                        ),
                    ),
                    PropInfo(
                        "onBackground",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_background_desc,
                        ),
                    ),
                    PropInfo(
                        "surface",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_surface_desc,
                        ),
                    ),
                    PropInfo(
                        "onSurface",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_surface_desc,
                        ),
                    ),
                ),
        )

        Spacer(Modifier.height(AETheme.spacing.lg))

        // ── Interactive Colors ──

        AEText(
            text =
                stringResource(
                    Res.string.theming_subsection_interactive_colors,
                ),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "primary",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_primary_desc,
                        ),
                    ),
                    PropInfo(
                        "onPrimary",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_primary_desc,
                        ),
                    ),
                    PropInfo(
                        "secondary",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_secondary_desc,
                        ),
                    ),
                    PropInfo(
                        "onSecondary",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_secondary_desc,
                        ),
                    ),
                    PropInfo(
                        "destructive",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_destructive_desc,
                        ),
                    ),
                    PropInfo(
                        "onDestructive",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_destructive_desc,
                        ),
                    ),
                ),
        )

        Spacer(Modifier.height(AETheme.spacing.lg))

        // ── Status Colors ──

        AEText(
            text =
                stringResource(
                    Res.string.theming_subsection_status_colors,
                ),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "warning",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_warning_desc,
                        ),
                    ),
                    PropInfo(
                        "onWarning",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_warning_desc,
                        ),
                    ),
                    PropInfo(
                        "success",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_success_desc,
                        ),
                    ),
                    PropInfo(
                        "onSuccess",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_success_desc,
                        ),
                    ),
                ),
        )

        Spacer(Modifier.height(AETheme.spacing.lg))

        // ── Muted Colors ──

        AEText(
            text =
                stringResource(
                    Res.string.theming_subsection_muted_colors,
                ),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "muted",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_muted_desc,
                        ),
                    ),
                    PropInfo(
                        "onMuted",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_muted_desc,
                        ),
                    ),
                ),
        )

        Spacer(Modifier.height(AETheme.spacing.lg))

        // ── Tinted Surfaces ──

        AEText(
            text =
                stringResource(
                    Res.string.theming_subsection_tinted_surfaces,
                ),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "primaryTinted",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_primary_tinted_desc,
                        ),
                    ),
                    PropInfo(
                        "onPrimaryTinted",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_primary_tinted_desc,
                        ),
                    ),
                    PropInfo(
                        "destructiveTinted",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_destructive_tinted_desc,
                        ),
                    ),
                    PropInfo(
                        "onDestructiveTinted",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_destructive_tinted_desc,
                        ),
                    ),
                ),
        )

        Spacer(Modifier.height(AETheme.spacing.lg))

        // ── Border & Focus Colors ──

        AEText(
            text =
                stringResource(
                    Res.string.theming_subsection_border_colors,
                ),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "border",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_border_desc,
                        ),
                    ),
                    PropInfo(
                        "borderSubtle",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_border_subtle_desc,
                        ),
                    ),
                    PropInfo(
                        "ring",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_ring_desc,
                        ),
                    ),
                    PropInfo(
                        "scrim",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_scrim_desc,
                        ),
                    ),
                ),
        )

        Spacer(Modifier.height(AETheme.spacing.lg))

        // ── Inverse & Utility ──

        AEText(
            text =
                stringResource(
                    Res.string.theming_subsection_inverse_colors,
                ),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "inverseSurface",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_inverse_surface_desc,
                        ),
                    ),
                    PropInfo(
                        "onInverseSurface",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_on_inverse_surface_desc,
                        ),
                    ),
                ),
        )

        Spacer(Modifier.height(AETheme.spacing.lg))

        // ── Interaction States ──

        AEText(
            text =
                stringResource(
                    Res.string.theming_subsection_interaction_states,
                ),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        "primaryHover",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_primary_hover_desc,
                        ),
                    ),
                    PropInfo(
                        "primaryPressed",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_primary_pressed_desc,
                        ),
                    ),
                    PropInfo(
                        "destructiveHover",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_destructive_hover_desc,
                        ),
                    ),
                    PropInfo(
                        "destructivePressed",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_destructive_pressed_desc,
                        ),
                    ),
                    PropInfo(
                        "secondaryHover",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_secondary_hover_desc,
                        ),
                    ),
                    PropInfo(
                        "secondaryPressed",
                        "Color",
                        "\u2014",
                        stringResource(
                            Res.string.theming_token_secondary_pressed_desc,
                        ),
                    ),
                ),
        )
    }
}

@Composable
private fun AccentSwatch(
    name: String,
    color: androidx.compose.ui.graphics.Color,
) {
    Column(
        horizontalAlignment =
            androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.spacedBy(AETheme.spacing.xs),
    ) {
        Box(
            modifier =
                Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(color),
        )
        AEText(text = name, style = AETheme.typography.bodySmall)
    }
}
