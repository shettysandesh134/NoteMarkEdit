package com.sandeshshetty.notemarkedit.data.auth

import com.sandeshshetty.notemarkedit.data.networking.post
import com.sandeshshetty.notemarkedit.domain.auth.AuthRepository
import com.sandeshshetty.notemarkedit.domain.util.DataError
import com.sandeshshetty.notemarkedit.domain.util.EmptyResult
import com.sandeshshetty.notemarkedit.domain.util.Result
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

    override suspend fun login(
        email: String,
        password: String
    ): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/api/auth/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        )

        return Result.Error(DataError.Network.UNKNOWN)
    }
}