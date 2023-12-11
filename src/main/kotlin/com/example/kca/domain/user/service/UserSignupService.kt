package com.example.kca.domain.user.service

import com.example.kca.domain.user.presentation.data.dto.UserDto

interface UserSignupService {

    fun signUp(userDto: UserDto)
}