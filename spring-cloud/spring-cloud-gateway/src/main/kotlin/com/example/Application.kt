package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.security.config.Customizer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User


@SpringBootApplication
class Application {

    @Bean
    fun authorization(http: ServerHttpSecurity) =
            http.httpBasic(Customizer.withDefaults())
                    .csrf { it.disable() }
                    .authorizeExchange {
                        it.pathMatchers("/proxy").authenticated()
                                .anyExchange().permitAll()
                    }
                    .build()

    @Bean
    fun authentication() =
            MapReactiveUserDetailsService(
                    User.withDefaultPasswordEncoder()
                            .username("user1")
                            .password("pass1")
                            .roles("USER", "ADMIN")
                            .build())

    @Bean
    fun redisRateLimiter() = RedisRateLimiter(5, 10)

    @Bean
    fun gateway(rlb: RouteLocatorBuilder, redisRateLimiter: RedisRateLimiter) =
            rlb.routes {
                route {
                    path("/proxy")
                    filters {
                        setPath("/users")
                        addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                        requestRateLimiter {
                            it.rateLimiter = redisRateLimiter
                        }
                    }
                    uri("http://localhost:8080")
                }
            }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
