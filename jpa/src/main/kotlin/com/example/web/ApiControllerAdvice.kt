package com.example.web

import com.example.exception.DataNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class ApiControllerAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(DataNotFoundException::class)
    fun handleDataNotFoundException(e: DataNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val response = ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.reasonPhrase)
        return handleExceptionInternal(e, response, null, HttpStatus.OK, request)
    }

    data class ErrorResponse(var errorCode: Int = 0,
                             var errorMessage: String = "")
}
