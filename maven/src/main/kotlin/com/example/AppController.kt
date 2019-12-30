package com.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AppController(private val properties: AppProperties) {
    @GetMapping("/")
    fun properties() = properties
}
