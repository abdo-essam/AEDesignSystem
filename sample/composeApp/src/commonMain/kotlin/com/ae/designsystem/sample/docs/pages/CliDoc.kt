package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.cli_add_body
import aedesignsystem.sample.composeapp.generated.resources.cli_add_deps
import aedesignsystem.sample.composeapp.generated.resources.cli_add_flags_title
import aedesignsystem.sample.composeapp.generated.resources.cli_config_body
import aedesignsystem.sample.composeapp.generated.resources.cli_desc
import aedesignsystem.sample.composeapp.generated.resources.cli_how_note
import aedesignsystem.sample.composeapp.generated.resources.cli_how_step_1
import aedesignsystem.sample.composeapp.generated.resources.cli_how_step_2
import aedesignsystem.sample.composeapp.generated.resources.cli_how_step_3
import aedesignsystem.sample.composeapp.generated.resources.cli_how_step_4
import aedesignsystem.sample.composeapp.generated.resources.cli_init_body
import aedesignsystem.sample.composeapp.generated.resources.cli_init_creates
import aedesignsystem.sample.composeapp.generated.resources.cli_install_body
import aedesignsystem.sample.composeapp.generated.resources.cli_install_manual
import aedesignsystem.sample.composeapp.generated.resources.cli_list_body
import aedesignsystem.sample.composeapp.generated.resources.cli_section_add
import aedesignsystem.sample.composeapp.generated.resources.cli_section_config
import aedesignsystem.sample.composeapp.generated.resources.cli_section_how
import aedesignsystem.sample.composeapp.generated.resources.cli_section_init
import aedesignsystem.sample.composeapp.generated.resources.cli_section_install
import aedesignsystem.sample.composeapp.generated.resources.cli_section_list
import aedesignsystem.sample.composeapp.generated.resources.cli_title
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.docs.components.CodeBlock
import com.ae.designsystem.sample.docs.components.ComponentPageHeader
import com.ae.designsystem.sample.docs.components.DocSection
import com.ae.designsystem.sample.docs.components.PropInfo
import com.ae.designsystem.sample.docs.components.PropsTable

@Composable
fun CliDoc() {
    ComponentPageHeader(
        name = stringResource(Res.string.cli_title),
        description = stringResource(Res.string.cli_desc),
    )

    // ─── Install ────────────────────────────────────────────
    DocSection(stringResource(Res.string.cli_section_install)) {
        AEText(
            text = stringResource(Res.string.cli_install_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
curl -fsSL https://aedesignsystem.dev/install.sh | bash
            """.trimIndent(),
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        AEText(
            text = stringResource(Res.string.cli_install_manual),
            style = AETheme.typography.bodyMedium,
        )
    }

    // ─── Init ───────────────────────────────────────────────
    DocSection(stringResource(Res.string.cli_section_init)) {
        AEText(
            text = stringResource(Res.string.cli_init_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
aedesignsystem init

# Interactive prompts:
# Package name (e.g. com.example.ui): com.myapp.ui
# Source set [commonMain]:
#
# Created ae.json
            """.trimIndent(),
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        AEText(
            text = stringResource(Res.string.cli_init_creates),
            style = AETheme.typography.bodyMedium,
        )
    }

    // ─── Add ────────────────────────────────────────────────
    DocSection(stringResource(Res.string.cli_section_add)) {
        AEText(
            text = stringResource(Res.string.cli_add_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
# Add a single component
aedesignsystem add button

# Add multiple components
aedesignsystem add button dialog card

# Add all components
aedesignsystem add --all

# Preview without writing files
aedesignsystem add button --dry-run

# Specify path and package inline (no config needed)
aedesignsystem add button --path ./ui --package com.myapp.ui
            """.trimIndent(),
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        AEText(
            text = stringResource(Res.string.cli_add_deps),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
$ aedesignsystem add button

Resolving dependencies...

  + Added button
  + Added icon (dependency)
  + Added spinner (dependency)
  + Added text (dependency)

  Ensure these dependencies are in your build.gradle.kts:
  implementation("com.ae.designsystem:foundation:1.0.0")
            """.trimIndent(),
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        AEText(
            text = stringResource(Res.string.cli_add_flags_title),
            style = AETheme.typography.headingMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        name = "--path",
                        type = "String",
                        default = "from config",
                        description = "Target directory for component files",
                    ),
                    PropInfo(
                        name = "--package",
                        type = "String",
                        default = "from config",
                        description = "Target package name for import rewriting",
                    ),
                    PropInfo(
                        name = "--dry-run",
                        type = "Flag",
                        default = "false",
                        description = "Preview file operations without writing",
                    ),
                    PropInfo(
                        name = "--overwrite",
                        type = "Flag",
                        default = "false",
                        description = "Overwrite existing files",
                    ),
                    PropInfo(
                        name = "--all",
                        type = "Flag",
                        default = "false",
                        description = "Add all components from the registry",
                    ),
                    PropInfo(
                        name = "--registry",
                        type = "String",
                        default = "aedsui.dev/r",
                        description = "Custom registry URL",
                    ),
                ),
        )
    }

    // ─── List ───────────────────────────────────────────────
    DocSection(stringResource(Res.string.cli_section_list)) {
        AEText(
            text = stringResource(Res.string.cli_list_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
$ aedsui list

  AEDesignSystem v1.0.0 — 40 components

  Layout:
    card                 A container with border and shadow.
    separator            A horizontal or vertical divider.
    accordion            Expandable content sections. [deps: icon, text]
    ...

  Forms:
    button               Interactive button with 6 variants. [deps: icon, spinner, text]
    input                Single-line text input.
    select               Dropdown select. [deps: icon, text]
    ...
            """.trimIndent(),
        )
    }

    // ─── Configuration ──────────────────────────────────────
    DocSection(stringResource(Res.string.cli_section_config)) {
        AEText(
            text = stringResource(Res.string.cli_config_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
// ae.json
{
  "foundation": "com.ae.designsystem:foundation:1.0.0",
  "registry": "https://aedsui.dev/r",
  "packageName": "com.myapp.ui",
  "componentsDir": "shared/src/commonMain/kotlin/com/myapp/ui"
}
            """.trimIndent(),
        )
    }

    // ─── How It Works ───────────────────────────────────────
    DocSection(stringResource(Res.string.cli_section_how)) {
        Column(
            verticalArrangement =
                Arrangement.spacedBy(AETheme.spacing.xs),
        ) {
            StepItem("1", stringResource(Res.string.cli_how_step_1))
            StepItem("2", stringResource(Res.string.cli_how_step_2))
            StepItem("3", stringResource(Res.string.cli_how_step_3))
            StepItem("4", stringResource(Res.string.cli_how_step_4))
        }

        Spacer(Modifier.height(AETheme.spacing.sm))

        AEText(
            text = stringResource(Res.string.cli_how_note),
            style = AETheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun StepItem(
    step: String,
    text: String,
) {
    AEText(
        text = "$step. $text",
        style = AETheme.typography.bodyMedium,
    )
}
