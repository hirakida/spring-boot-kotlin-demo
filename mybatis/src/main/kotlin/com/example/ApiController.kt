package com.example

import com.example.domain.Account
import com.example.domain.AccountMapper
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(val accountMapper: AccountMapper) {

    @GetMapping("/api/accounts")
    fun getAll(): List<Account>
            = accountMapper.findAll()

    @GetMapping("/api/accounts/{id}")
    fun getOne(@PathVariable id: Int): Account
            = accountMapper.findOne(id)

    @PostMapping("/api/accounts")
    fun post(@RequestBody account: Account): Int
            = accountMapper.insert(account)

    @PutMapping("/api/accounts/{id}")
    fun put(@RequestBody account: Account): Int
            = accountMapper.update(account)

    @DeleteMapping("/api/accounts/{id}")
    fun delete(@PathVariable id: Int): Int
            = accountMapper.delete(id)
}
