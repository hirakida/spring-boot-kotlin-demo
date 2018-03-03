package com.example.service

import com.example.entity.Account
import com.example.repository.AccountRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun findById(id: Int): Account {
        return accountRepository.findById(id)
                .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }
}
