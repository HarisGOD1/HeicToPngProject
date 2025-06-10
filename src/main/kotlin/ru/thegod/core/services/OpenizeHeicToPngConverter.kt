package ru.thegod.core.services

import jakarta.inject.Singleton
import openize.heic.decoder.HeicImage
import openize.heic.decoder.PixelFormat
import openize.heic.decoder.Rectangle
import openize.io.IOFileStream
import openize.io.IOMode
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO


@Singleton
public class OpenizeHeicToPngConverter {

    fun convertHeicToPng(heicFileName:String)
    {
        try
            {
                val fs = IOFileStream(heicFileName, IOMode.READ)
                println("file found")
                var image = HeicImage.load(fs)
                println("file load")
                var width = image.width.toInt()
                var height = image.height.toInt()
                println(image.frames.keys)
                println(image.header.defaultFrameId)
                println("start grabbing int argb32")
                var pixels = image.getInt32Array(PixelFormat.Argb32, Rectangle(0,0,width,height))
                println("pixel grab")
                println(pixels)
                println()


                println("pixels got")

                var image2 = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
                image2.setRGB(0, 0, width, height, pixels, 0, width)
                ImageIO.write(image2, "PNG", File("output.png"))
                println("pixels converted")

            }
        catch (e: Exception){
            throw e
        }
    }

}