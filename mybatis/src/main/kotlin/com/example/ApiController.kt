package com.example

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
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api")
class ApiController(val accountMapper: AccountMapper) {

    @GetMapping("/accounts")
    fun findAll(): List<Account> = accountMapper.findAll()

    @GetMapping("/accounts/{id}")
    fun findById(@PathVariable id: Int): Account {
        return accountMapper.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody account: Account): Account {
        accountMapper.insert(account)
        return accountMapper.findById(account.id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@RequestBody account: Account) {
        accountMapper.update(account)
    }

    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        accountMapper.delete(id)
    }
}
