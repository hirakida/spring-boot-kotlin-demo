package com.example.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.util.StreamUtils
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.nio.charset.StandardCharsets

@Configuration
class ClientConfig {
    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        val factory = HttpComponentsClientHttpRequestFactory()
        factory.setConnectTimeout(5000)
        factory.setReadTimeout(5000)
        factory.setConnectionRequestTimeout(5000)

        return builder.requestFactory { factory }
            .errorHandler(CustomResponseErrorHandler())
            .build()
    }
}

private class CustomResponseErrorHandler : DefaultResponseErrorHandler() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(CustomResponseErrorHandler::class.java)
    }

    @Throws(IOException::class)
    override fun handleError(response: ClientHttpResponse) {
        log.error(
            "{} {} {}",
            response.statusCode, response.statusText,
            StreamUtils.copyToString(response.body, StandardCharsets.UTF_8)
        )
        super.handleError(response)
    }
}
