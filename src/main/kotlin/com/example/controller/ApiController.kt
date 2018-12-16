package com.example.controller

import com.example.entity.User
import com.example.service.UserService
import org.hibernate.validator.constraints.Range
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("/api")
class ApiController(private val userService: UserService) {

    @GetMapping("/users")
    fun findAll(): List<User> = userService.findAll()

    @GetMapping("/users/{id}")
    fun getOne(@PathVariable id: Int): User = userService.getOne(id)

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Validated request: Request): ResponseEntity<User> {
        val user = User(name = request.name, age = request.age)
        val created = userService.create(user)
        return ResponseEntity.created(URI.create("/api/users/" + created.id)).body(created)
    }

    @PutMapping("/users/{id}")
    fun update(@PathVariable id: Int, @RequestBody @Validated request: Request): User {
        val user = User(id = id, name = request.name, age = request.age)
        return userService.update(user)
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        userService.delete(id)
    }

    data class Request(@field:NotEmpty val name: String,
                       @field:Range(min = 20, max = 60) val age: Byte)
}
