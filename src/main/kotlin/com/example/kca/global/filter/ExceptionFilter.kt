package com.example.kca.global.filter

import com.example.kca.domain.user.exception.UserNotFoundException
import com.example.kca.global.error.ErrorCode
import com.example.kca.global.error.response.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        kotlin.runCatching {
            filterChain.doFilter(request, response)
        }.onFailure { throwable ->
            when (throwable) {
                is ExpiredJwtException -> setErrorResponse(ErrorCode.EXPIRATION_TOKEN, response)
                is JwtException -> setErrorResponse(ErrorCode.INVALID_TOKEN, response)
                is UserNotFoundException -> setErrorResponse(ErrorCode.USER_NOT_FOUND, response)
                else -> setErrorResponse(ErrorCode.INTERVAL_SERVER_ERROR, response)
            }
        }
    }

    private fun setErrorResponse(errorCode: ErrorCode, response: HttpServletResponse) {
        response.status = errorCode.status
        response.contentType = "application/json; charset=utf-8"
        val errorResponse = ErrorResponse(errorCode.message, errorCode.status)
        val errorResponseEntityToJson = objectMapper.writeValueAsString(errorResponse)
        response.writer.write(errorResponseEntityToJson)
    }
}