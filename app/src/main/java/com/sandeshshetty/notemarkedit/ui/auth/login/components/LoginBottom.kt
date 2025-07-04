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
    modifier: Modifier = Modifier,
    dontHaveAccountClicked: () -> Unit,
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
    ) {
        NoteMarkEditTextFields(
            text = emailText,
            onValueChange = onEmailTextChange,
            label = stringResource(R.string.email),
            hint = stringResource(R.string.email_hint),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkEditTextFields(
            text = passwordText,
            onValueChange = onPasswordTextChange,
            label = stringResource(R.string.password),
            hint = stringResource(R.string.password),
            isLastTextField = true,
            isTextFieldPassword = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkEditButton(
            text = stringResource(R.string.log_in),
            onClick = {},
            isLoading = isLoading,
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

