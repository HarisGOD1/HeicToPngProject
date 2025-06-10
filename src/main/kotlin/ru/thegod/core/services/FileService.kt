package ru.thegod.core.services

import jakarta.inject.Singleton
import openize.heic.decoder.HeicImage
import openize.io.IOFileStream
import openize.io.IOMode
import java.io.File


@Singleton
class FileService {
    private val defaultPath:String = System.getProperty("user.dir")+"/src/main/resources/images"


    fun loadJpgFile(filename:String, pathToImages: String = defaultPath): File{
        val pathToFile = "$pathToImages/jpg/$filename"
        val file:File = File(pathToFile)
        return file
    }

    fun loadHeicFile(filename:String, pathToImages: String = defaultPath): File?
    {
        val pathToFile = "$pathToImages/heic/$filename"
        val file:File = File(pathToFile)
        return file
    }
}
