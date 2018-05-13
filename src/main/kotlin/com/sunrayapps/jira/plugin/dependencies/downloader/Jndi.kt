package com.sunrayapps.jira.plugin.dependencies.downloader

import org.apache.commons.io.FileUtils
import org.rauschig.jarchivelib.ArchiveFormat
import org.rauschig.jarchivelib.ArchiverFactory
import java.io.File
import java.net.URI
import java.nio.file.Path

//jndi:jndi:pom:1.2.1
class Jndi(
    private val installDirectory: Path,
    private val maven: Maven
) {
    private val jarName = "jndi-1.2.1.jar"
    private val archiveFileName = "jndi-1.2.1.jar.zip"
    private val url = "http://www.java2s.com/Code/JarDownload/jndi/$archiveFileName"

    fun download(): Jndi {
        FileUtils.copyURLToFile(
            URI.create(url).toURL(),
            File(installDirectory
                .resolve(archiveFileName)
                .toUri())
        )
        return this
    }

    fun unzip(): Jndi {
        val archive = File(archiveFileName)
        val destination = installDirectory.toFile()

        val archiver = ArchiverFactory.createArchiver(ArchiveFormat.ZIP)
        archiver.extract(archive, destination)
        return this
    }

    fun install(): Jndi {
        Shell().exec(
            maven.mavenExecutable,
            "install:install-file",
            "-Dfile=${installDirectory.resolve(jarName).toAbsolutePath()}",
            "-DgroupId=jndi",
            "-DartifactId=jndi",
            "-Dversion=1.2.1",
            "-Dpackaging=jar"
        )
        return this
    }

    fun clean(): Jndi {
        File(archiveFileName).delete()
        File(jarName).delete()
        return this
    }
}