package com.sandeshshetty.notemarkedit.ui.auth.landing

sealed interface LandingAction {
    data object onGetStartedClicked : LandingAction
    data object onLogInClicked : LandingAction
}