package com.example.kca.domain.user.presentation.data.dto

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiredAt: Long,
    val refreshTokenExpiredAt: Long
)