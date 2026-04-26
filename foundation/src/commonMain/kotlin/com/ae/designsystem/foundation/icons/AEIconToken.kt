package com.ae.designsystem.foundation.icons

import androidx.compose.runtime.Immutable

/**
 * A semantic icon identifier — decouples component code from actual
 * icon vector data.
 *
 * Components reference icons via tokens like [AEIcons.Heart] instead
 * of direct [ImageVector] references. The actual rendering is resolved
 * at runtime by the current [AEIconPack].
 *
 * @property name Unique string key used for icon pack resolution.
 */
@Immutable
public data class AEIconToken(val name: String)
