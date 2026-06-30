package com.ae.designsystem.sample.docs.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.*

import com.ae.designsystem.components.ui.button.AEButton
import com.ae.designsystem.components.ui.button.AEButtonSize
import com.ae.designsystem.components.ui.button.AEButtonVariant
import com.ae.designsystem.components.ui.tabs.AETabs
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.components.ui.tooltip.AETooltip
import com.ae.designsystem.components.ui.divider.AEDivider
import com.ae.designsystem.foundation.color.AEAccent
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.icons.AEIcon
import com.ae.designsystem.foundation.icons.AEIcons
import com.ae.designsystem.foundation.theme.AETheme
import com.ae.designsystem.sample.app.LocalAppState

// ─── Preview Theme Local ────────────────────────────────────

/**
 * Per-page dark/light preview override for component demos.
 *
 * - `null` → follow the global site theme (default)
 * - `true` → force dark mode for demos
 * - `false` → force light mode for demos
 */
val LocalDocPreviewIsDark = compositionLocalOf<Boolean?> { null }

// ─── Page Header ─────────────────────────────────────────────

/**
 * Component page header with title and description.
 */
@Composable
fun ComponentPageHeader(
    name: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        AEText(
            text = name,
            style = AETheme.typography.headingLarge,
        )
        Spacer(Modifier.height(AETheme.spacing.xs))
        AEText(
            text = description,
            color = AETheme.colors.textMuted,
            style = AETheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(AETheme.spacing.lg))
        AEDivider()
    }
}

// ─── Section Header ──────────────────────────────────────────

/**
 * Section header within a component doc page.
 */
@Composable
fun DocSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(Modifier.height(AETheme.spacing.xl))
        AEText(
            text = title,
            style = AETheme.typography.headingMedium,
        )
        Spacer(Modifier.height(AETheme.spacing.md))
        content()
    }
}

// ─── Demo Box ────────────────────────────────────────────────

/**
 * A bordered container for live component demos.
 */
@Composable
fun DemoBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val previewIsDark = LocalDocPreviewIsDark.current

    if (previewIsDark != null) {
        AETheme(palette = AEPalette.Zinc, darkTheme = previewIsDark) {
            DemoBoxContent(modifier = modifier, content = content)
        }
    } else {
        DemoBoxContent(modifier = modifier, content = content)
    }
}

@Composable
private fun DemoBoxContent(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val radius = AETheme.radius
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    AETheme.colors.border,
                    androidx.compose.foundation.shape.RoundedCornerShape(radius.lg),
                ).background(
                    AETheme.colors.surface,
                    androidx.compose.foundation.shape.RoundedCornerShape(radius.lg),
                ).clip(androidx.compose.foundation.shape.RoundedCornerShape(radius.lg))
                .padding(AETheme.spacing.xl),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

// ─── Variant Selector ────────────────────────────────────────

/**
 * A row of selectable chips for switching demo variants.
 */
@Composable
fun VariantSelector(
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val radius = AETheme.radius
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
        horizontalArrangement =
            Arrangement.spacedBy(AETheme.spacing.xs),
    ) {
        options.forEach { option ->
            val isActive = option == selected
            val interactionSource =
                remember { MutableInteractionSource() }
            val isHovered by
                interactionSource.collectIsHoveredAsState()

            val bg =
                when {
                    isActive -> AETheme.colors.accent
                    isHovered -> AETheme.colors.surfaceHover
                    else -> AETheme.colors.background
                }
            val fg =
                if (isActive) {
                    AETheme.colors.textOnAccent
                } else {
                    AETheme.colors.textPrimary
                }

            Box(
                modifier =
                    Modifier
                        .hoverable(interactionSource)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) { onSelect(option) }
                        .border(
                            if (isActive) 0.dp else 1.dp,
                            if (isActive) {
                                AETheme.colors.accent
                            } else {
                                AETheme.colors.border
                            },
                            androidx.compose.foundation.shape.RoundedCornerShape(radius.md),
                        ).background(bg, androidx.compose.foundation.shape.RoundedCornerShape(radius.md))
                        .padding(
                            horizontal = AETheme.spacing.sm,
                            vertical = AETheme.spacing.xs,
                        ),
            ) {
                BasicText(
                    text = option,
                    style =
                        AETheme.typography.bodySmall.merge(
                            TextStyle(color = fg),
                        ),
                )
            }
        }
    }
}

// ─── Code Block ──────────────────────────────────────────────

/**
 * A styled code block with monospace font and dark background.
 */
@Composable
fun CodeBlock(
    code: String,
    modifier: Modifier = Modifier,
) {
    val clipboardManager = LocalClipboardManager.current
    var showCopied by remember { mutableStateOf(false) }
    val radius = AETheme.radius

    LaunchedEffect(showCopied) {
        if (showCopied) {
            delay(1500L)
            showCopied = false
        }
    }

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .background(
                    AETheme.colors.backgroundSecondary,
                    androidx.compose.foundation.shape.RoundedCornerShape(radius.md),
                ).clip(androidx.compose.foundation.shape.RoundedCornerShape(radius.md)),
    ) {
        Box(
            modifier =
                Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(AETheme.spacing.md)
                    .padding(end = 48.dp),
        ) {
            SelectionContainer {
                BasicText(
                    text = code,
                    style =
                        AETheme.typography.bodySmall.merge(
                            TextStyle(
                                color = AETheme.colors.textPrimary,
                                fontFamily = FontFamily.Monospace,
                            ),
                        ),
                )
            }
        }

        // Copy button
        Box(
            modifier =
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(AETheme.spacing.xs)
                    .background(
                        AETheme.colors.backgroundSecondary.copy(alpha = 0.9f),
                        androidx.compose.foundation.shape.RoundedCornerShape(radius.sm),
                    ).clip(androidx.compose.foundation.shape.RoundedCornerShape(radius.sm))
                    .clickable {
                        clipboardManager.setText(AnnotatedString(code))
                        showCopied = true
                    }.padding(
                        horizontal = AETheme.spacing.sm,
                        vertical = AETheme.spacing.xs,
                    ),
        ) {
            BasicText(
                text = if (showCopied) "Copied!" else "Copy",
                style =
                    AETheme.typography.bodySmall.merge(
                        TextStyle(
                            color = AETheme.colors.textSecondary,
                            fontFamily = FontFamily.Monospace,
                            fontSize = 11.sp,
                        ),
                    ),
            )
        }
    }
}

// ─── Props Table ─────────────────────────────────────────────

/**
 * A single prop/parameter row.
 */
data class PropInfo(
    val name: String,
    val type: String,
    val default: String,
    val description: String,
)

/**
 * Renders a property reference table for a component's API.
 */
@Composable
fun PropsTable(
    props: List<PropInfo>,
    modifier: Modifier = Modifier,
) {
    val radius = AETheme.radius
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    AETheme.colors.border,
                    androidx.compose.foundation.shape.RoundedCornerShape(radius.md),
                ).clip(androidx.compose.foundation.shape.RoundedCornerShape(radius.md)),
    ) {
        // Header row
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(AETheme.colors.backgroundSecondary)
                    .padding(
                        horizontal = AETheme.spacing.md,
                        vertical = AETheme.spacing.sm,
                    ),
        ) {
            PropHeaderCell(
                stringResource(Res.string.prop_header_prop),
                Modifier.weight(0.2f),
            )
            PropHeaderCell(
                stringResource(Res.string.prop_header_type),
                Modifier.weight(0.2f),
            )
            PropHeaderCell(
                stringResource(Res.string.prop_header_default),
                Modifier.weight(0.15f),
            )
            PropHeaderCell(
                stringResource(Res.string.prop_header_description),
                Modifier.weight(0.45f),
            )
        }

        // Data rows
        props.forEachIndexed { index, prop ->
            if (index > 0) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(AETheme.colors.border),
                )
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = AETheme.spacing.md,
                            vertical = AETheme.spacing.sm,
                        ),
            ) {
                PropCell(
                    prop.name,
                    Modifier.weight(0.2f),
                    isMono = true,
                )
                PropCell(
                    prop.type,
                    Modifier.weight(0.2f),
                    isMono = true,
                )
                PropCell(
                    prop.default,
                    Modifier.weight(0.15f),
                    isMono = true,
                )
                PropCell(
                    prop.description,
                    Modifier.weight(0.45f),
                )
            }
        }
    }
}

@Composable
private fun PropHeaderCell(
    text: String,
    modifier: Modifier = Modifier,
) {
    BasicText(
        text = text,
        modifier = modifier,
        style =
            AETheme.typography.bodySmall.merge(
                TextStyle(
                    color = AETheme.colors.textPrimary,
                    fontWeight = FontWeight.SemiBold,
                ),
            ),
    )
}

@Composable
private fun PropCell(
    text: String,
    modifier: Modifier = Modifier,
    isMono: Boolean = false,
) {
    BasicText(
        text = text,
        modifier = modifier,
        style =
            AETheme.typography.bodySmall.merge(
                TextStyle(
                    color = AETheme.colors.textSecondary,
                    fontFamily =
                        if (isMono) {
                            FontFamily.Monospace
                        } else {
                            FontFamily.Default
                        },
                ),
            ),
    )
}

// ─── Tabbed Doc Page ────────────────────────────────────────

/**
 * Three-tab documentation layout: Overview, Usage, API.
 */
@Composable
fun TabbedDocPage(
    modifier: Modifier = Modifier,
    overview: @Composable () -> Unit,
    usage: @Composable () -> Unit,
    api: @Composable () -> Unit,
) {
    var selectedTab by remember { mutableStateOf(0) }
    var previewIsDark by remember { mutableStateOf<Boolean?>(null) }

    val tabLabels =
        listOf(
            stringResource(Res.string.doc_tab_overview),
            stringResource(Res.string.doc_tab_usage),
            stringResource(Res.string.doc_tab_api),
        )

    val tooltipText =
        when (previewIsDark) {
            null -> stringResource(Res.string.doc_preview_auto)
            true -> stringResource(Res.string.doc_preview_dark)
            false -> stringResource(Res.string.doc_preview_light)
        }

    CompositionLocalProvider(LocalDocPreviewIsDark provides previewIsDark) {
        Column(modifier = modifier.fillMaxWidth()) {
            Spacer(Modifier.height(AETheme.spacing.lg))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    AETabs(
                        tabs = tabLabels,
                        selectedIndex = selectedTab,
                        onTabSelected = { selectedTab = it },
                    )
                }

                Spacer(Modifier.width(AETheme.spacing.md))

                AETooltip(tooltip = tooltipText) {
                    AEButton(
                        onClick = {
                            previewIsDark =
                                when (previewIsDark) {
                                    null -> false // auto → light
                                    false -> true // light → dark
                                    true -> null // dark → auto
                                }
                        },
                        variant =
                            if (previewIsDark != null) {
                                AEButtonVariant.Outlined
                            } else {
                                AEButtonVariant.Ghost
                            },
                        size = AEButtonSize.Small,
                    ) {
                        AEIcon(
                            token =
                                when (previewIsDark) {
                                    false -> AEIcons.Sun
                                    true -> AEIcons.Moon
                                    null -> AEIcons.Sun
                                },
                            contentDescription = tooltipText,
                            tint = AETheme.colors.textPrimary,
                            size = 16.dp,
                        )
                    }
                }
            }

            Spacer(Modifier.height(AETheme.spacing.md))

            when (selectedTab) {
                0 -> overview()
                1 -> usage()
                2 -> api()
            }
        }
    }
}

// ─── Do & Don't ─────────────────────────────────────────────

/**
 * Side-by-side Do / Don't panels for usage guidelines.
 */
@Composable
fun DoAndDont(
    doContent: @Composable () -> Unit,
    doDescription: String,
    dontContent: @Composable () -> Unit,
    dontDescription: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
    ) {
        // Do panel
        DoOrDontPanel(
            isDo = true,
            description = doDescription,
            modifier = Modifier.weight(1f),
            content = doContent,
        )

        // Don't panel
        DoOrDontPanel(
            isDo = false,
            description = dontDescription,
            modifier = Modifier.weight(1f),
            content = dontContent,
        )
    }
}

@Composable
private fun DoOrDontPanel(
    isDo: Boolean,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val accentColor =
        if (isDo) {
            AETheme.colors.success
        } else {
            AETheme.colors.destructive
        }
    val radius = AETheme.radius

    Column(
        modifier =
            modifier
                .fillMaxHeight()
                .border(1.dp, AETheme.colors.border, androidx.compose.foundation.shape.RoundedCornerShape(radius.lg))
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(radius.lg)),
    ) {
        // Accent bar
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(accentColor),
        )

        Column(
            modifier = Modifier.padding(AETheme.spacing.md),
        ) {
            // Label row with icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
            ) {
                AEIcon(
                    token = if (isDo) AEIcons.Check else AEIcons.Close,
                    contentDescription = null,
                    tint = accentColor,
                    size = 16.dp,
                )
                AEText(
                    text =
                        if (isDo) {
                            stringResource(Res.string.do_label)
                        } else {
                            stringResource(Res.string.dont_label)
                        },
                    style = AETheme.typography.bodySmall,
                    color = accentColor,
                )
            }

            Spacer(Modifier.height(AETheme.spacing.md))

            // Demo area — respects per-page preview theme
            val previewIsDark = LocalDocPreviewIsDark.current
            val demoArea: @Composable () -> Unit = {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(AETheme.colors.surface, androidx.compose.foundation.shape.RoundedCornerShape(radius.md))
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(radius.md))
                            .padding(AETheme.spacing.md),
                    contentAlignment = Alignment.Center,
                ) {
                    content()
                }
            }
            if (previewIsDark != null) {
                AETheme(palette = AEPalette.Zinc, darkTheme = previewIsDark) {
                    demoArea()
                }
            } else {
                demoArea()
            }

            Spacer(Modifier.height(AETheme.spacing.sm))

            // Description
            AEText(
                text = description,
                color = AETheme.colors.textMuted,
                style = AETheme.typography.bodySmall,
            )
        }
    }
}

// ─── Component Family ───────────────────────────────────────

/**
 * "See also" chip row linking to related components.
 */
@Composable
fun ComponentFamily(
    related: List<Pair<String, String>>,
    currentId: String,
    onNavigate: (String) -> Unit = LocalDocNavigation.current,
    modifier: Modifier = Modifier,
) {
    if (related.size <= 1) return
    val radius = AETheme.radius

    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(Modifier.height(AETheme.spacing.md))

        Row(
            horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AEText(
                text = stringResource(Res.string.family_see_also),
                color = AETheme.colors.textMuted,
                style = AETheme.typography.bodySmall,
            )

            Spacer(Modifier.width(AETheme.spacing.xs))

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.xs),
            ) {
                related.forEach { (id, name) ->
                    val isCurrent = id == currentId
                    val interactionSource = remember { MutableInteractionSource() }
                    val isHovered by interactionSource.collectIsHoveredAsState()

                    val bg =
                        when {
                            isCurrent -> AETheme.colors.accent
                            isHovered -> AETheme.colors.surfaceHover
                            else -> Color.Transparent
                        }
                    val fg =
                        if (isCurrent) {
                            AETheme.colors.textOnAccent
                        } else {
                            AETheme.colors.textPrimary
                        }
                    val borderColor =
                        if (isCurrent) {
                            AETheme.colors.accent
                        } else {
                            AETheme.colors.border
                        }

                    Box(
                        modifier =
                            Modifier
                                .hoverable(interactionSource)
                                .then(
                                    if (isCurrent) {
                                        Modifier
                                    } else {
                                        Modifier.clickable(
                                            interactionSource = interactionSource,
                                            indication = null,
                                        ) { onNavigate(id) }
                                    },
                                ).border(1.dp, borderColor, androidx.compose.foundation.shape.RoundedCornerShape(radius.md))
                                .background(bg, androidx.compose.foundation.shape.RoundedCornerShape(radius.md))
                                .padding(
                                    horizontal = AETheme.spacing.sm,
                                    vertical = AETheme.spacing.xs,
                                ),
                    ) {
                        BasicText(
                            text = name,
                            style =
                                AETheme.typography.bodySmall.merge(
                                    TextStyle(color = fg),
                                ),
                        )
                    }
                }
            }
        }
    }
}
