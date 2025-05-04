package com.example.housecall_takehomeassissment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.housecall_takehomeassissment.presentation.Routes.Companion.CREATE_ACCOUNT
import com.example.housecall_takehomeassissment.presentation.Routes.Companion.LOGIN
import com.example.housecall_takehomeassissment.presentation.Routes.Companion.WELCOME_SCREEN
import com.example.housecall_takehomeassissment.presentation.navigation.screens.login.LoginScreen
import com.example.housecall_takehomeassissment.presentation.navigation.screens.WelcomeScreen
import com.example.housecall_takehomeassissment.presentation.navigation.screens.createaccount.CreateAccountScreen
import com.example.housecall_takehomeassissment.presentation.theme.HouseCallTakeHomeAssissmentTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HouseCallTakeHomeAssissmentTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = WELCOME_SCREEN
                ) {
                    composable(WELCOME_SCREEN) {
                        WelcomeScreen(navController)
                    }
                    composable(CREATE_ACCOUNT) {
                        CreateAccountScreen(navController)
                    }
                    composable(LOGIN) {
                        LoginScreen(navController)
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FirebaseAuth.getInstance().signOut()
    }
}

class Routes {
    companion object {
        const val WELCOME_SCREEN = "welcome_screen"
        const val CREATE_ACCOUNT = "create_account"
        const val LOGIN = "login"
    }
}