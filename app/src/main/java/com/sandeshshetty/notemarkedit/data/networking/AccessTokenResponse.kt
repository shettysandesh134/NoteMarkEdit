package com.sandeshshetty.notemarkedit.data.networking

import kotlinx.serialization.Serializable

/**
 * @author sandeshshetty
 * Created 7/9/25 at {TIME}
 */
@Serializable
data class AccessTokenResponse(
    val accessToken: String,
    val refreshToken: String
)
