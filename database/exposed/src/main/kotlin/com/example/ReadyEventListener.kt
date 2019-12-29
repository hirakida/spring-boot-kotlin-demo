package com.example

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionTemplate

@Component
class ReadyEventListener(
    private val userService: UserService,
    private val transactionTemplate: TransactionTemplate
) : ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val users = (1..5).map { User(0, "name$it") }.toList()
        userService.insert(users)
        userService.selectAll().forEach { println(it) }

        transactionTemplate.execute {
            val user = userService.select(1)
            userService.update(User(user.id, user.name + "__"))
            userService.delete(2)
        }
        userService.selectAll().forEach { println(it) }
    }
}
