package com.ae.designsystem.foundation.tokens

import androidx.compose.ui.unit.dp

/**
 * Style presets — named personalities that bundle spacing, radius,
 * and motion characteristics.
 */
public enum class AEStylePreset {
    /** Balanced defaults — 8dp radius, standard spacing, smooth motion */
    Default,
    /** Small radius, tight spacing, snappy motion — for dense UIs */
    Sharp,
    /** Large radius, generous spacing, bouncy motion — friendly feel */
    Soft,
    /** Minimal spacing for data-heavy screens */
    Compact,
    /** Extra-large radius, spacious, playful — expressive brand apps */
    Expressive;

    /** Resolve spacing tokens for this preset. */
    public fun toSpacing(): AESpacing = when (this) {
        Default -> AESpacing()
        Sharp -> AESpacing(xxs = 1.dp, xs = 2.dp, sm = 4.dp, md = 8.dp, lg = 12.dp, xl = 16.dp, xxl = 24.dp, xxxl = 32.dp)
        Soft -> AESpacing(xxs = 4.dp, xs = 6.dp, sm = 10.dp, md = 14.dp, lg = 20.dp, xl = 28.dp, xxl = 40.dp, xxxl = 56.dp)
        Compact -> AESpacing(xxs = 1.dp, xs = 2.dp, sm = 4.dp, md = 6.dp, lg = 8.dp, xl = 12.dp, xxl = 16.dp, xxxl = 24.dp)
        Expressive -> AESpacing(xxs = 4.dp, xs = 8.dp, sm = 12.dp, md = 16.dp, lg = 24.dp, xl = 32.dp, xxl = 48.dp, xxxl = 64.dp)
    }

    /** Resolve radius tokens for this preset. */
    public fun toRadius(): AERadius = when (this) {
        Default -> AERadius()
        Sharp -> AERadius(none = 0.dp, sm = 2.dp, md = 4.dp, lg = 6.dp, xl = 8.dp, full = 9999.dp)
        Soft -> AERadius(none = 0.dp, sm = 8.dp, md = 12.dp, lg = 16.dp, xl = 24.dp, full = 9999.dp)
        Compact -> AERadius(none = 0.dp, sm = 2.dp, md = 4.dp, lg = 6.dp, xl = 8.dp, full = 9999.dp)
        Expressive -> AERadius(none = 0.dp, sm = 12.dp, md = 16.dp, lg = 24.dp, xl = 32.dp, full = 9999.dp)
    }

    /** Resolve motion tokens for this preset. */
    public fun toMotion(): AEMotion = when (this) {
        Default -> AEMotion()
        Sharp -> AEMotion(durationFast = 100, durationMedium = 200, durationSlow = 300)
        Soft -> AEMotion(durationFast = 200, durationMedium = 350, durationSlow = 500)
        Compact -> AEMotion(durationFast = 100, durationMedium = 150, durationSlow = 250)
        Expressive -> AEMotion(durationFast = 200, durationMedium = 400, durationSlow = 600)
    }
}
