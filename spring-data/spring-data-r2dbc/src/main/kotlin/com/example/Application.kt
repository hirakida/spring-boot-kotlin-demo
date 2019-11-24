package com.example

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.transaction.reactive.TransactionalOperator
import reactor.core.publisher.Flux

@SpringBootApplication
class Application(private val operator: TransactionalOperator,
                  private val userRepository: UserRepository) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @EventListener(ApplicationReadyEvent::class)
    fun readyEvent() {
        Flux.fromIterable(1..5)
                .flatMap { userRepository.insert(User(it, "name$it")) }
                .thenMany(userRepository.findAll())
                .subscribe { log.info("$it") }

        Flux.fromIterable(listOf("name6", "name7"))
                .flatMap { userRepository.insertUntyped(it) }
                .subscribe { log.info("insert: rowsUpdated=$it") }

        userRepository.delete(1)
                .subscribe { log.info("delete: rowsUpdated=$it") }

        operator.transactional(userRepository.findOne(2)
                .map { user -> User(user.id, user.name + "!") }
                .flatMap { userRepository.update(it) })
                .subscribe { log.info("update: rowsUpdated=$it") }

        userRepository.findAll()
                .subscribe { log.info("$it") }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
