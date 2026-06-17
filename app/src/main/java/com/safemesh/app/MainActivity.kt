package com.safemesh.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.safemesh.app.data.ThemePreference
import com.safemesh.app.navigation.AuthNavigation
import com.safemesh.app.ui.theme.SafeMeshTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val context = LocalContext.current

            val themePreference = remember {
                ThemePreference(context)
            }

            val scope = rememberCoroutineScope()

            val isDarkMode by themePreference
                .isDarkMode
                .collectAsState(initial = false)

            SafeMeshTheme(
                darkTheme = isDarkMode
            ) {

                AuthNavigation(

                    isDarkMode = isDarkMode,

                    onThemeChange = { enabled ->

                        scope.launch {
                            themePreference.saveTheme(enabled)
                        }
                    }
                )
            }
        }
    }
}