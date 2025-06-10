package ru.thegod.core.services

import jakarta.inject.Singleton
import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.Java2DFrameConverter
import org.bytedeco.javacv.OpenCVFrameConverter
import org.bytedeco.opencv.global.opencv_imgcodecs.imwrite
import org.bytedeco.opencv.opencv_core.Mat
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.min

@Singleton
class FFmpegConverter {
    val defaultPath = "/home/thegod/JAVA/PROJECTS/HeicToPngProject/src/main/resources/images"
    fun convert(filename: String, dirPath: String = defaultPath) {
        var inputHeicPath: String = "$dirPath/heic/$filename"
        var outputPngPath: String = "$dirPath/png/$filename.png"

        var inputFile = File(inputHeicPath)
        if(!inputFile.exists()) {
            println("Error: Input HEIC file not found at " + inputHeicPath)
            return
        }
        try {
            val grabber = FFmpegFrameGrabber(inputHeicPath)
            grabber.start()
            printGrabberInfo(grabber)
//            println(grabber.keys)
            val frame = grabber.grabImage()
            printFrameInfo(frame)
            if (frame == null) {
                System.err.println("Error: Could not grab a frame from the HEIC file. The file might be corrupt or empty.")
                return
            }
            val converter = Java2DFrameConverter()
            val matConverter = OpenCVFrameConverter.ToMat()
            val bufferedImage = converter.getBufferedImage(frame)
            printImageInfo(bufferedImage)
            val mat1 = matConverter
                .convertToMat(frame)
                .clone()



            if (mat1!=null) {
                imwrite(outputPngPath,mat1)
            } else {
                System.err.println("Error: Failed to convert the frame to a BufferedImage.")
            }

        } catch (e: IOException) {
            // This can happen if FFmpeg fails to open or read the file.
            // It might indicate that the underlying FFmpeg build doesn't support HEIC.
            System.err.println("An error occurred during the conversion process.")
            e.printStackTrace()
        }

    }

    fun convertMultiple(filename: String, dirPath: String = defaultPath) {
        var inputHeicPath: String = "$dirPath/heic/$filename"
        var outputPngDirPath: String = "$dirPath/png/"

        val grabber = FFmpegFrameGrabber(inputHeicPath)
        grabber.start()
        printGrabberInfo(grabber)

        val converter = Java2DFrameConverter()
        val converterOpenCV = OpenCVFrameConverter.ToMat()
        var index = 0
        var frame: Frame?

        do {
            frame = grabber.grabImage()
            printFrameInfo(frame)
            if (frame?.image != null && frame.imageChannels >= 3) {
                val image = converter.getBufferedImage(frame)
                printImageInfo(image)
                ImageIO.write(image, "png", File("$outputPngDirPath/$filename$index.png"))
                println("Saved frame_$index.png")
                index++
            }
        } while (frame != null)
        grabber.stop()
    }

    private fun printGrabberInfo(grabber: FFmpegFrameGrabber) {
        println("GRABBER INFO")
        println(grabber.videoCodecName)
        println(grabber.format)
        println(grabber.imageMode)
        println(grabber.imageWidth)
        println(grabber.imageHeight)
    }
    private fun printFrameInfo(frame:Frame?) {
        println("FRAME INFO")
        if (frame!=null) {
            println(frame.imageChannels)
            println(frame.imageStride)
            println(frame.data)
            //frame "I" (Intra-coded frame) It contains all the information needed to decode that frame.
            //      "P" (Predictive frame) – Encoded using motion-compensated prediction from past frames.
            //      "B" (Bi-directional frame) – Encoded using both past and future frames as references.
            //      "S" (S-frame / Switching frame) – Rare, used in some codecs for stream switching.
            println(frame.pictType)
            println(frame.imageDepth)
            println(frame.image)
            for(e in frame.image){
                print(e)
            }

        }
        else{
            println("FRAME IS NULL")
        }
    }



    private fun printImageInfo(image: BufferedImage) {
        println("IMAGE INFO")
        println(image.type)
        println(image.alphaRaster)
        println(image.isAlphaPremultiplied)
        for (i in 0..min(image.width,image.height)-1)
            println(Integer.toBinaryString(image.getRGB(i,i)))
    }

}