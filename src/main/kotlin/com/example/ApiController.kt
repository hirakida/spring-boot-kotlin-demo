package com.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(val accountRepository: AccountRepository) {

    @GetMapping("/api/accounts")
    fun accounts() : List<Account>
            = accountRepository.findAll()

    @GetMapping("/api/accounts/{id}")
    fun account(@PathVariable id: Int) : Account
            = accountRepository.findOne(id)
}
