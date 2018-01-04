package com.example

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(val accountMapper: AccountMapper) {

    @GetMapping("/accounts")
    fun findAll(): List<Account>
            = accountMapper.findAll()

    @GetMapping("/accounts/{id}")
    fun findOne(@PathVariable id: Int): Account
            = accountMapper.findOne(id)

    @PostMapping("/accounts")
    fun create(@RequestBody account: Account): Int
            = accountMapper.insert(account)

    @PutMapping("/accounts/{id}")
    fun update(@RequestBody account: Account): Int
            = accountMapper.update(account)

    @DeleteMapping("/accounts/{id}")
    fun delete(@PathVariable id: Int): Int
            = accountMapper.delete(id)
}
