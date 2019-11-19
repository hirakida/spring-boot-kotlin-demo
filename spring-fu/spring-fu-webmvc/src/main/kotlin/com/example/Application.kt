package com.example

import org.springframework.boot.WebApplicationType
import org.springframework.boot.logging.LogLevel
import org.springframework.fu.kofu.application
import org.springframework.fu.kofu.webmvc.webMvc
import org.springframework.http.MediaType
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.time.LocalDateTime

val app = application(WebApplicationType.SERVLET) {
    logging {
        level = LogLevel.INFO
    }
    beans {
        bean<MainHandler>()
    }
    webMvc {
        port = if (profiles.contains("test")) 8181 else 8080
        router {
            val handler = ref<MainHandler>()
            GET("/", handler::dateTime)
        }
        converters {
            string()
            jackson()
        }
    }
}

@Suppress("UNUSED_PARAMETER")
class MainHandler {
    fun dateTime(request: ServerRequest) =
            ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(mapOf("dateTime" to LocalDateTime.now()))
}

fun main() {
    app.run()
}
