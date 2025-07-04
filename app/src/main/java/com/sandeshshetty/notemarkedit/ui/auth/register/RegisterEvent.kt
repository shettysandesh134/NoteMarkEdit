package com.sandeshshetty.notemarkedit.ui.auth.register

import com.sandeshshetty.notemarkedit.core.presentation.util.UiText

/**
 * @author sandeshshetty
 * Created 7/4/25 at {TIME}
 */
interface RegisterEvent {
    data object RegisterionSuccess: RegisterEvent
    data class RegisterError(val error: UiText): RegisterEvent
}