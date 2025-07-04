package com.sandeshshetty.notemarkedit.data.auth

import com.sandeshshetty.notemarkedit.data.networking.post
import com.sandeshshetty.notemarkedit.domain.auth.AuthRepository
import com.sandeshshetty.notemarkedit.domain.util.DataError
import com.sandeshshetty.notemarkedit.domain.util.EmptyResult
import io.ktor.client.HttpClient

/**
 * @author sandeshshetty
 * Created 7/3/25 at {TIME}
 */
class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/api/auth/register",
            body = RegisterRequest(
                username = username,
                email = email,
                password = password
            )
        )
    }
}