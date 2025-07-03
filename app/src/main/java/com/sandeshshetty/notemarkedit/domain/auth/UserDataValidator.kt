package com.sandeshshetty.notemarkedit.domain.auth

/**
 * @author sandeshshetty
 * Created 7/2/25 at {TIME}
 */
class UserDataValidator(
    private val patternValidator: PatternValidator
) {

    fun isValidUsername(username: String): Boolean {
        return username.length >= MIN_USERNAME_LENGTH
    }

    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email)
    }

    fun validatePassword(password: String): PasswordIsValid {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasNumber = password.any { it.isDigit() }
        val hasSymbol = password.any { !it.isLetterOrDigit() }
        return PasswordIsValid(
            hasMinLength = hasMinLength,
            hasNumber = hasNumber,
            hasSymbol = hasSymbol
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 8
        const val MIN_USERNAME_LENGTH = 3
    }

}