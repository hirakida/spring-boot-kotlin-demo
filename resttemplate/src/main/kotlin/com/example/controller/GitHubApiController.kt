package com.example.controller

import com.example.GitHubApiClient
import com.example.model.Email
import com.example.model.Follow
import com.example.model.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/github")
class GitHubApiController(private val gitHubApiClient: GitHubApiClient) {

    @GetMapping("/user")
    fun user(): User = gitHubApiClient.getUser()

    @GetMapping("/user/emails")
    fun emails(): List<Email> = gitHubApiClient.getEmails()

    @GetMapping("/user/public_emails")
    fun publicEmails(): List<Email> = gitHubApiClient.getPublicEmails()

    @GetMapping("/user/followers")
    fun followers(): List<Follow> = gitHubApiClient.getFollowers()

    @GetMapping("/user/following")
    fun following(): List<Follow> = gitHubApiClient.getFollowing()

    @GetMapping("/users/{username}")
    fun user(@PathVariable username: String): User = gitHubApiClient.getUser(username)

    @GetMapping("/users/{username}/followers")
    fun followers(@PathVariable username: String): List<Follow> = gitHubApiClient.getFollowers(username)

    @GetMapping("/users/{username}/following")
    fun following(@PathVariable username: String): List<Follow> = gitHubApiClient.getFollowing(username)
}
