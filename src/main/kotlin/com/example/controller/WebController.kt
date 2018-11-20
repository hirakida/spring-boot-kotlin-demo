package com.example.controller

import com.example.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebController(val userRepository: UserRepository) {

    @GetMapping("/")
    fun index(model: Model): String {
        val users = userRepository.findAll()
        model.addAttribute("users", users)
        return "index"
    }
}
