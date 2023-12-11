package com.example.kca.domain.user.utils

import com.example.kca.domain.user.presentation.data.dto.UserDto
import com.example.kca.domain.user.presentation.data.type.ValidatorType

interface AuthValidator {

    fun validate(validatorType: ValidatorType, userDto: UserDto)
}