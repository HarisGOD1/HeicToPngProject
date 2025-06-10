package ru.thegod.core.services


import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.awt.Image
import javax.imageio. ImageIO;
import javax.imageio. ImageReader;
import javax.imageio.stream. ImageInputStream;
import javax.imageio.stream. ImageOutputStream; import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Singleton
public class HeicToPngConverter {

    @Inject
    lateinit var fileService: FileService
    fun convertHeicToPng(heicFileName:String)
    {

        var heicFile: File = fileService.loadHeicFile(heicFileName)
        var jpgFile: File = fileService.loadJpgFile(heicFileName)
        try {
            var input:ImageInputStream = ImageIO.createImageInputStream(heicFile)
            var  readers:MutableIterator<ImageReader> = ImageIO.getImageReadersByFormatName ("HEIF")
            if (readers.hasNext()) {
                var reader:ImageReader = readers.next()
                reader.setInput(input);
                var image:BufferedImage = reader.read(0)
                var output:ImageOutputStream = ImageIO.createImageOutputStream (jpgFile);
                ImageIO.write(image, "jpg", output);
                output.flush();
            } else {

            }
            System.out.println("No reader found for HEIF format");
        } catch(e: IOException) {
            e.printStackTrace();
        }
        }

}