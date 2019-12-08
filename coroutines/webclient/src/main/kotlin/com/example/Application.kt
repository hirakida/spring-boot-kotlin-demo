package com.example

import kotlinx.coroutines.flow.Flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToFlow
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

data class User(val id: Int, val name: String)

@SpringBootApplication
class Application {

    @Bean
    fun webClient(builder: WebClient.Builder) = builder.baseUrl("http://localhost:8080").build()
}

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

@RestController
class InternalUserController {

    @GetMapping("/internal/users")
    fun findAll(): Flux<User> {
        return Flux.fromIterable(listOf(User(1, "name1"), User(2, "name2"), User(3, "name3")))
    }

    @GetMapping("/internal/users/{id}")
    fun findOne(@PathVariable id: Int): Mono<User> {
        return Mono.just(User(id, "name$id"))
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
