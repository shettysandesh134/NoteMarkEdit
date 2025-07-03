package com.sandeshshetty.notemarkedit.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class LoginViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(LoginState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LoginState()
        )



    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.onEmailChanged -> {
                _state.value = _state.value.copy(
                    email = action.email
                )
            }
            is LoginAction.onPasswordChanged -> {
                _state.value = _state.value.copy(
                    password = action.password
                )
            }
            is LoginAction.onLoginClicked -> {

            }
            else -> Unit
        }
    }

}