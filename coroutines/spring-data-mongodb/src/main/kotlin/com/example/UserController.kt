package com.example

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/users")
    suspend fun findAll() = userRepository.findAll()

    @GetMapping("/users/{id}")
    suspend fun findOne(@PathVariable id: String) = userRepository.findOne(id)

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody user: User) {
        userRepository.insert(user)
    }

    @PutMapping("/users/{id}")
    suspend fun update(@PathVariable id: String, @RequestBody user: User) {
        userRepository.update(user)
    }
}
