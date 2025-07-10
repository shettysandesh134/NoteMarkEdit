package com.sandeshshetty.notemarkedit.ui.auth.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandeshshetty.notemarkedit.R
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.buttons.NoteMarkEditButton
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.textFields.NoteMarkEditTextFields
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme
import com.sandeshshetty.notemarkedit.ui.auth.login.LoginAction

/**
 * @author sandeshshetty
 * Created 6/25/25 at {TIME}
 */

@Composable
fun LoginBottom(
    emailText: String,
    onEmailTextChange: (String) -> Unit,
    passwordText: String,
    onPasswordTextChange: (String) -> Unit,
    isLoading: Boolean = false,
    canLogin: Boolean = false,
    onLoginClicked: () -> Unit,
    dontHaveAccountClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val buttonTextColor = if (canLogin) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.12f)
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
    ) {
        NoteMarkEditTextFields(
            text = emailText,
            onValueChange = onEmailTextChange,
            hint = stringResource(R.string.email_hint),
            label = stringResource(R.string.email),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkEditTextFields(
            text = passwordText,
            onValueChange = onPasswordTextChange,
            hint = stringResource(R.string.password),
            label = stringResource(R.string.password),
            isLastTextField = true,
            isTextFieldPassword = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkEditButton(
            text = stringResource(R.string.log_in),
            onClick = { onLoginClicked() },
            isLoading = isLoading,
            enabled = canLogin,
            borderContainerColor = Color.Transparent,
            textColor = buttonTextColor,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.don_t_have_an_account),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = {
                    dontHaveAccountClicked()
                })
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun LoginBottomPreview() {
    NoteMarkEditTheme {
        LoginBottom(
            emailText = "",
            onEmailTextChange = {},
            passwordText = "",
            onPasswordTextChange = {},
            dontHaveAccountClicked = {},
            onLoginClicked = {},
            canLogin = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

