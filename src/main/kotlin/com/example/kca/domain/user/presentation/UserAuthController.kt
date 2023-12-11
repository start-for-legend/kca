package com.example.kca.domain.user.presentation

import com.example.kca.domain.user.presentation.data.request.SignUpRequest
import com.example.kca.domain.user.service.UserSignupService
import com.example.kca.domain.user.utils.AuthConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("auth")
class UserAuthController(
    private val signupService: UserSignupService,
    private val authConverter: AuthConverter
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        authConverter.toDto(request)
            .let { signupService.signUp(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}