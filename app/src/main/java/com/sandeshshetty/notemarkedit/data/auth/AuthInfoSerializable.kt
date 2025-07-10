package com.sandeshshetty.notemarkedit.data.auth

import kotlinx.serialization.Serializable

/**
 * @author sandeshshetty
 * Created 7/9/25 at {TIME}
 */
@Serializable
data class AuthInfoSerializable(
    val accessToken: String,
    val refreshToken: String
)
