package com.example.kca.global.error.handler

import com.example.kca.global.error.exception.KcaException
import com.example.kca.global.error.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(KcaException::class)
    fun handler(e: KcaException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(e.errorCode.message, e.errorCode.status),
            HttpStatus.valueOf(e.errorCode.status)
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> =
        ResponseEntity(
            ErrorResponse(e.bindingResult.allErrors[0].defaultMessage, HttpStatus.BAD_REQUEST.value()),
            HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value())
        )
}