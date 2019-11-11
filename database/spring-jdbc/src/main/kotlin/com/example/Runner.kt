package com.example

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class Runner(private val userService: UserService) : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        userService.findAll().forEach {
            println(it)
        }

        val user = userService.findById(1)
        println(user)
    }
}
