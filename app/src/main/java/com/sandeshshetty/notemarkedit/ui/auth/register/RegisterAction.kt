package com.sandeshshetty.notemarkedit.ui.auth.register

sealed interface RegisterAction {
    data class OnUsernameChanged(val username: String): RegisterAction
    data class OnEmailChanged(val email: String): RegisterAction
    data class OnPasswordChanged(val password: String): RegisterAction
    data class OnRepeatPasswordChanged(val password: String): RegisterAction
    data object OnAlreadyHaveAccountClicked: RegisterAction
    data object OnRegisterClicked: RegisterAction
}