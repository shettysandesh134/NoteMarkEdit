package com.sandeshshetty.notemarkedit.data.auth

import kotlinx.serialization.Serializable

/**
 * @author sandeshshetty
 * Created 7/5/25 at {TIME}
 */

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
