package com.example

import com.example.client.GitHubApiClient
import com.example.model.Follow
import com.example.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/github")
class GitHubController(private val client: GitHubApiClient) {

    @GetMapping("/users/{username}")
    fun user(@PathVariable username: String): Mono<User> = client.getUser(username)

    @GetMapping("/users/{username}/followers")
    fun followers(@PathVariable username: String): Flux<Follow> = client.getFollowers(username)

    @GetMapping("/users/{username}/following")
    fun following(@PathVariable username: String): Flux<Follow> = client.getFollowing(username)
}
