package com.sandeshshetty.notemarkedit.data.auth

import kotlinx.serialization.Serializable

/**
 * @author sandeshshetty
 * Created 7/3/25 at {TIME}
 */
@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
