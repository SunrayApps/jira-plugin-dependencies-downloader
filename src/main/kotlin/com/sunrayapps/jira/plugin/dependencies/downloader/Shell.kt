package com.sunrayapps.jira.plugin.dependencies.downloader

import java.io.InputStreamReader
import java.io.BufferedReader


class Shell {
    fun exec(vararg commands: String) {
        println("\$ ${commands.joinToString(" ")}")

        val process = if (commands.size == 1) {
            Runtime.getRuntime().exec(commands[0])
        } else {
            Runtime.getRuntime().exec(commands)
        }

        val reader = BufferedReader(InputStreamReader(process.inputStream))
        reader.lines().forEach { println(it) }
        process.waitFor()

        if (process.exitValue() != 0) {
            val errorReader = BufferedReader(InputStreamReader(process.errorStream))
            throw Exception("Process exited with ${process.exitValue()} exit value. ${errorReader.readText()}")
        }
    }
}