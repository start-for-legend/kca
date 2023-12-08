package com.example.kca.global.security.token

import com.example.kca.domain.auth.presentation.data.dto.TokenDto
import com.example.kca.global.security.token.common.properties.JwtExpTimeProperties
import com.example.kca.global.security.token.common.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date

@Component
class TokenGenerator(
    private val jwtProperties: JwtProperties,
    private val jwtExpTimeProperties: JwtExpTimeProperties
) {

    fun generateToken(email: String): TokenDto =
        TokenDto(
            accessToken = doGenerateAccessToken(email),
            refreshToken = doGenerateRefreshToken(email),
            accessTokenExpiredAt = jwtExpTimeProperties.accessExp.toLong(),
            refreshTokenExpiredAt = jwtExpTimeProperties.refreshExp.toLong()
        )

    private fun doGenerateAccessToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.ACCESS)
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.accessExp))
            .setIssuedAt(Date())
            .signWith(jwtProperties.accessSecret, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun doGenerateRefreshToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .claim(JwtProperties.TOKEN_TYPE, JwtProperties.REFRESH)
            .setExpiration(Date(System.currentTimeMillis() + jwtExpTimeProperties.refreshExp))
            .setIssuedAt(Date())
            .signWith(jwtProperties.refreshSecret, SignatureAlgorithm.HS256)
            .compact()
    }
}