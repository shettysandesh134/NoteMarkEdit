package com.sandeshshetty.notemarkedit.ui.auth.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class LandingViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(LandingState())
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
            initialValue = LandingState()
        )

    fun onAction(action: LandingAction) {
        when (action) {
            LandingAction.onGetStartedClicked -> {

            }
            LandingAction.onLogInClicked -> TODO()
        }
    }

}