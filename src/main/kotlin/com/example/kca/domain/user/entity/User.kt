package com.example.kca.domain.user.entity

import javax.persistence.*

@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val password: String,
)