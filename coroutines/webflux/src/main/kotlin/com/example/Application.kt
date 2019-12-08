package com.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

data class User(val id: Int, val message: String)

@SpringBootApplication
class Application

@RestController
class UserController {

    @GetMapping("/users")
    suspend fun findAll(): Flow<User> {
        delay(1000)
        return flowOf(User(1, "name1"), User(2, "name2"), User(3, "name3"))
    }

    @GetMapping("/users/{id}")
    suspend fun findOne(@PathVariable id: Int): User {
        delay(1000)
        return User(id, "message${id}")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
