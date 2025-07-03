package com.sandeshshetty.notemarkedit.ui.auth.login


sealed interface LoginAction {
    data object onLoginClicked: LoginAction
    data class onEmailChanged(val email: String): LoginAction
    data class onPasswordChanged(val password: String): LoginAction
    data object OnDontHaveAccountClicked: LoginAction
}