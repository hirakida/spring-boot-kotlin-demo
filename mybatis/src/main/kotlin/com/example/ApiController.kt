package com.example

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.constraints.NotEmpty

@RestController
class ApiController(private val userMapper: UserMapper) {

    @GetMapping("/users")
    fun findAll(): List<User> = userMapper.findAll()

    @GetMapping("/users/{id}")
    fun findById(@PathVariable id: Long): User =
            userMapper.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @PostMapping("/users")
    fun create(@RequestBody @Validated request: Request): Int {
        val user = User(name = request.name)
        return userMapper.insert(user)
    }

    @PutMapping("/users/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Validated request: Request): Int {
        val user: User = userMapper.findById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        user.name = request.name
        return userMapper.update(user)
    }

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: Long): Int = userMapper.delete(id);

    data class Request(@field:NotEmpty val name: String)
}
