package com.example

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class Runner(private val userService: UserService) : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        for (i in 1..5) {
            userService.insert(User(0, "name$i"))
        }

        userService.selectAll().forEach {
            println(it)
        }
    }
}
