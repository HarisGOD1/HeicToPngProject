package ru.thegod.core.services

import jakarta.inject.Singleton
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO


@Singleton
class FileService {
    private val defaultPath:String = System.getProperty("user.dir")+"/src/main/resources/images"


    fun loadJpgFile(filename:String, pathToImages: String = defaultPath): File{
        val pathToFile = "$pathToImages/jpg/$filename.jpg"
        val file:File = File(pathToFile)
        return file
    }

    fun loadHeicFile(filename:String, pathToImages: String = defaultPath): File
    {
        val pathToFile = "$pathToImages/heic/$filename"
        val url: URL? = javaClass.getResource(pathToFile)
        if (url==null) throw IOException()
        val file: File = File(url.getPath())
        return file
    }
}
