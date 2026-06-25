package com.ae.designsystem.sample.showcase

import com.ae.designsystem.foundation.color.AEAccent
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.tokens.AEStylePreset

sealed interface ShowcaseAction {
    data class ChangePalette(val palette: AEPalette) : ShowcaseAction
    data class ChangeAccent(val accent: AEAccent) : ShowcaseAction
    data class ChangeStylePreset(val preset: AEStylePreset) : ShowcaseAction
}
