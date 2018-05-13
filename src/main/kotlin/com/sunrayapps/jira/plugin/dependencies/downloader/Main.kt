package com.sunrayapps.jira.plugin.dependencies.downloader

import com.sunrayapps.jira.plugin.dependencies.downloader.cli.Cli
import java.nio.file.Paths


fun main(args: Array<String>) {
    val params = Cli()
        .parse(args)

    Jta(
        installDirectory = Paths.get("."),
        maven = Maven(
            mavenExecutable = params.maven
        )
    ).download()
        .unzip()
        .install()
        .clean()

    Jndi(
        installDirectory = Paths.get("."),
        maven = Maven(
            mavenExecutable = params.maven
        )
    ).download()
        .unzip()
        .install()
        .clean()
}

