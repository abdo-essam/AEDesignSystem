package com.ae.designsystem.sample.docs.catalog

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import aedesignsystem.sample.composeapp.generated.resources.Res
import aedesignsystem.sample.composeapp.generated.resources.guide_cli
import aedesignsystem.sample.composeapp.generated.resources.guide_installation
import aedesignsystem.sample.composeapp.generated.resources.guide_introduction
import aedesignsystem.sample.composeapp.generated.resources.guide_theming
import aedesignsystem.sample.composeapp.generated.resources.guide_whats_new
import com.ae.designsystem.sample.docs.pages.CliDoc
import com.ae.designsystem.sample.docs.pages.InstallationDoc
import com.ae.designsystem.sample.docs.pages.IntroductionDoc
import com.ae.designsystem.sample.docs.pages.ThemingDoc
import com.ae.designsystem.sample.docs.pages.WhatsNewDoc

object GuideIds {
    const val WHATS_NEW = "whats-new"
    const val INTRODUCTION = "introduction"
    const val INSTALLATION = "installation"
    const val THEMING = "theming"
    const val CLI = "cli"
}

data class GuidePage(
    val id: String,
    val nameRes: StringResource,
    val content: @Composable () -> Unit,
)

val guidePages =
    listOf(
        GuidePage(
            id = GuideIds.WHATS_NEW,
            nameRes = Res.string.guide_whats_new,
            content = { WhatsNewDoc() },
        ),
        GuidePage(
            id = GuideIds.INTRODUCTION,
            nameRes = Res.string.guide_introduction,
            content = { IntroductionDoc() },
        ),
        GuidePage(
            id = GuideIds.INSTALLATION,
            nameRes = Res.string.guide_installation,
            content = { InstallationDoc() },
        ),
        GuidePage(
            id = GuideIds.THEMING,
            nameRes = Res.string.guide_theming,
            content = { ThemingDoc() },
        ),
        GuidePage(
            id = GuideIds.CLI,
            nameRes = Res.string.guide_cli,
            content = { CliDoc() },
        ),
    )

val guidePageIds = guidePages.map { it.id }.toSet()
