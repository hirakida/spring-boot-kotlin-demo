package com.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
class ApiController {

    @GetMapping("/")
    fun hello(): Map<String, Any> = mapOf("datetime" to LocalDateTime.now())
}
