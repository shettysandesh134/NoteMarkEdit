package com.sandeshshetty.notemarkedit.ui.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme
import com.sandeshshetty.notemarkedit.core.presentation.util.DeviceMode
import com.sandeshshetty.notemarkedit.core.presentation.util.DeviceModeInfo
import com.sandeshshetty.notemarkedit.ui.auth.login.components.LoginBottom
import com.sandeshshetty.notemarkedit.ui.auth.login.components.LoginHeader
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoot(
    viewModel: LoginViewModel = koinViewModel(),
    dontHaveAccountClicked: () -> Unit,
    onSuccessfulLogin: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is LoginAction.OnDontHaveAccountClicked -> {
                    dontHaveAccountClicked()
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
) {


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->

        val defaultModifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .clip(
                RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            )
            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(
                horizontal = 16.dp,
                vertical = 24.dp
            )
            .consumeWindowInsets(WindowInsets.navigationBars)

        val windSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceMode = DeviceModeInfo(windSizeClass)
        when (deviceMode) {
            DeviceMode.MOBILE_PORTRAIT -> {
                Column(
                    modifier = defaultModifier
                ) {
                    LoginHeader(
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    LoginBottom(
                        emailText = state.email,
                        onEmailTextChange = {
                            onAction(LoginAction.onEmailChanged(it))
                        },
                        passwordText = state.password,
                        onPasswordTextChange = {
                            onAction(LoginAction.onPasswordChanged(it))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        dontHaveAccountClicked = {
                            onAction(LoginAction.OnDontHaveAccountClicked)
                        }
                    )
                }
            }

            DeviceMode.MOBILE_LANDSCAPE -> {
                Row(
                    modifier = defaultModifier
                        .windowInsetsPadding(WindowInsets.displayCutout)
                        .padding(horizontal = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    LoginHeader(
                        modifier = Modifier.weight(1f)
                    )
                    LoginBottom(
                        emailText = state.email,
                        onEmailTextChange = {
                            onAction(LoginAction.onEmailChanged(it))
                        },
                        passwordText = state.password,
                        onPasswordTextChange = {
                            onAction(LoginAction.onPasswordChanged(it))
                        },
                        modifier = Modifier
                            .weight(1f),
                        dontHaveAccountClicked = {
                            onAction(LoginAction.OnDontHaveAccountClicked)
                        }
                    )
                }
            }

            DeviceMode.TABLE_PORTRAIT,
            DeviceMode.TABLET_LANDSCAPE,
            DeviceMode.DESKTOP -> {
                Column(
                    modifier = defaultModifier
                        .padding(top = 48.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginHeader(
                        modifier = Modifier
                            .widthIn(max = 540.dp),
                        alignment = Alignment.CenterHorizontally
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    LoginBottom(
                        emailText = state.email,
                        onEmailTextChange = {
                            onAction(LoginAction.onEmailChanged(it))
                        },
                        passwordText = state.password,
                        onPasswordTextChange = {
                            onAction(LoginAction.onPasswordChanged(it))
                        },
                        dontHaveAccountClicked = {
                            onAction(LoginAction.OnDontHaveAccountClicked)
                        },
                        modifier = Modifier
                            .widthIn(max = 540.dp)
                    )
                }
            }
        }


    }
}

@Preview(name = "Portrait Preview", device = "spec:width=800dp,height=1280dp,dpi=240")
@Composable
private fun Preview() {
    NoteMarkEditTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}

//@Preview(name = "Landscape Preview", widthDp = 800, heightDp = 360)
//@Composable
//private fun Preview1() {
//    NoteMarkEditTheme {
//        LoginScreen(
//            state = LoginState(),
//            onAction = {}
//        )
//    }
//}