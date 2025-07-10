package com.sandeshshetty.notemarkedit.core.presentation.designsystem.textFields

import android.R.id.mask
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandeshshetty.notemarkedit.R
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme
import kotlinx.coroutines.launch

/**
 * @author sandeshshetty
 * Created 6/25/25 at {TIME}
 */

@Composable
fun NoteMarkEditTextFields(
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,
    label: String,
    isLastTextField: Boolean = false,
    isTextFieldPassword: Boolean = false,
    isValid: Boolean = true,
    supportingText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val interactionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    }
    val isTextFieldFocused = interactionSource.collectIsFocusedAsState()

    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(7.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            maxLines = 1,
            isError = text.isNotEmpty() && !isValid,
            supportingText = {
                if (text.isNotEmpty() && !isValid) {
                    Text(
                        text = supportingText,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = if (isLastTextField) ImeAction.Done else ImeAction.Next,
                keyboardType = keyboardType
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier
                .fillMaxWidth()
            ,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = if (isTextFieldPassword && !isPasswordVisible) {
                PasswordVisualTransformation(mask = '*')
            } else VisualTransformation.None,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Transparent,
                errorTrailingIconColor = Color.Black,
            ),
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            trailingIcon = {
                if (isTextFieldPassword) {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        when {
                            isPasswordVisible -> {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.passwordoff),
                                    contentDescription = null
                                )
                            }
                            !isPasswordVisible -> {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.password_on),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFEFEFF2
)
@Composable
private fun NoteMarkEditTextFieldsPreview() {
    NoteMarkEditTheme {
        NoteMarkEditTextFields(
            text = "Sandesh",
            label = "Username",
            hint = "type username",
            onValueChange = {},
            isTextFieldPassword = true,
            isValid = true,
            supportingText = "Username must be atleast 3 characters",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

