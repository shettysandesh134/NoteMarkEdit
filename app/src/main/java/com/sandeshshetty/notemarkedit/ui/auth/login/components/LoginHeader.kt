package com.sandeshshetty.notemarkedit.ui.auth.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sandeshshetty.notemarkedit.R
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme

/**
 * @author sandeshshetty
 * Created 6/25/25 at {TIME}
 */

@Composable
fun LoginHeader(
    alignment: Alignment.Horizontal = Alignment.Start,
    modifier: Modifier = Modifier,
    topHeader: String = stringResource(R.string.log_in),
    secondHeader: String = stringResource(R.string.capture_your_thoughts_and_ideas)
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        Text(
            text = topHeader,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = secondHeader,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun LoginHeaderPreview() {
    NoteMarkEditTheme {
        LoginHeader(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

