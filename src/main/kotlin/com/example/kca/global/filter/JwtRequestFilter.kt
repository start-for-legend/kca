package com.example.kca.global.filter

import com.example.kca.global.security.principal.AuthDetailsService
import com.example.kca.global.security.token.TokenParser
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.lang.NullPointerException
import java.lang.RuntimeException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
    private val tokenParser: TokenParser,
    private val authDetailsService: AuthDetailsService,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken = request.getHeader("Authorization")

        if (accessToken != null) {
            val token = tokenParser.parseAccessToken(request)
            token?.let { registerContext(it) }
        }

        filterChain.doFilter(request, response)
    }

    private fun registerContext(token: String) {
        try {
            val name = tokenParser.extractNameWithToken(token)
            val userDetails = authDetailsService.loadUserByUsername(name)
            val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, null)
            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
        } catch (e: NullPointerException) {
            throw RuntimeException()
        }
    }
}