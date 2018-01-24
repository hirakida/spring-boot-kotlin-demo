package com.example.controller

import com.example.entity.Account
import com.example.repository.AccountRepository
import com.example.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(val accountService: AccountService,
                    val accountRepository: AccountRepository) {

    @GetMapping("/accounts")
    fun findAll(): List<Account> = accountRepository.findAll()

    @GetMapping("/accounts/{id}")
    fun find(@PathVariable id: Int): Account
            = accountService.findOne(id)

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: AccountRequest): Account {
        val account = Account(name = request.name)
        return accountRepository.save(account)
    }

    @PutMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody request: AccountRequest) {
        val account = accountService.findOne(id)
        accountRepository.save(account.copy(name = request.name))
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        val account = accountService.findOne(id)
        accountRepository.delete(account)
    }

    data class AccountRequest(var id: Int = 0,
                              var name: String = "")
}
