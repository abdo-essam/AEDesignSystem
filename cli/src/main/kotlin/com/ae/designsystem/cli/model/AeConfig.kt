package com.ae.designsystem.cli.model

import kotlinx.serialization.Serializable
import com.ae.designsystem.cli.CliVersion

@Serializable
data class AeConfig(
    val foundation: String = CliVersion.FOUNDATION,
    val registry: String = "https://aedesignsystem.com/r",
    val packageName: String = "",
    val componentsDir: String = "",
)
