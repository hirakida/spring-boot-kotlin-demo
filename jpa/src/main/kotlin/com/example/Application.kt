package com.example

import com.example.domain.Account
import com.example.domain.AccountRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class Application(val accountRepository: AccountRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        accountRepository.deleteAll()
        val list = accountRepository.findAll()
        if (list.isEmpty()) {
            for (i in 1..10) {
                val account = Account(name = "account" + i)
                accountRepository.save(account)
            }
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
