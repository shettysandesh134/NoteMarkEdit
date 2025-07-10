package com.sandeshshetty.notemarkedit.app.navigation

import androidx.compose.material3.Text
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
    isLoggedIn: Boolean = false,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn) NavigationRoute.NoteRoute else NavigationRoute.AuthRoute
    ) {
        authGraph(isLoggedIn,navHostController)
        addNote(navHostController)
    }
}

private fun NavGraphBuilder.addNote(controller: NavHostController) {
    navigation<NavigationRoute.NoteRoute> (
        startDestination = NavigationRoute.AddNoteRoute
    ) {
        composable<NavigationRoute.AddNoteRoute> {
            Text(text = "Add Note")
        }
    }
}

private fun NavGraphBuilder.authGraph(isLoggedIn: Boolean,controller: NavHostController)  {
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
                onSuccessfulLogin = {
                   controller.navigate(NavigationRoute.AddNoteRoute)
                }
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
