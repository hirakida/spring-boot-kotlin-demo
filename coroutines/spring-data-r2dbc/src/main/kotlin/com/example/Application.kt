package com.example

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait

@SpringBootApplication
class Application(private val operator: TransactionalOperator,
                  private val userRepository: UserRepository) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @EventListener(ApplicationReadyEvent::class)
    fun readyEvent() = runBlocking {
        operator.executeAndAwait {
            userRepository.insert(User(1, "name1"))
            userRepository.insert(User(2, "name2"))
            userRepository.insert(User(3, "name3"))
            userRepository.insert(User(4, "name4"))
            userRepository.insert(User(5, "name5"))
        }

        userRepository.findAll()
                .collect { log.info("$it") }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
