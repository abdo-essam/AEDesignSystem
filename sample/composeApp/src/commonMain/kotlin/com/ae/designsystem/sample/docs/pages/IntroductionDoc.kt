package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.intro_badge_android
import aedesignsystem.sample.composeapp.generated.resources.intro_badge_desktop
import aedesignsystem.sample.composeapp.generated.resources.intro_badge_ios
import aedesignsystem.sample.composeapp.generated.resources.intro_badge_web
import aedesignsystem.sample.composeapp.generated.resources.intro_desc
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_copy_paste_desc
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_copy_paste_title
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_cross_platform_desc
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_cross_platform_title
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_no_material_desc
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_no_material_title
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_spring_desc
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_spring_title
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_theme_desc
import aedesignsystem.sample.composeapp.generated.resources.intro_feature_theme_title
import aedesignsystem.sample.composeapp.generated.resources.intro_name_meaning
import aedesignsystem.sample.composeapp.generated.resources.intro_platform_body
import aedesignsystem.sample.composeapp.generated.resources.intro_section_component_count
import aedesignsystem.sample.composeapp.generated.resources.intro_section_platform_support
import aedesignsystem.sample.composeapp.generated.resources.intro_section_quick_example
import aedesignsystem.sample.composeapp.generated.resources.intro_section_what_is
import aedesignsystem.sample.composeapp.generated.resources.intro_section_why
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_accent_colors
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_accent_colors_value
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_animation_enums
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_animation_enums_value
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_color_palettes
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_color_palettes_value
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_style_presets
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_style_presets_value
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_total_components
import aedesignsystem.sample.composeapp.generated.resources.intro_stat_total_components_value
import aedesignsystem.sample.composeapp.generated.resources.intro_title
import aedesignsystem.sample.composeapp.generated.resources.intro_what_is_body
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.CodeBlock
import com.ae.designsystem.sample.docs.components.ComponentPageHeader
import com.ae.designsystem.sample.docs.components.DemoBox
import com.ae.designsystem.sample.docs.components.DocSection
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Introduction / "What is AEDesignSystem?" documentation page.
 */
@Composable
fun IntroductionDoc() {
    ComponentPageHeader(
        name = stringResource(Res.string.intro_title),
        description = stringResource(Res.string.intro_desc),
    )

    DocSection(stringResource(Res.string.intro_section_what_is)) {
        AEText(
            text = stringResource(Res.string.intro_what_is_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        AEText(
            text = stringResource(Res.string.intro_name_meaning),
            style = AETheme.typography.bodyMedium,
        )
    }

    DocSection(stringResource(Res.string.intro_section_why)) {
        Column(
            verticalArrangement =
                Arrangement.spacedBy(AETheme.spacing.md),
        ) {
            FeatureItem(
                title =
                    stringResource(
                        Res.string.intro_feature_no_material_title,
                    ),
                description =
                    stringResource(
                        Res.string.intro_feature_no_material_desc,
                    ),
            )
            FeatureItem(
                title =
                    stringResource(
                        Res.string.intro_feature_copy_paste_title,
                    ),
                description =
                    stringResource(
                        Res.string.intro_feature_copy_paste_desc,
                    ),
            )
            FeatureItem(
                title =
                    stringResource(
                        Res.string.intro_feature_spring_title,
                    ),
                description =
                    stringResource(
                        Res.string.intro_feature_spring_desc,
                    ),
            )
            FeatureItem(
                title =
                    stringResource(
                        Res.string.intro_feature_cross_platform_title,
                    ),
                description =
                    stringResource(
                        Res.string.intro_feature_cross_platform_desc,
                    ),
            )
            FeatureItem(
                title =
                    stringResource(
                        Res.string.intro_feature_theme_title,
                    ),
                description =
                    stringResource(
                        Res.string.intro_feature_theme_desc,
                    ),
            )
        }
    }

    DocSection(
        stringResource(Res.string.intro_section_platform_support),
    ) {
        DemoBox {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement =
                    Arrangement.spacedBy(
                        AETheme.spacing.sm,
                    ),
                modifier = Modifier.horizontalScroll(rememberScrollState()),
            ) {
                AEBadge(
    label =
                        stringResource(
                            Res.string.intro_badge_android,
                        ),
                )
                AEBadge(
    label =
                        stringResource(
                            Res.string.intro_badge_ios,
                        ),
                )
                AEBadge(
    label =
                        stringResource(
                            Res.string.intro_badge_desktop,
                        ),
                )
                AEBadge(
    label =
                        stringResource(
                            Res.string.intro_badge_web,
                        ),
                )
            }
        }

        Spacer(Modifier.height(AETheme.spacing.md))

        AEText(
            text = stringResource(Res.string.intro_platform_body),
            style = AETheme.typography.bodyMedium,
        )
    }

    DocSection(
        stringResource(Res.string.intro_section_component_count),
    ) {
        DemoBox {
            Column(
                verticalArrangement =
                    Arrangement.spacedBy(
                        AETheme.spacing.sm,
                    ),
            ) {
                StatRow(
                    stringResource(
                        Res.string.intro_stat_total_components,
                    ),
                    stringResource(
                        Res.string.intro_stat_total_components_value,
                    ),
                )
                StatRow(
                    stringResource(
                        Res.string.intro_stat_animation_enums,
                    ),
                    stringResource(
                        Res.string.intro_stat_animation_enums_value,
                    ),
                )
                StatRow(
                    stringResource(
                        Res.string.intro_stat_style_presets,
                    ),
                    stringResource(
                        Res.string.intro_stat_style_presets_value,
                    ),
                )
                StatRow(
                    stringResource(
                        Res.string.intro_stat_color_palettes,
                    ),
                    stringResource(
                        Res.string.intro_stat_color_palettes_value,
                    ),
                )
                StatRow(
                    stringResource(
                        Res.string.intro_stat_accent_colors,
                    ),
                    stringResource(
                        Res.string.intro_stat_accent_colors_value,
                    ),
                )
            }
        }
    }

    DocSection(
        stringResource(Res.string.intro_section_quick_example),
    ) {
        CodeBlock(
            """
AETheme(
    colors = AEPalette.Zinc.resolve(isDark = true),
) {
    Button(
        text = "Click me",
        onClick = { /* ... */ },
        variant = ButtonVariant.Default,
        animation = ButtonAnimation.Scale,
    )
}
            """.trimIndent(),
        )
    }
}

@Composable
private fun FeatureItem(
    title: String,
    description: String,
) {
    Column {
        AEText(
            text = title,
            style = AETheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(AETheme.spacing.xs))
        AEText(
            text = description,
            style = AETheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun StatRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        AEText(text = label, style = AETheme.typography.bodyMedium)
        AEBadge(
    label = value,
            variant = AEBadgeVariant.Outline,
        )
    }
}
