package com.sandeshshetty.notemarkedit.ui.auth.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sandeshshetty.notemarkedit.R
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme
import com.sandeshshetty.notemarkedit.core.presentation.util.DeviceMode
import com.sandeshshetty.notemarkedit.core.presentation.util.DeviceModeInfo
import com.sandeshshetty.notemarkedit.ui.auth.landing.components.LandingPageBottom

@Composable
fun LandingRoot(
    viewModel: LandingViewModel = viewModel(),
    onGetStartedClicked: () -> Unit,
    onLoginClicked: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LandingScreen(
        state = state,
        onAction = {action->
            when (action) {
                LandingAction.onGetStartedClicked -> onGetStartedClicked()
                LandingAction.onLogInClicked -> onLoginClicked()
            }
        }
    )
}

@Composable
fun LandingScreen(
    state: LandingState,
    onAction: (LandingAction) -> Unit,
) {
    val windSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceMode = DeviceModeInfo(windSizeClass)
    when (deviceMode) {
        DeviceMode.MOBILE_PORTRAIT -> {
            PortraitPhone(onAction = onAction)
        }

        DeviceMode.MOBILE_LANDSCAPE -> {
            Scaffold(
                containerColor = Color(0xffe0eaff),
                contentWindowInsets = WindowInsets.statusBars
            ) { paddingValues ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .navigationBarsPadding(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.landingpage),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxHeight()
                            .weight(1f),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillBounds
                    )
                    LandingPageBottom(
                        modifier = Modifier
                            .weight(1f)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 24.dp,
                                    bottomStart = 24.dp
                                )
                            )
                            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest),
                        alignment = Alignment.CenterHorizontally,
                        horizontalPadding = 40.dp,
                        onGetStartedClicked = onAction,
                        onLogInClicked = onAction
                    )
                }
            }
        }

        DeviceMode.TABLE_PORTRAIT,
        DeviceMode.TABLET_LANDSCAPE,
        DeviceMode.DESKTOP -> {
            Tablet(onAction = onAction)
        }
    }

}

@Composable
fun PortraitPhone(
    modifier: Modifier = Modifier,
    onAction: (LandingAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xffe0eaff))
    ) {
        Image(
            painter = painterResource(id = R.drawable.landingpage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopStart,
            contentScale = ContentScale.Fit
        )
        Scaffold(
            containerColor = Color.Transparent,
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Spacer(modifier = Modifier.weight(6f))
                    LandingPageBottom(
                        modifier = Modifier
                            .weight(4f)
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp
                                )
                            )
                            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest),
                        onGetStartedClicked = {
                            onAction(LandingAction.onGetStartedClicked)
                        },
                        onLogInClicked = {
                            onAction(LandingAction.onLogInClicked)
                        }
                    )
                }
            }
        )

    }
}

@Composable
fun Tablet(
    modifier: Modifier = Modifier,
    onAction: (LandingAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xffe0eaff))
    ) {
        Image(
            painter = painterResource(id = R.drawable.landing_tablet),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f) // Takes 70% of screen height
                .align(Alignment.TopCenter)
        )
        Scaffold(
            containerColor = Color.Transparent,
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    LandingPageBottom(
                        modifier = Modifier
                            .widthIn(max = 640.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp
                                )
                            )
                            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest),
                        horizontalPadding = 38.dp,
                        onGetStartedClicked = onAction,
                        onLogInClicked = onAction
                    )
                }
            }
        )

    }
}

@Preview(
    device = "spec:width=800dp,height=1280dp,dpi=240,orientation=portrait"
)
@Composable
private fun Preview() {
    NoteMarkEditTheme {
        LandingScreen(
            state = LandingState(),
            onAction = {}
        )
    }
}