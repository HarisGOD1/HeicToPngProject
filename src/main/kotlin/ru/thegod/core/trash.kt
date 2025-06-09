package ru.thegod.core

import org.bytedeco.opencv.helper.opencv_imgcodecs.cvLoadImage
import org.bytedeco.opencv.opencv_core.IplImage

object trash {
    fun readFile(filename: String?) {
        val image: IplImage = cvLoadImage(filename)
        println(image.height())
        println(System.getProperty("user.dir"))
//            opencv_imgproc.GaussianBlur(image, image, Size(3, 3), 0.0)

    }
}