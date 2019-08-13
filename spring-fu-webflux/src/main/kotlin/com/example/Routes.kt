package com.example

import org.springframework.web.reactive.function.server.router

fun routes(rootHandler: RootHandler, userHandler: UserHandler) = router {
    GET("/", rootHandler::hello)
    GET("/users", userHandler::findAll)
    GET("/users/{id}", userHandler::findById)
}
