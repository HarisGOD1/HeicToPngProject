package ru.thegod.core

import jakarta.inject.Singleton
import org.bytedeco.ffmpeg.global.avutil
import org.bytedeco.javacv.FFmpegFrameGrabber
import org.bytedeco.javacv.Frame
import org.bytedeco.javacv.Java2DFrameConverter
import org.bytedeco.javacv.OpenCVFrameConverter
import org.bytedeco.opencv.global.opencv_imgcodecs.imwrite
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

@Singleton
class superConverter {
    private val defaultPath:String = System.getProperty("user.dir")+"/src/main/resources/images"

    fun convertHeicToPng(inName: String) {
        var inputPath: String = "$defaultPath/heic/$inName.HEIC"
        var outputPath: String = "$defaultPath/png/${inName}_converted.png"
        avutil.av_log_set_level(avutil.AV_LOG_ERROR) // suppress FFmpeg noise

        FFmpegFrameGrabber(inputPath).use { grabber ->
            grabber.start()
//            val matConverter = OpenCVFrameConverter.ToMat()
            // Grab the first frame (should be the image itself)
            val frame: Frame? = grabber.grabImage()
            if (frame != null) {

                println("Frame info:${frame.imageWidth}\n" +
                        "${frame.imageHeight}\n" +
                        "${frame.imageChannels}\n" +
                        "${frame.data}\n" +
                        "${frame.image.get(0)}\n" +
                        "${frame.image.get(0).hasRemaining()}")
                println(frame.image.get(0).hasArray())
                println(frame.image.get(0).position())
                // Convert frame to BufferedImage
                val converter = Java2DFrameConverter()
                val bufferedImage = converter.getBufferedImage(frame)

                // Save to PNG
                val output = File(outputPath)
//                ImageIO.write(bufferedImage, "png", output)

                println("Conversion successful: ${output.absolutePath}")
            } else {
                println("Failed to grab image from HEIC file.")
            }

            grabber.stop()
        }
    }
}