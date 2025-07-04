package com.sandeshshetty.notemarkedit.ui.auth.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val canLogin: Boolean = false,
    val isLoginInProgress: Boolean = false,
)