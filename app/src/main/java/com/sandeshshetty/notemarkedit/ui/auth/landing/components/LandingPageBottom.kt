package com.sandeshshetty.notemarkedit.ui.auth.landing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.buttons.NoteMarkEditButton
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme
import com.sandeshshetty.notemarkedit.ui.auth.landing.LandingAction

/**
 * @author sandeshshetty
 * Created 6/26/25 at {TIME}
 */

@Composable
fun LandingPageBottom(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    horizontalPadding: Dp = 16.dp,
    verticalPadding: Dp = 16.dp,
    onGetStartedClicked: (LandingAction) -> Unit,
    onLogInClicked: (LandingAction) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontalPadding),
        horizontalAlignment = alignment
    ) {
        Column {
            Text(
                text = "Your Own Collection \nof Notes",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Capture your thoughts and ideas.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        NoteMarkEditButton(
            text = "Get Started",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = { onGetStartedClicked(LandingAction.onGetStartedClicked) }
        )
        NoteMarkEditButton(
            text = "Log In",
            containerColor = Color.Transparent,
            textColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = { onLogInClicked(LandingAction.onLogInClicked) }
        )
    }
}

@Preview
@Composable
private fun LandingPageBottomPreview() {
    NoteMarkEditTheme {
        LandingPageBottom(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 24.dp,
                        bottomStart = 24.dp
                    )
                )
                .background(color = MaterialTheme.colorScheme.surfaceContainerLowest),
            onGetStartedClicked = {},
            onLogInClicked = {}
        )
    }
}

