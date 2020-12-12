package com.example

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime

@Controller
class HelloController {
    private val clock: Clock = Clock.systemDefaultZone()

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("instant", Instant.now(clock))
        model.addAttribute("localDateTime", LocalDateTime.now(clock))
        model.addAttribute("offsetDateTime", OffsetDateTime.now(clock))
        model.addAttribute("zonedDateTime", ZonedDateTime.now(clock))
        return "index"
    }
}
