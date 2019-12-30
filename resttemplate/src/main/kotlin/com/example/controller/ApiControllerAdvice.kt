package com.example.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ApiControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler
    fun handleHttpClientErrorException(e: HttpClientErrorException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(e, null, HttpHeaders(), e.statusCode, request)
    }

    @ExceptionHandler
    fun handleHttpServerErrorException(e: HttpServerErrorException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(e, null, HttpHeaders(), e.statusCode, request)
    }

    @ExceptionHandler
    fun handleNoSuchElementException(e: NoSuchElementException, request: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(e, null, HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }
}
