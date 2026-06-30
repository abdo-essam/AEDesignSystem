package com.ae.designsystem.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import com.ae.designsystem.cli.commands.AddCommand
import com.ae.designsystem.cli.commands.InitCommand
import com.ae.designsystem.cli.commands.ListCommand

class AeCli : CliktCommand(name = "aedesignsystem") {
    override fun help(context: Context) =
        "AEDesignSystem — Add components to your Compose Multiplatform project"

    override fun run() = Unit
}

fun main(args: Array<String>) =
    AeCli()
        .subcommands(InitCommand(), AddCommand(), ListCommand())
        .main(args)
