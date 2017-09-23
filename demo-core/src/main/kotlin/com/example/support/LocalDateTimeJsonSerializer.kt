package com.example.support

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.ResolverStyle

class LocalDateTimeJsonSerializer : JsonSerializer<LocalDateTime>() {
    @Throws(IOException::class)
    override fun serialize(var1: LocalDateTime, var2: JsonGenerator, var3: SerializerProvider) {
        val format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.SSS")
                .withResolverStyle(ResolverStyle.STRICT)
        var2.writeString(var1.format(format))
    }
}
