package com.ae.designsystem.cli.commands

import com.github.ajalt.clikt.core.Abort
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.parameters.options.option
import com.ae.designsystem.cli.registry.ConfigManager
import com.ae.designsystem.cli.registry.RegistryClient

class ListCommand : CliktCommand(name = "list") {

    override fun help(context: Context) = "List all available components"

    private val registryFlag by option("--registry", "-r", help = "Registry URL")

    override fun run() {
        val config = ConfigManager.load()
        val registryUrl = registryFlag ?: config?.registry ?: "https://aedesignsystem.com/r"
        val client = RegistryClient(registryUrl)

        echo("Fetching components from $registryUrl...")
        echo("")

        val index = try {
            client.fetchIndex()
        } catch (e: Exception) {
            echo("Error: Failed to fetch registry: ${e.message}", err = true)
            throw Abort()
        }

        echo("  AEDesignSystem v${index.version} — ${index.items.size} components")
        echo("")

        val grouped = index.items.groupBy { it.category.ifEmpty { "Other" } }
        grouped.forEach { (category, items) ->
            echo("  $category:")
            items.forEach { item ->
                val deps = if (item.registryDependencies.isNotEmpty()) {
                    " [deps: ${item.registryDependencies.joinToString(", ")}]"
                } else ""
                echo("    ${item.name.padEnd(20)} ${item.description}$deps")
            }
            echo("")
        }

        echo("")
        echo("  Run 'aedesignsystem add <name>' to add a component.")
        echo("")
    }
}
