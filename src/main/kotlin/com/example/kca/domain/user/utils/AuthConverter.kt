package com.example.kca.domain.user.utils

import com.example.kca.domain.user.entity.User
import com.example.kca.domain.user.presentation.data.dto.UserDto
import com.example.kca.domain.user.presentation.data.request.SignUpRequest

interface AuthConverter {

    fun toDto(request: SignUpRequest): UserDto

    fun toEntity(userDto: UserDto, encodePassword: String): User
}