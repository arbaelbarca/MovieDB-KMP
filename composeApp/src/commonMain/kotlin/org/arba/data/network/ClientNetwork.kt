package org.arba.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.arba.utils.BASE_URL
import org.arba.utils.TOKEN_BEARER

class ClientNetwork {
    val httpClient = HttpClient {
        defaultRequest {
            url(BASE_URL)
            contentType(ContentType.Application.Json)
            header("Accept", "application/json")
            header(
                "Authorization",
                "Bearer $TOKEN_BEARER"
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 60000
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = true

                }
            )
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println("respon Logger $message")
                }

            }
        }
    }

}