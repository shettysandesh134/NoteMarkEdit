package com.sandeshshetty.notemarkedit.data.auth

import kotlinx.serialization.Serializable

/**
 * @author sandeshshetty
 * Created 7/5/25 at {TIME}
 */
@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
