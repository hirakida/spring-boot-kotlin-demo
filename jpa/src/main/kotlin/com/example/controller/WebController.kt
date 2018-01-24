package com.example.controller

import com.example.repository.AccountRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebController(val accountRepository: AccountRepository) {

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("message", "hello kotlin!")
        return "index"
    }

    @GetMapping("/accounts")
    fun accounts(model: Model): String {
       val accounts = accountRepository.findAll()
        model.addAttribute("accounts", accounts)
        return "account"
    }
}
