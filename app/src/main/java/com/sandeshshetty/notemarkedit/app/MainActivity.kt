package com.sandeshshetty.notemarkedit.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sandeshshetty.notemarkedit.app.navigation.NavigationRoot
import com.sandeshshetty.notemarkedit.core.presentation.designsystem.theme.NoteMarkEditTheme
import com.sandeshshetty.notemarkedit.ui.auth.landing.LandingRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteMarkEditTheme {
                NavigationRoot(
                    navHostController = rememberNavController()
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteMarkEditTheme {
        Greeting("Android")
    }
}