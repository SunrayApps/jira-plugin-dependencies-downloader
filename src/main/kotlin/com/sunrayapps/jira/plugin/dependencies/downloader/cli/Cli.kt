package com.sunrayapps.jira.plugin.dependencies.downloader.cli

import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import java.nio.file.Paths

class Cli {
    fun parse(args: Array<String>): Params {
        val maven = Option.builder("m")
            .hasArg()
            .required()
            .desc("Maven executable")
            .build()

        val directory = Option.builder("d")
            .hasArg()
            .desc("Temporary directory")
            .build()

        val options = Options()
        options.addOption(maven)
        options.addOption(directory)

        val parser = DefaultParser()
        val command = parser.parse(options, args)
        command.getOptionValue(maven.opt)
        return Params(
            maven = command.getOptionValue(maven.opt),
            directory = Paths.get(command.getOptionValue(directory.opt) ?: "")
        )
    }
}