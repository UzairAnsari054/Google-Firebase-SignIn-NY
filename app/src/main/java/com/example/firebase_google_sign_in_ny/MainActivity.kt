package com.example.firebase_google_sign_in_ny

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.example.firebase_google_sign_in_ny.ui.theme.FirebaseGoogleSigninNYTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val googleAuthClient = GoogleAuthClient(this)

        setContent {
            FirebaseGoogleSigninNYTheme {

                var isSignedIn by rememberSaveable {
                    mutableStateOf(googleAuthClient.isSignedIn())
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSignedIn) {

                        OutlinedButton(onClick = {
                            lifecycleScope.launch {
                                googleAuthClient.signOut()
                                isSignedIn = false
                            }
                        }) {
                            Text(
                                text = "Sign Out",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(
                                    horizontal = 24.dp, vertical = 4.dp
                                )
                            )
                        }

                    } else {

                        OutlinedButton(onClick = {
                            lifecycleScope.launch {
                                isSignedIn = googleAuthClient.signIn()
                            }
                        }) {
                            Text(
                                text = "Sign In With Google",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(
                                    horizontal = 24.dp, vertical = 4.dp
                                )
                            )
                        }

                    }

                }
            }
        }
    }
}