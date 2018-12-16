package com.example.client

import com.example.client.model.Email
import com.example.client.model.Follow
import com.example.client.model.User
import com.example.config.AppProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.exchange
import org.springframework.web.client.getForObject
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.util.UriComponentsBuilder

@Component
class GitHubApiClient(private val appProperties: AppProperties,
                      restTemplateBuilder: RestTemplateBuilder) {
    private val restTemplate = restTemplateBuilder.build()

    companion object {
        private const val API_ENDPOINT = "https://api.github.com"
    }

    fun getUser(): User {
        val url = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .path("/user")
                .queryParam("access_token", appProperties.userScope.token)
                .toUriString()
        return restTemplate.getForObject(url) ?: throw  ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getUser(username: String): User {
        val url = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .path("/users/{username}")
                .buildAndExpand(username)
                .toUriString()
        return restTemplate.getForObject(url) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getEmails(): List<Email> {
        val url = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .path("/user/emails")
                .queryParam("access_token", appProperties.userScope.token)
                .toUriString()
        val response: ResponseEntity<List<Email>> = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getPublicEmails(): List<Email> {
        val url = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .path("/user/public_emails")
                .queryParam("access_token", appProperties.userScope.token)
                .toUriString()
        val response: ResponseEntity<List<Email>> = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getFollowers(): List<Follow> {
        val url = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .path("/user/followers")
                .queryParam("access_token", appProperties.userScope.token)
                .toUriString()
        val response: ResponseEntity<List<Follow>> = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getFollowers(username: String): List<Follow> {
        val url = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .path("/users/{username}/followers")
                .buildAndExpand(username)
                .toUriString()
        val response: ResponseEntity<List<Follow>> = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getFollowing(): List<Follow> {
        val url = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .path("/user/following")
                .queryParam("access_token", appProperties.userScope.token)
                .toUriString()
        val response: ResponseEntity<List<Follow>> = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getFollowing(username: String): List<Follow> {
        val url = UriComponentsBuilder.fromHttpUrl(API_ENDPOINT)
                .path("/users/{username}/following")
                .buildAndExpand(username)
                .toUriString()
        val response: ResponseEntity<List<Follow>> = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}
