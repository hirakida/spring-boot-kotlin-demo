package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@SpringBootApplication
@ConfigurationPropertiesScan    // or @EnableConfigurationProperties(GitHubProperties::class)
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
