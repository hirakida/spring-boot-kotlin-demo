package com.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun authorization(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.httpBasic(Customizer.withDefaults())
            .csrf { it.disable() }
            .authorizeExchange {
                it.pathMatchers("/proxy").authenticated()
                    .anyExchange().permitAll()
            }
            .build()

    @Bean
    fun authentication(passwordEncoder: PasswordEncoder): MapReactiveUserDetailsService {
        val userDetails =
            User.withUsername("user1")
                .password(passwordEncoder.encode("pass1"))
                .roles("USER", "ADMIN")
                .build()
        return MapReactiveUserDetailsService(userDetails)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
