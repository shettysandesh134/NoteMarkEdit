package com.sandeshshetty.notemarkedit.ui.auth.register


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeshshetty.notemarkedit.core.presentation.util.asUiText
import com.sandeshshetty.notemarkedit.domain.auth.AuthRepository
import com.sandeshshetty.notemarkedit.domain.auth.UserDataValidator
import com.sandeshshetty.notemarkedit.domain.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RegisterState())
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
            initialValue = RegisterState()
        )

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnUsernameChanged -> {
                val isUserNameValid = userDataValidator.isValidUsername(action.username)
                _state.value = _state.value.copy(
                    username = action.username,
                    isUsernameValid = isUserNameValid,
                    canRegister = isUserNameValid && _state.value.isEmailValid && _state.value.isPasswordValid && _state.value.isRepeatPasswordValid
                )
            }

            is RegisterAction.OnEmailChanged -> {
                val isEmailValid = userDataValidator.isValidEmail(action.email)
                _state.value = _state.value.copy(
                    email = action.email,
                    isEmailValid = isEmailValid,
                    canRegister = _state.value.isUsernameValid
                            && isEmailValid
                            && _state.value.isPasswordValid
                            && _state.value.isRepeatPasswordValid
                )
            }

            is RegisterAction.OnPasswordChanged -> {
                val isPasswordValid = userDataValidator.validatePassword(action.password)
                _state.value = _state.value.copy(
                    password = action.password,
                    isPasswordValid = isPasswordValid.isValidPassword,
                    canRegister = _state.value.isUsernameValid
                            && _state.value.isEmailValid
                            && isPasswordValid.isValidPassword
                            && _state.value.isRepeatPasswordValid
                )
            }

            is RegisterAction.OnRepeatPasswordChanged -> {
                val isRepeatPasswordValid = _state.value.password.equals(action.password)
                _state.value = _state.value.copy(
                    repeatPassword = action.password,
                    isRepeatPasswordValid = isRepeatPasswordValid,
                    canRegister = _state.value.isUsernameValid
                            && _state.value.isEmailValid
                            && _state.value.isPasswordValid
                            && isRepeatPasswordValid
                )
            }

            RegisterAction.OnRegisterClicked -> {
                    register()
            }
            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isRegistering = true
            )
//            Timber.tag("RegisterViewModel").d("register: ${_state.value.username}, ${_state.value.email}, ${_state.value.password}")
            val result = authRepository.register(
                username = _state.value.username.trim(),
                email = _state.value.email.trim(),
                password = _state.value.password
            )
             _state.value = _state.value.copy(
                isRegistering = false
            )
            when(result) {
                is Result.Error -> {
                    eventChannel.send(RegisterEvent.RegisterError(result.error.asUiText()))
                }
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegisterionSuccess)
                }
            }

        }
    }

}