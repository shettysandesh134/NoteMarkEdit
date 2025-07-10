package com.sandeshshetty.notemarkedit.ui.auth.register

import android.widget.Toast
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sandeshshetty.notemarkedit.R
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme
import com.sandeshshetty.notemarkedit.core.presentation.util.DeviceMode
import com.sandeshshetty.notemarkedit.core.presentation.util.DeviceModeInfo
import com.sandeshshetty.notemarkedit.core.presentation.util.ObserveAsEvents
import com.sandeshshetty.notemarkedit.ui.auth.login.components.LoginHeader
import com.sandeshshetty.notemarkedit.ui.auth.register.components.RegisterForm
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    alreadyHaveAccountClicked: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(viewModel.events) { event->
        when (event) {
            is RegisterEvent.RegisterError -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            is RegisterEvent.RegisterionSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    R.string.register_success,
                    Toast.LENGTH_LONG
                ).show()
                onSuccessfulRegistration()
            }
        }
    }

    RegisterScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is RegisterAction.OnAlreadyHaveAccountClicked -> {
                    alreadyHaveAccountClicked()
                }
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding->
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
                        modifier = Modifier
                            .fillMaxWidth(),
                        topHeader = stringResource(R.string.create_account),
                        secondHeader = stringResource(R.string.capture_your_thoughts_and_ideas)
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    RegisterForm(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        state = state,
                        onAction = onAction
                    )
                }
            }
            DeviceMode.MOBILE_LANDSCAPE ->{
                Row(
                    modifier = defaultModifier
                        .windowInsetsPadding(WindowInsets.displayCutout)
                        .padding(horizontal = 32.dp),
                ) {
                    LoginHeader(
                        modifier = Modifier
                            .weight(1f),
                        alignment = Alignment.CenterHorizontally,
                        topHeader = stringResource(R.string.create_account),
                        secondHeader = stringResource(R.string.capture_your_thoughts_and_ideas)
                    )
                    RegisterForm(
                        modifier = Modifier
                            .weight(1f),
                        state = state,
                        onAction = onAction
                    )
                }
            }
            DeviceMode.TABLE_PORTRAIT ,
            DeviceMode.TABLET_LANDSCAPE ,
            DeviceMode.DESKTOP -> {
                Column(
                    modifier = defaultModifier
                        .padding(top = 100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginHeader(
                        modifier = Modifier
                            .widthIn(max = 540.dp),
                        topHeader = stringResource(R.string.create_account),
                        secondHeader = stringResource(R.string.capture_your_thoughts_and_ideas)
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    RegisterForm(
                        modifier = Modifier
                            .widthIn(max = 540.dp)
                            .padding(horizontal = 8.dp),
                        state = state,
                        onAction = onAction
                    )
                }
            }
        }
    }

}

@Preview(
    device = "spec:width=411dp,height=891dp,orientation=landscape",
)
@Composable
private fun Preview() {
    NoteMarkEditTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {}
        )
    }
}