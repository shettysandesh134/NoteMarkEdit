package com.sandeshshetty.notemarkedit.domain.auth

/**
 * @author sandeshshetty
 * Created 7/9/25 at {TIME}
 */
data class AuthInfo(
    val accessToken: String,
    val refreshToken: String
)
