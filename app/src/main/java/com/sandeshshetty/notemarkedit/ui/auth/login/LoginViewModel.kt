package com.sandeshshetty.notemarkedit.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeshshetty.notemarkedit.core.presentation.util.asUiText
import com.sandeshshetty.notemarkedit.domain.auth.AuthRepository
import com.sandeshshetty.notemarkedit.domain.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

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

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.onEmailChanged -> {
                _state.value = _state.value.copy(
                    email = action.email,
                    canLogin = action.email.isNotBlank() && _state.value.password.isNotBlank()
                )
            }
            is LoginAction.onPasswordChanged -> {
                _state.value = _state.value.copy(
                    password = action.password,
                    canLogin = action.password.isNotBlank() && _state.value.email.isNotBlank()
                )
            }
            is LoginAction.onLoginClicked -> {
                login()
            }
            else -> Unit
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoginInProgress = true
            )

            val result = authRepository.login(
                email = _state.value.email.trim().toString(),
                password = _state.value.password.toString()
            )

            _state.value = _state.value.copy(
                isLoginInProgress = false
            )

            when(result) {
                is Result.Error -> {
                    eventChannel.send(LoginEvent.LoginError(result.error.asUiText()))
                }
                is Result.Success -> {
                    eventChannel.send(LoginEvent.LoginSuccess)
                }
            }

        }
    }

}