package com.example.config

import com.example.support.LocalDateTimeJsonSerializer
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class JsonConfig {

    @Bean
    fun customModule(): SimpleModule {
        val module = SimpleModule("customModule")
        module.addSerializer(LocalDateTime::class.java, LocalDateTimeJsonSerializer())
        return module
    }
}
