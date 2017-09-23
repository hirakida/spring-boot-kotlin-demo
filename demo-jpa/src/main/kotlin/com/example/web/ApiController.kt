package com.example.web

import com.example.domain.Account
import com.example.domain.AccountRepository
import com.example.exception.DataNotFoundException
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
class ApiController(val accountRepository: AccountRepository) {

    @GetMapping("/accounts")
    fun findAll(): List<Account>
            = accountRepository.findAll()

    @GetMapping("/accounts/{id}")
    fun find(@PathVariable id: Int): Account
            = findOne(id)

    @PostMapping("/accounts")
    fun create(@RequestBody request: ApiAccount): Account {
        val account = Account(name = request.name)
        return accountRepository.save(account)
    }

    @PutMapping("/accounts/{id}")
    fun update(@PathVariable id: Int,
               @RequestBody request: ApiAccount): Account {
        val account = findOne(id)
        return accountRepository.save(account.copy(name = request.name))
    }

    @DeleteMapping("/accounts/{id}")
    fun delete(@PathVariable id: Int) {
        val account = findOne(id)
        accountRepository.delete(account)
    }

    private fun findOne(id: Int): Account =
            accountRepository.findOne(id)
                    ?: throw DataNotFoundException(id.toString())

    data class ApiAccount(var id: Int = 0,
                          var name: String = "")
}
