package com.example

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders

@Configuration
class GatewayConfig {
    @Bean
    fun routeLocator(rlb: RouteLocatorBuilder, rateLimiter: RedisRateLimiter): RouteLocator =
        rlb.routes {
            route {
                path("/actuator/**")
                filters {
                    addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                    requestRateLimiter {
                        it.rateLimiter = rateLimiter
                    }
                }
                uri("http://localhost:8081")
            }
        }

    @Bean
    fun redisRateLimiter(): RedisRateLimiter = RedisRateLimiter(5, 10)
}
