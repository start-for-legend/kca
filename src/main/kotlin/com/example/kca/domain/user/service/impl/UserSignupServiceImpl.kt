package com.example.kca.domain.user.service.impl

import com.example.kca.domain.user.entity.repository.UserRepository
import com.example.kca.domain.user.presentation.data.dto.UserDto
import com.example.kca.domain.user.presentation.data.type.ValidatorType
import com.example.kca.domain.user.service.UserSignupService
import com.example.kca.domain.user.utils.AuthConverter
import com.example.kca.domain.user.utils.AuthValidator
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class UserSignupServiceImpl(
    private val userRepository: UserRepository,
    private val authConverter: AuthConverter,
    private val passwordEncoder: PasswordEncoder,
    private val authValidator: AuthValidator
) : UserSignupService {

    override fun signUp(userDto: UserDto) {
        authValidator.validate(ValidatorType.SIGNUP, userDto)
            .let { authConverter.toEntity(userDto, passwordEncoder.encode(userDto.password)) }
            .let { userRepository.save(it) }
    }
}