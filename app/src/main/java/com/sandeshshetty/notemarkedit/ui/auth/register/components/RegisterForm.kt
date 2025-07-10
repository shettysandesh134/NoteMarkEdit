package com.sandeshshetty.notemarkedit.ui.auth.register.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandeshshetty.notemarkedit.R
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.buttons.NoteMarkEditButton
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.textFields.NoteMarkEditTextFields
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme
import com.sandeshshetty.notemarkedit.ui.auth.register.RegisterAction
import com.sandeshshetty.notemarkedit.ui.auth.register.RegisterState
import kotlinx.coroutines.launch

/**
 * @author sandeshshetty
 * Created 6/27/25 at {TIME}
 */

@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    val buttonTextColor = if (state.canRegister) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.12f)
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NoteMarkEditTextFields(
            text = state.username,
            onValueChange = {
                onAction(RegisterAction.OnUsernameChanged(it))
            },
            label = stringResource(R.string.username),
            hint = stringResource(R.string.username_hint),
            isValid = state.isUsernameValid,
            supportingText = stringResource(R.string.username_error),
            isLastTextField = false,
            modifier = Modifier
                .fillMaxWidth()
        )

        NoteMarkEditTextFields(
            text = state.email,
            onValueChange = {
                onAction(RegisterAction.OnEmailChanged(it))
            },
            label = stringResource(R.string.email),
            hint = stringResource(R.string.email_hint),
            isValid = state.isEmailValid,
            supportingText = stringResource(R.string.email_error),
            isLastTextField = false,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )
        NoteMarkEditTextFields(
            text = state.password,
            onValueChange = {
                onAction(RegisterAction.OnPasswordChanged(it))
            },
            label = stringResource(R.string.password),
            hint = stringResource(R.string.password),
            isLastTextField = false,
            isTextFieldPassword = true,
            isValid = state.isPasswordValid,
            supportingText = stringResource(R.string.password_error),
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth()
        )
        NoteMarkEditTextFields(
            text = state.repeatPassword,
            onValueChange = {
                onAction(RegisterAction.OnRepeatPasswordChanged(it))
            },
            label = stringResource(R.string.repeat_password),
            hint = stringResource(R.string.password),
            isLastTextField = true,
            isTextFieldPassword = true,
            isValid = state.isRepeatPasswordValid,
            supportingText = stringResource(R.string.repeat_password_error),
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth()
        )

        NoteMarkEditButton(
            text = stringResource(R.string.create_account),
            onClick = {
                onAction(RegisterAction.OnRegisterClicked)
            },
            enabled = state.canRegister,
            borderContainerColor = Color.Transparent,
            textColor = buttonTextColor,
            isLoading = state.isRegistering,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.register_last_text),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    onAction(RegisterAction.OnAlreadyHaveAccountClicked)
                })
        )
    }

}

@Preview(
    showBackground = true
)
@Composable
private fun RegisterFormPreview() {
    NoteMarkEditTheme {
        RegisterForm(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            state = RegisterState(),
            onAction = {}
        )
    }
}

