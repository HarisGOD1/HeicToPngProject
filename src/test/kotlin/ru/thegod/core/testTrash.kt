package ru.thegod.core

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import ru.thegod.core.services.FileService

@MicronautTest
class testTrash {

    @Inject
    lateinit var fileService: FileService


    @Test
    fun testSmoothConverter(){
        trash.readFile("/home/thegod/JAVA/PROJECTS/HeicToPngProject/src/main/resources/images/jpg/BlackBalls.jpg")
        fileService.loadJpgFile("BlackBalls.jpg")
    }

}