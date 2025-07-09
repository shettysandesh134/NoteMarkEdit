package com.sandeshshetty.notemarkedit.data.networking


import com.sandeshshetty.notemarkedit.domain.auth.AuthInfo
import com.sandeshshetty.notemarkedit.domain.auth.SessionStorage
import com.sandeshshetty.notemarkedit.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import timber.log.Timber

/**
 * @author sandeshshetty
 * Created 7/4/25 at {TIME}
 */
class HttpClientFactory(
    private val sessionStorage: SessionStorage
) {

    private val refreshClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            header("X-User-Email", "sandesh.shetty134@gmail.com")
        }
    }

    fun build(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Timber.d(message)
                    }
                }
                level = LogLevel.ALL
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                header("X-User-Email", "sandesh.shetty134@gmail.com")
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val authInfo = sessionStorage.get()
                        BearerTokens(
                            accessToken = authInfo?.accessToken ?: "",
                            refreshToken = authInfo?.refreshToken ?: ""
                        )
                    }
                    refreshTokens {

                        val authInfo = sessionStorage.get()
                        val response = refreshClient.post<AccessTokenRequest, AccessTokenResponse>(
                            route = "/api/auth/refresh",
                            body = AccessTokenRequest(
                                refreshToken = authInfo?.refreshToken ?: ""
                            )
                        )
                        if (response is Result.Success) {
                            val newAuthInfo = AuthInfo(
                                accessToken = response.data.accessToken,
                                refreshToken = response.data.refreshToken
                            )
                            sessionStorage.set(newAuthInfo)

                            BearerTokens(
                                accessToken = newAuthInfo.accessToken,
                                refreshToken = newAuthInfo.refreshToken
                            )
                        } else {
                            BearerTokens(
                                accessToken = "",
                                refreshToken = ""
                            )
                        }
                    }
                }
            }
        }
    }
}