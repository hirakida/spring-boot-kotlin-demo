package com.example.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.persistence.EntityNotFoundException

@RestControllerAdvice
class ApiControllerAdvice : ResponseEntityExceptionHandler() {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleResponseStatusException(e: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        log.warn(e.toString())
        return handleExceptionInternal(e, null, HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    @ExceptionHandler
    fun handleHttpClientErrorException(e: HttpClientErrorException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(e, null, HttpHeaders(), e.statusCode, request)
    }

    @ExceptionHandler
    fun handleHttpServerErrorException(e: HttpServerErrorException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(e, null, HttpHeaders(), e.statusCode, request)
    }
}
