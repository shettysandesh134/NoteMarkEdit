package com.sandeshshetty.notemarkedit.ui.auth.login

import com.sandeshshetty.notemarkedit.core.presentation.util.UiText

/**
 * @author sandeshshetty
 * Created 7/10/25 at {TIME}
 */
interface LoginEvent {
    data object LoginSuccess: LoginEvent
    data class LoginError(val error: UiText): LoginEvent
}