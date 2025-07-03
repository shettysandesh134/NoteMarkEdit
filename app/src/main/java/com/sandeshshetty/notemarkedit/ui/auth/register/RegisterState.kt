package com.sandeshshetty.notemarkedit.ui.auth.register

data class RegisterState(
    val username: String = "",
    val isUsernameValid: Boolean = false,
    val email: String = "",
    val isEmailValid: Boolean = false,
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isPasswordValid: Boolean = false,
    val repeatPassword: String = "",
    val isRepeatPasswordValid: Boolean = false,
    val isRepeatPasswordVisible: Boolean = false,
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false
)