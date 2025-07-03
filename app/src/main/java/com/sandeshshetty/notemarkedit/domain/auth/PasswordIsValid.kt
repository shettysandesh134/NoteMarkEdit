package com.sandeshshetty.notemarkedit.domain.auth

/**
 * @author sandeshshetty
 * Created 7/2/25 at {TIME}
 */
data class PasswordIsValid(
    val hasMinLength: Boolean = false,
    val hasNumber: Boolean = false,
    val hasSymbol: Boolean = false
) {
    val isValidPassword: Boolean
        get() = hasMinLength && (hasNumber || hasSymbol)
}
