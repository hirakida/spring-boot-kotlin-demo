package com.example

import kotlinx.coroutines.flow.Flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToFlow

@RestController
class UserController(private val webClient: WebClient) {

    @GetMapping("/users")
    suspend fun findAll(): Flow<User> =
        webClient.get()
            .uri("/internal/users")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlow()

    @GetMapping("/users/{id}")
    suspend fun findOne(@PathVariable id: Int): User =
        webClient.get()
            .uri("/internal/users/{id}", id)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .awaitBody()
}
