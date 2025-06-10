package ru.thegod.core.service

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import ru.thegod.core.services.FileService

@MicronautTest
class FileServiceTest {


    @Inject
    lateinit var fileService: FileService

    @Test
    fun `service loads existing jpg file from resources`(){
        val jpgBufferedImage = fileService.loadJpgFile("BlackBalls.jpg")

        assertNotNull(jpgBufferedImage)

    }

    @Test
    fun `service loads existing heic file from resources`(){
        val file = fileService.loadHeicFile("chef-with-trumpet.heic")

        assertNotNull(file)

    }
}