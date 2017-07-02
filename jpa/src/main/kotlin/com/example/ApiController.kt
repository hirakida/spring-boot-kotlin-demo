package com.example

import com.example.domain.Account
import com.example.domain.AccountRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(val accountRepository: AccountRepository) {

    @GetMapping("/api/accounts")
    fun getAll(): List<Account>
            = accountRepository.findAll()

    @GetMapping("/api/accounts/{id}")
    fun getOne(@PathVariable id: Int): Account
            = accountRepository.findOne(id)

    @DeleteMapping("/api/accounts/{id}")
    fun delete(@PathVariable id: Int): Unit {
        accountRepository.delete(id)
    }
}
