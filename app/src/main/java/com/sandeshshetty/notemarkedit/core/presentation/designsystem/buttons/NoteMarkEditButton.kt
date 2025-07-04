package com.sandeshshetty.notemarkedit.core.presentation.designsystem.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme

/**
 * @author sandeshshetty
 * Created 6/25/25 at {TIME}
 */

@Composable
fun NoteMarkEditButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    borderContainerColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = textColor,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface
        ),
        border = BorderStroke(width = if (enabled) 1.dp else 0.dp, color = borderContainerColor),
        contentPadding = PaddingValues(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                color = textColor,
                modifier = Modifier
                    .alpha(if (isLoading) 0f else 1f)
            )
        }

    }
}

@Preview(
    showBackground = true
)
@Composable
private fun NoteMarkEditButtonPreview() {
    NoteMarkEditTheme {
        NoteMarkEditButton(
            text = "Log In",
            onClick = {},
            enabled = true,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.12f),
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}

