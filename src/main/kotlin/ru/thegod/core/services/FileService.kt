package ru.thegod.core.services

import jakarta.inject.Singleton
import org.bytedeco.opencv.global.opencv_imgcodecs.imread
import org.bytedeco.opencv.opencv_core.Mat

@Singleton
class FileService {
    private val defaultPath:String = System.getProperty("user.dir")+"/src/main/resources/images"


    fun loadJpgFile(filename:String, pathToImages: String = defaultPath): Mat {
        val pathToFile = "$pathToImages/jpg/$filename"
        val image: Mat = imread(pathToFile)
        return image
    }

    fun loadHeicFile(filename:String, pathToImages: String = defaultPath): Mat {
        val pathToFile = "$pathToImages/heic/$filename"
        val image: Mat = imread(pathToFile)
        return image
    }

}
