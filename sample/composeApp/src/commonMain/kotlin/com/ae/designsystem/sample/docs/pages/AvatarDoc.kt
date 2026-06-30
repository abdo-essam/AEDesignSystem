package com.ae.designsystem.sample.docs.pages

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.*

import com.ae.designsystem.components.ui.avatar.AEAvatar
import com.ae.designsystem.components.ui.avatar.AEAvatarSize
import com.ae.designsystem.components.ui.text.AEText
import com.ae.designsystem.sample.docs.catalog.ComponentFamilies
import com.ae.designsystem.sample.docs.components.*
import com.ae.designsystem.foundation.theme.AETheme

@Composable
fun AvatarDoc() {
    ComponentPageHeader(
        name = "Avatar",
        description = "Circular user identity component with fallback initials or icon.",
    )

    TabbedDocPage(
        overview = { AvatarOverviewTab() },
        usage = { AvatarUsageTab() },
        api = { AvatarApiTab() },
    )
}

@Composable
private fun AvatarOverviewTab() {
    DocSection("Sizes") {
        DemoBox {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md),
                modifier = Modifier.padding(AETheme.spacing.sm)
            ) {
                AEAvatar(initials = "XS", size = AEAvatarSize.XSmall)
                AEAvatar(initials = "S", size = AEAvatarSize.Small)
                AEAvatar(initials = "M", size = AEAvatarSize.Medium)
                AEAvatar(initials = "L", size = AEAvatarSize.Large)
                AEAvatar(initials = "XL", size = AEAvatarSize.XLarge)
            }
        }
    }

    DocSection("Fallbacks") {
        DemoBox {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md)
            ) {
                // Initials fallback
                AEAvatar(initials = "AE")
                // Icon fallback (when initials are null)
                AEAvatar()
            }
        }
    }

    DocSection("Online Status") {
        DemoBox {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AETheme.spacing.md)
            ) {
                AEAvatar(initials = "JD", showOnlineStatus = true, isOnline = true)
                AEAvatar(initials = "OF", showOnlineStatus = true, isOnline = false)
            }
        }
    }
}

@Composable
private fun AvatarUsageTab() {
    DocSection("Usage") {
        CodeBlock(
            """
AEAvatar(initials = "JD")
AEAvatar(size = AEAvatarSize.Large, initials = "AE")
AEAvatar(initials = "ON", showOnlineStatus = true, isOnline = true)
            """.trimIndent()
        )
    }

    DocSection("Guidelines") {
        DoAndDont(
            doContent = {
                AEAvatar(initials = "JD")
            },
            doDescription = "Use clear, 1-2 character initials for user avatars.",
            dontContent = {
                AEAvatar(initials = "JOHN DOE")
            },
            dontDescription = "Avoid long strings for fallback initials, as they will get truncated.",
        )
    }
}

@Composable
private fun AvatarApiTab() {
    DocSection("API Reference") {
        PropsTable(
            listOf(
                PropInfo("initials", "String?", "null", "Initials to display as fallback. Truncated to 2 chars."),
                PropInfo("size", "AEAvatarSize", "Medium", "Size preset (XSmall, Small, Medium, Large, XLarge)."),
                PropInfo("backgroundColor", "Color", "AETheme.colors.accentMuted", "Circle background color."),
                PropInfo("contentColor", "Color", "AETheme.colors.accent", "Text/icon foreground color."),
                PropInfo("borderColor", "Color?", "null", "Optional border stroke color."),
                PropInfo("showOnlineStatus", "Boolean", "false", "Whether to overlay status dot."),
                PropInfo("isOnline", "Boolean", "true", "Green (online) vs gray (offline) status color.")
            )
        )
    }
}
