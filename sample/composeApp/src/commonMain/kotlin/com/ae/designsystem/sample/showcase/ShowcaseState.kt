package com.ae.designsystem.sample.showcase

import com.ae.designsystem.foundation.color.AEAccent
import com.ae.designsystem.foundation.color.AEPalette
import com.ae.designsystem.foundation.tokens.AEStylePreset

data class ShowcaseState(
    val palette: AEPalette = AEPalette.Zinc,
    val accent: AEAccent = AEAccent.Blue,
    val stylePreset: AEStylePreset = AEStylePreset.Default,
)
