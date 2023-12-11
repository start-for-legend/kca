package com.example.kca.global.security.principal

import com.example.kca.domain.user.entity.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class AuthDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    
    override fun loadUserByUsername(username: String?): UserDetails =
        AuthDetails(
            userRepository.findByName(username) ?: throw RuntimeException()
        )
}