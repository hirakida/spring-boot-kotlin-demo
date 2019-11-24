package com.example

import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@EnableReactiveMongoRepositories
@SpringBootApplication
class Application(private val userRepository: UserRepository) {

    @EventListener(ApplicationReadyEvent::class)
    fun readyEvent() = runBlocking {
        IntRange(1, 5)
                .map { User("", "name$it") }
                .map { userRepository.insert(it) }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
