package com.ae.designsystem.foundation.tokens

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable

/** Motion / animation tokens — durations and easing curves. */
@Immutable
public data class AEMotion(
    /** Fast micro-interactions (hover, press) — 150ms */
    val durationFast: Int = 150,
    /** Standard transitions (expand, collapse) — 250ms */
    val durationMedium: Int = 250,
    /** Slow / emphasis animations (page transition) — 400ms */
    val durationSlow: Int = 400,
    /** Standard easing — slight acceleration, smooth deceleration */
    val easingStandard: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f),
    /** Emphasized easing — dramatic entry/exit */
    val easingEmphasized: Easing = CubicBezierEasing(0.05f, 0.7f, 0.1f, 1f),
    /** Decelerate easing — for elements entering the screen */
    val easingDecelerate: Easing = CubicBezierEasing(0f, 0f, 0.2f, 1f),
)
