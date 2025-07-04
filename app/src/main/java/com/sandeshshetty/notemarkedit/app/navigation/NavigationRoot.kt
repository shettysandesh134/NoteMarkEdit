package com.sandeshshetty.notemarkedit.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.sandeshshetty.notemarkedit.ui.auth.landing.LandingRoot
import com.sandeshshetty.notemarkedit.ui.auth.login.LoginRoot
import com.sandeshshetty.notemarkedit.ui.auth.register.RegisterRoot

/**
 * @author sandeshshetty
 * Created 6/27/25 at {TIME}
 */

@Composable
fun NavigationRoot(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationRoute.AuthRoute
    ) {
        authGraph(navHostController)
//        composable<NavigationRoute.LandingRoute> {
//            LandingRoot(
//                onGetStartedClicked = {
//
//                },
//                onLoginClicked = {
//                    navHostController.navigate(NavigationRoute.LoginRoute)
//                }
//            )
//        }
//
//        composable<NavigationRoute.LoginRoute> {
//            LoginRoot()
//        }
    }
}

private fun NavGraphBuilder.authGraph(controller: NavHostController)  {
    navigation<NavigationRoute.AuthRoute>(
        startDestination = NavigationRoute.LandingRoute
    ) {
        composable<NavigationRoute.LandingRoute> {
            LandingRoot(
                onGetStartedClicked = {
                    controller.navigate(NavigationRoute.RegisterRoute)
                },
                onLoginClicked = {
                    controller.navigate(NavigationRoute.LoginRoute)
                }
            )
        }

        composable<NavigationRoute.LoginRoute> {
            LoginRoot(
                dontHaveAccountClicked = {
                    controller.navigate(NavigationRoute.RegisterRoute) {
                        popUpTo(NavigationRoute.LoginRoute) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulLogin = {}
            )
        }
        composable<NavigationRoute.RegisterRoute> {
            RegisterRoot(
                alreadyHaveAccountClicked = {
                    controller.navigate(NavigationRoute.LoginRoute) {
                        popUpTo(NavigationRoute.RegisterRoute) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    controller.navigate(NavigationRoute.LoginRoute)
                }
            )
        }
    }
}
