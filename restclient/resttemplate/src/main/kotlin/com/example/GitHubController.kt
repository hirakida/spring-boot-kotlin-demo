package com.example

import com.example.client.GitHubApiClient
import com.example.model.Email
import com.example.model.Follow
import com.example.model.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException

@RestController
@RequestMapping("/github")
class GitHubController(private val client: GitHubApiClient) {

    @GetMapping("/users/{username}")
    fun user(@PathVariable username: String): User = client.getUser(username)

    @GetMapping("/users/{username}/followers")
    fun followers(@PathVariable username: String): List<Follow> = client.getFollowers(username)

    @GetMapping("/users/{username}/following")
    fun following(@PathVariable username: String): List<Follow> = client.getFollowing(username)

    @GetMapping("/user")
    fun user(): User = client.getUser()

    @GetMapping("/user/emails")
    fun emails(): List<Email> = client.getEmails()

    @GetMapping("/user/public_emails")
    fun publicEmails(): List<Email> = client.getPublicEmails()

    @GetMapping("/user/followers")
    fun followers(): List<Follow> = client.getFollowers()

    @GetMapping("/user/following")
    fun following(): List<Follow> = client.getFollowing()

    @ExceptionHandler
    fun handleHttpClientErrorException(e: HttpClientErrorException): ResponseEntity<Any> {
        return ResponseEntity.status(e.statusCode).build()
    }
}
