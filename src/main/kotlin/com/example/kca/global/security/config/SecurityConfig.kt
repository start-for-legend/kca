package com.example.kca.global.security.config

import com.example.kca.global.filter.ExceptionFilter
import com.example.kca.global.filter.JwtRequestFilter
import com.example.kca.global.security.handler.CustomAccessDeniedHandler
import com.example.kca.global.security.handler.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtRequestFilter: JwtRequestFilter,
    private val exceptionFilter: ExceptionFilter,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint
) {

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf().disable()
            .httpBasic().disable()
            .formLogin().disable()
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
            .anyRequest().denyAll()
        http
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(exceptionFilter, JwtRequestFilter::class.java)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
}