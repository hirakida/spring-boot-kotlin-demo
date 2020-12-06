package com.example.client

import com.example.model.Follow
import com.example.model.User
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class GitHubApiClient(
    builder: WebClient.Builder
) {
    private val webClient: WebClient = builder.baseUrl(BASE_URL).build()

    companion object {
        private const val BASE_URL = "https://api.github.com"
    }

    fun getUser(username: String): Mono<User> {
        return webClient.get()
            .uri("/users/{username}", username)
            .retrieve()
            .bodyToMono(User::class.java)
    }

    fun getFollowers(username: String): Flux<Follow> {
        return webClient.get()
            .uri("/users/{username}/followers", username)
            .retrieve()
            .bodyToFlux(Follow::class.java)
    }

    fun getFollowing(username: String): Flux<Follow> {
        return webClient.get()
            .uri("/users/{username}/following", username)
            .retrieve()
            .bodyToFlux(Follow::class.java)
    }
}
