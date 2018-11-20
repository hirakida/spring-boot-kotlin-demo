package com.example.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class ApiControllerAdvice : ResponseEntityExceptionHandler() {

    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleResponseStatusException(e: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        log.warn(e.toString())
        return handleExceptionInternal(e, null, HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }
}
