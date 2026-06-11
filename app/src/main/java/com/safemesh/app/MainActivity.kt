package com.safemesh.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.safemesh.app.navigation.AuthNavigation
import com.safemesh.app.ui.theme.SafeMeshTheme
import com.safemesh.app.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SafeMeshTheme {
                AuthNavigation()
            }
        }
    }
}