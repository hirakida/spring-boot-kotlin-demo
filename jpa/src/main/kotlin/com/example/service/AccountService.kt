package com.example.service

import com.example.entity.Account
import com.example.repository.AccountRepository
import com.example.support.DataNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun findOne(id: Int): Account
            = accountRepository.findOne(id) ?: throw DataNotFoundException(id.toString())
}
