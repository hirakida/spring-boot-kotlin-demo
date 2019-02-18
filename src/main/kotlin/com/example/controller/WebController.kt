package com.example.controller

import com.example.core.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebController(private val userRepository: UserRepository) {

    @GetMapping("/")
    fun index(model: Model): String {
        val users = userRepository.findAll()
        model.addAttribute("users", users)
        return "index"
    }
}
