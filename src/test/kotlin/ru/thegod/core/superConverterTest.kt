package ru.thegod.core

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Test

@MicronautTest
class superConverterTest {

    @Inject
    lateinit var superConverter: superConverter

    @Test
    fun `test chatgpt code`(){
        superConverter.convertHeicToPng("IMG_8498")
    }
}