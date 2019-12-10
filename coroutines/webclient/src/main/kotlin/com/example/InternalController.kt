package com.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/internal")
class InternalController {

    @GetMapping("/users")
    fun findAll(): Flux<User> = Flux.fromIterable((1..5).map { User(it, "name$it") }.toList())

    @GetMapping("/users/{id}")
    fun findOne(@PathVariable id: Int): Mono<User> = Mono.just(User(id, "name$id"))
}
