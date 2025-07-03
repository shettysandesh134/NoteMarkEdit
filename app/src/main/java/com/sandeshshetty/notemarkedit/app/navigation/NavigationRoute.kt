package com.sandeshshetty.notemarkedit.app.navigation

import kotlinx.serialization.Serializable


/**
 * @author sandeshshetty
 * Created 6/27/25 at {TIME}
 */
sealed interface NavigationRoute {

    @Serializable
    data object AuthRoute: NavigationRoute

    @Serializable
    data object LandingRoute: NavigationRoute

    @Serializable
    data object LoginRoute: NavigationRoute

    @Serializable
    data object RegisterRoute: NavigationRoute
}