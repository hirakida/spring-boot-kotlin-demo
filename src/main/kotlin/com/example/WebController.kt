package com.example

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebController() {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("message", "hello!")
        return "index"
    }
}
