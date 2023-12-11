package com.example.kca.domain.user.entity.repository

import com.example.kca.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {

    fun findByName(name: String?) : User?

    fun existsByName(name: String) : Boolean
}