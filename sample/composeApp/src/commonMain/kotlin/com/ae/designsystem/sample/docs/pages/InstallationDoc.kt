package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.install_android_body
import aedesignsystem.sample.composeapp.generated.resources.install_android_note
import aedesignsystem.sample.composeapp.generated.resources.install_copy_paste_body
import aedesignsystem.sample.composeapp.generated.resources.install_desc
import aedesignsystem.sample.composeapp.generated.resources.install_kmp_body
import aedesignsystem.sample.composeapp.generated.resources.install_kmp_note
import aedesignsystem.sample.composeapp.generated.resources.install_module_components_desc
import aedesignsystem.sample.composeapp.generated.resources.install_module_foundation_desc
import aedesignsystem.sample.composeapp.generated.resources.install_modules_body
import aedesignsystem.sample.composeapp.generated.resources.install_req_compose_desc
import aedesignsystem.sample.composeapp.generated.resources.install_req_kotlin_desc
import aedesignsystem.sample.composeapp.generated.resources.install_req_material3_desc
import aedesignsystem.sample.composeapp.generated.resources.install_req_minsdk_desc
import aedesignsystem.sample.composeapp.generated.resources.install_section_android
import aedesignsystem.sample.composeapp.generated.resources.install_section_copy_paste
import aedesignsystem.sample.composeapp.generated.resources.install_section_kmp
import aedesignsystem.sample.composeapp.generated.resources.install_section_modules
import aedesignsystem.sample.composeapp.generated.resources.install_section_requirements
import aedesignsystem.sample.composeapp.generated.resources.install_section_verify
import aedesignsystem.sample.composeapp.generated.resources.install_step_1
import aedesignsystem.sample.composeapp.generated.resources.install_step_2
import aedesignsystem.sample.composeapp.generated.resources.install_step_3
import aedesignsystem.sample.composeapp.generated.resources.install_step_4
import aedesignsystem.sample.composeapp.generated.resources.install_title
import aedesignsystem.sample.composeapp.generated.resources.install_verify_body
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.components.CodeBlock
import com.ae.designsystem.sample.docs.components.ComponentPageHeader
import com.ae.designsystem.sample.docs.components.DocSection
import com.ae.designsystem.sample.docs.components.PropInfo
import com.ae.designsystem.sample.docs.components.PropsTable
import com.ae.designsystem.foundation.theme.AETheme

/**
 * Installation / setup documentation page.
 *
 * Covers module structure, Gradle dependencies, and
 * platform-specific setup for both KMP and Android-only.
 */
@Composable
fun InstallationDoc() {
    ComponentPageHeader(
        name = stringResource(Res.string.install_title),
        description = stringResource(Res.string.install_desc),
    )

    DocSection(
        stringResource(Res.string.install_section_modules),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.install_modules_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.md))

        PropsTable(
            props =
                listOf(
                    PropInfo(
                        name = ":foundation",
                        type = "Theme System",
                        default = "\u2014",
                        description =
                            stringResource(
                                Res.string.install_module_foundation_desc,
                            ),
                    ),
                    PropInfo(
                        name = ":components",
                        type = "UI Components",
                        default = "\u2014",
                        description =
                            stringResource(
                                Res.string.install_module_components_desc,
                            ),
                    ),
                ),
        )
    }

    DocSection(
        stringResource(Res.string.install_section_kmp),
    ) {
        AEText(
            text = stringResource(Res.string.install_kmp_body),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
// settings.gradle.kts
include(":aedesignsystem:components")
include(":aedesignsystem:foundation")

// Or as a published dependency:
// build.gradle.kts (shared module)
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(
                "com.ae.designsystem:components:<version>"
            )
        }
    }
}
            """.trimIndent(),
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        AEText(
            text = stringResource(Res.string.install_kmp_note),
            style = AETheme.typography.bodyMedium,
        )
    }

    DocSection(
        stringResource(Res.string.install_section_android),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.install_android_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
// build.gradle.kts (app module)
dependencies {
    implementation(
        "com.ae.designsystem:components-android:<version>"
    )
}
            """.trimIndent(),
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        AEText(
            text =
                stringResource(
                    Res.string.install_android_note,
                ),
            style = AETheme.typography.bodyMedium,
        )
    }

    DocSection(
        stringResource(Res.string.install_section_copy_paste),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.install_copy_paste_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        Column(
            verticalArrangement =
                Arrangement.spacedBy(AETheme.spacing.xs),
        ) {
            StepItem(
                step = "1",
                text =
                    stringResource(
                        Res.string.install_step_1,
                    ),
            )
            StepItem(
                step = "2",
                text =
                    stringResource(
                        Res.string.install_step_2,
                    ),
            )
            StepItem(
                step = "3",
                text =
                    stringResource(
                        Res.string.install_step_3,
                    ),
            )
            StepItem(
                step = "4",
                text =
                    stringResource(
                        Res.string.install_step_4,
                    ),
            )
        }
    }

    DocSection(
        stringResource(Res.string.install_section_requirements),
    ) {
        PropsTable(
            props =
                listOf(
                    PropInfo(
                        name = "Kotlin",
                        type = "2.0+",
                        default = "\u2014",
                        description =
                            stringResource(
                                Res.string.install_req_kotlin_desc,
                            ),
                    ),
                    PropInfo(
                        name = "Compose",
                        type = "1.7+",
                        default = "\u2014",
                        description =
                            stringResource(
                                Res.string.install_req_compose_desc,
                            ),
                    ),
                    PropInfo(
                        name = "Material3",
                        type = "Not needed",
                        default = "\u2014",
                        description =
                            stringResource(
                                Res.string.install_req_material3_desc,
                            ),
                    ),
                    PropInfo(
                        name = "minSdk",
                        type = "24 (Android)",
                        default = "\u2014",
                        description =
                            stringResource(
                                Res.string.install_req_minsdk_desc,
                            ),
                    ),
                ),
        )
    }

    DocSection(
        stringResource(Res.string.install_section_verify),
    ) {
        AEText(
            text =
                stringResource(
                    Res.string.install_verify_body,
                ),
            style = AETheme.typography.bodyMedium,
        )

        Spacer(Modifier.height(AETheme.spacing.sm))

        CodeBlock(
            """
import com.ae.designsystem.components.ui.text.AEText

@Composable
fun App() {
    val colors = AEPalette.Zinc.resolve(isDark = true)

    AETheme(colors = colors) {
        Button(
            text = "Hello AEDesignSystem!",
            onClick = { },
        )
    }
}
            """.trimIndent(),
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
