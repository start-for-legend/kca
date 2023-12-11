package com.example.kca.domain.user.utils.impl

import com.example.kca.domain.user.entity.repository.UserRepository
import com.example.kca.domain.user.exception.DuplicatedNameException
import com.example.kca.domain.user.presentation.data.dto.UserDto
import com.example.kca.domain.user.presentation.data.type.ValidatorType
import com.example.kca.domain.user.presentation.data.type.ValidatorType.*
import com.example.kca.domain.user.utils.AuthValidator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthValidatorImpl(
    private val userRepository: UserRepository
) : AuthValidator {

    override fun validate(validatorType: ValidatorType, userDto: UserDto) {
        when (validatorType) {
            SIGNUP -> validateName(userDto.name)
            LOGIN -> TODO()
        }
    }

    private fun validateName(name: String) {
        if (userRepository.existsByName(name)) {
            throw DuplicatedNameException()
        }
    }
}