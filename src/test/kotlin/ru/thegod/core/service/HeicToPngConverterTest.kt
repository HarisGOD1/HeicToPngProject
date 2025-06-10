package ru.thegod.core.service

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import ru.thegod.core.services.FFmpegConverter
import ru.thegod.core.services.OpenizeHeicToPngConverter

@MicronautTest
class HeicToPngConverterTest {

    @Inject
    lateinit var openizeHeicToPngConverter: OpenizeHeicToPngConverter
    @Inject
    lateinit var ffmpegConverter: FFmpegConverter

    @Test
    fun `test heic openize shit`(){

        openizeHeicToPngConverter.convertHeicToPng("/home/thegod/JAVA/PROJECTS/HeicToPngProject/src/main/resources/images/heic/chef-with-trumpet.heic")
    }

    @Test
    fun `test heic ffmpeg shit`(){
        ffmpegConverter.convert("IMG_8499.HEIC")
    }

    @Test
    fun `test heic ffmpeg multiple shit`(){
        ffmpegConverter.convertMultiple("IMG_8499.HEIC")
    }
}