package com.example.mapper

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateTimeConverter : AttributeConverter<LocalDateTime, Timestamp> {

    override fun convertToDatabaseColumn(localDateTime: LocalDateTime): Timestamp {
        return Timestamp.valueOf(localDateTime)
    }

    override fun convertToEntityAttribute(timestamp: Timestamp): LocalDateTime {
        return timestamp.toLocalDateTime()
    }
}
