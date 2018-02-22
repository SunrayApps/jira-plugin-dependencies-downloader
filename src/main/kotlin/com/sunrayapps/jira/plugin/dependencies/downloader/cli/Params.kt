package com.sunrayapps.jira.plugin.dependencies.downloader.cli

import java.nio.file.Path

data class Params(
    val maven: String,
    val directory: Path
)