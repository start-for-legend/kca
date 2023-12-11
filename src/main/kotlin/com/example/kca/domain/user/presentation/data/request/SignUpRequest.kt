package com.example.kca.domain.user.presentation.data.request

import javax.validation.constraints.NotBlank

data class SignUpRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val password: String,
)