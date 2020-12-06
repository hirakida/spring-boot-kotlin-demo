package com.example.client

import com.example.GitHubProperties
import com.example.model.Email
import com.example.model.Follow
import com.example.model.User
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.getForObject
import org.springframework.web.util.UriComponentsBuilder

@Component
class GitHubApiClient(
    private val properties: GitHubProperties,
    private val restTemplate: RestTemplate
) {
    companion object {
        private const val BASE_URL = "https://api.github.com"
    }

    fun getUser(username: String): User {
        val url = buildUrl("/users/{username}", username)
        return restTemplate.getForObject(url)
    }

    fun getFollowers(username: String): List<Follow> {
        val url = buildUrl("/users/{username}/followers", username)
        val response: ResponseEntity<List<Follow>> =
            restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: emptyList()
    }

    fun getFollowing(username: String): List<Follow> {
        val url = buildUrl("/users/{username}/following", username)
        val response: ResponseEntity<List<Follow>> =
            restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: emptyList()
    }

    fun getUser(): User {
        val url = buildUrl("/user")
        return restTemplate.getForObject(url)
    }

    fun getEmails(): List<Email> {
        val url = buildUrl("/user/emails")
        val response: ResponseEntity<List<Email>> = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: emptyList()
    }

    fun getPublicEmails(): List<Email> {
        val url = buildUrl("/user/public_emails")
        val response: ResponseEntity<List<Email>> = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: emptyList()
    }

    fun getFollowers(): List<Follow> {
        val url = buildUrl("/user/followers")
        val response: ResponseEntity<List<Follow>> =
            restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: emptyList()
    }

    fun getFollowing(): List<Follow> {
        val url = buildUrl("/user/following")
        val response: ResponseEntity<List<Follow>> =
            restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY)
        return response.body ?: emptyList()
    }

    private fun buildUrl(path: String, vararg uriVariableValues: String): String {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
            .path(path)
            .buildAndExpand(*uriVariableValues)
            .toUriString()
    }

    private fun buildUrl(path: String): String {
        return UriComponentsBuilder.fromHttpUrl(BASE_URL)
            .path(path)
            .queryParam("access_token", properties.userScope.token)
            .toUriString()
    }
}
