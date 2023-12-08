package com.example.kca.global.security.token.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
class JwtExpTimeProperties (
    val accessExp: Int,
    val refreshExp: Int
)