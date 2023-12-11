package com.example.kca.domain.user.utils.impl

import com.example.kca.domain.user.entity.User
import com.example.kca.domain.user.presentation.data.dto.UserDto
import com.example.kca.domain.user.presentation.data.request.SignUpRequest
import com.example.kca.domain.user.utils.AuthConverter
import org.springframework.stereotype.Component

@Component
class AuthConverterImpl : AuthConverter {

    override fun toDto(request: SignUpRequest): UserDto =
        UserDto(-1, request.name, request.password)

    override fun toEntity(userDto: UserDto, encodePassword: String): User =
        User(-1, userDto.name, encodePassword)

}