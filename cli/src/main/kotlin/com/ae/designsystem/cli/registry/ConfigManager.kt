package com.ae.designsystem.cli.registry

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.ae.designsystem.cli.model.AeConfig
import java.io.File

private val prettyJson = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
}

object ConfigManager {
    private const val CONFIG_FILE = "ae.json"

    fun findConfigFile(): File? {
        var dir = File(System.getProperty("user.dir"))
        while (dir.parent != null) {
            val file = dir.resolve(CONFIG_FILE)
            if (file.exists()) return file
            dir = dir.parentFile
        }
        return null
    }

    fun load(): AeConfig? {
        val file = findConfigFile() ?: return null
        return prettyJson.decodeFromString<AeConfig>(file.readText())
    }

    fun projectRoot(): File? = findConfigFile()?.parentFile

    fun save(config: AeConfig, dir: File = File(System.getProperty("user.dir"))) {
        val file = dir.resolve(CONFIG_FILE)
        file.writeText(prettyJson.encodeToString(config))
    }

    fun exists(): Boolean = findConfigFile() != null
}
