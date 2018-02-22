package com.sunrayapps.jira.plugin.dependencies.downloader

import java.io.File
import java.net.URI
import org.apache.commons.io.FileUtils.*
import org.rauschig.jarchivelib.ArchiveFormat
import org.rauschig.jarchivelib.ArchiverFactory
import java.nio.file.Path

class Jta(
    private val installDirectory: Path,
    private val maven: Maven
) {
    val jarName = "jta-1.0.1.jar"
    val archiveFileName = "jta-1.0.1.jar.zip"
    val url = "http://www.java2s.com/Code/JarDownload/jta/$archiveFileName"

    fun download(): Jta {
        copyURLToFile(
            URI.create(url).toURL(),
            File(installDirectory
                .resolve(archiveFileName)
                .toUri())
        )
        return this
    }

    fun unzip(): Jta {
        val archive = File(archiveFileName)
        val destination = installDirectory.toFile()

        val archiver = ArchiverFactory.createArchiver(ArchiveFormat.ZIP)
        archiver.extract(archive, destination)
        return this
    }

    fun install(): Jta {
        Shell().exec(
            maven.mavenExecutable,
            "install:install-file",
            "-Dfile=${installDirectory.resolve(jarName).toAbsolutePath()}",
            "-DgroupId=jta",
            "-DartifactId=jta",
            "-Dversion=1.0.1",
            "-Dpackaging=jar"
        )
        return this
    }

    fun clean(): Jta {
        File(archiveFileName).delete()
        File(jarName).delete()
        return this
    }
}