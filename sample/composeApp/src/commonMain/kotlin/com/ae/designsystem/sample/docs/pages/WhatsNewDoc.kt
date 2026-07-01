package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.*
import aedesignsystem.sample.composeapp.generated.resources.Res
import com.ae.designsystem.components.ui.badge.AEBadge
import com.ae.designsystem.components.ui.badge.AEBadgeVariant
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.BulletList
import com.ae.designsystem.sample.docs.components.CodeBlock
import com.ae.designsystem.sample.docs.components.ComponentPageHeader
import com.ae.designsystem.sample.docs.components.DocSection
import com.ae.designsystem.foundation.theme.AETheme

/**
 * "What's New in 0.3.0" release notes documentation page.
 *
 * Summarises the major changes since 0.2.0 for developers
 * upgrading or evaluating AEDesignSystem.
 */
@Composable
fun WhatsNewDoc() {
    ComponentPageHeader(
        name = stringResource(Res.string.whats_new_title),
        description = stringResource(Res.string.whats_new_desc),
    )

    // ─── Accessibility Overhaul ────────────────────────────
    DocSection(stringResource(Res.string.whats_new_section_a11y)) {
        AEText(
            text = stringResource(Res.string.whats_new_a11y_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        BulletList(items = listOf(
                    stringResource(Res.string.whats_new_a11y_item_roles),
                    stringResource(Res.string.whats_new_a11y_item_collection),
                    stringResource(Res.string.whats_new_a11y_item_keyboard),
                    stringResource(Res.string.whats_new_a11y_item_live_region),
                    stringResource(Res.string.whats_new_a11y_item_touch),
                ))
    }

    // ─── Distinct Color Palettes ───────────────────────────
    DocSection(stringResource(Res.string.whats_new_section_palettes)) {
        AEText(
            text = stringResource(Res.string.whats_new_palettes_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        Row(
            horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.sm),
        ) {
            AEBadge(label = "Zinc", variant = AEBadgeVariant.Outline)
            AEBadge(label = "Slate", variant = AEBadgeVariant.Outline)
            AEBadge(label = "Stone", variant = AEBadgeVariant.Outline)
            AEBadge(label = "Gray", variant = AEBadgeVariant.Outline)
            AEBadge(label = "Neutral", variant = AEBadgeVariant.Outline)
        }
    }

    // ─── Spacing & Layout Fixes ────────────────────────────
    DocSection(stringResource(Res.string.whats_new_section_spacing)) {
        AEText(
            text = stringResource(Res.string.whats_new_spacing_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        BulletList(items = listOf(
                    stringResource(Res.string.whats_new_spacing_item_card),
                    stringResource(Res.string.whats_new_spacing_item_form),
                    stringResource(Res.string.whats_new_spacing_item_popup),
                    stringResource(Res.string.whats_new_spacing_item_menu),
                ))

        Spacer(Modifier.height(AETheme.spacing.md))

        CodeBlock(
            """
// Popover now disables minTouchTarget for dense content
CompositionLocalProvider(
    LocalMinTouchTarget provides 0.dp,
) {
    // Buttons render at natural size here
}
            """.trimIndent(),
        )
    }

    // ─── New Components ────────────────────────────────────
    DocSection(stringResource(Res.string.whats_new_section_components)) {
        AEText(
            text = stringResource(Res.string.whats_new_components_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        CodeBlock(
            """
Scaffold(
    fab = {
        Fab(
            onClick = { /* action */ },
            icon = { Icon(AEIcons.Plus, "Add") },
        )
    },
    fabPosition = FabPosition.End,
) { padding ->
    // content
}
            """.trimIndent(),
        )
    }

    // ─── Text & Typography Fixes ───────────────────────────
    DocSection(stringResource(Res.string.whats_new_section_text)) {
        AEText(
            text = stringResource(Res.string.whats_new_text_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        BulletList(items = listOf(
                    stringResource(Res.string.whats_new_text_item_merge),
                    stringResource(Res.string.whats_new_text_item_kbd),
                    stringResource(Res.string.whats_new_text_item_branding),
                ))
    }

    // ─── Documentation ─────────────────────────────────────
    DocSection(stringResource(Res.string.whats_new_section_docs)) {
        AEText(
            text = stringResource(Res.string.whats_new_docs_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        BulletList(items = listOf(
                    stringResource(Res.string.whats_new_docs_item_tabs),
                    stringResource(Res.string.whats_new_docs_item_preview),
                    stringResource(Res.string.whats_new_docs_item_search),
                    stringResource(Res.string.whats_new_docs_item_why),
                ))
    }

    // ─── Example Cards Polish ──────────────────────────────
    DocSection(stringResource(Res.string.whats_new_section_examples)) {
        AEText(
            text = stringResource(Res.string.whats_new_examples_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        BulletList(items = listOf(
                    stringResource(Res.string.whats_new_examples_item_home),
                    stringResource(Res.string.whats_new_examples_item_creator),
                ))
    }

    // ─── Migration ─────────────────────────────────────────
    DocSection(stringResource(Res.string.whats_new_section_migration)) {
        AEText(
            text = stringResource(Res.string.whats_new_migration_body),
            style = AETheme.typography.bodyMedium,
        )
    }
}
