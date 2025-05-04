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
import com.example.housecall_takehomeassissment.presentation.Routes.Companion.MEDICATION_DETAILS
import com.example.housecall_takehomeassissment.presentation.Routes.Companion.MY_MEDICATION
import com.example.housecall_takehomeassissment.presentation.Routes.Companion.SEARCH_MEDICATION
import com.example.housecall_takehomeassissment.presentation.Routes.Companion.WELCOME_SCREEN
import com.example.housecall_takehomeassissment.presentation.navigation.screens.WelcomeScreen
import com.example.housecall_takehomeassissment.presentation.navigation.screens.createaccount.CreateAccountScreen
import com.example.housecall_takehomeassissment.presentation.navigation.screens.login.LoginScreen
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.detail.MedicationDetailScreen
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.MyMedicationScreen
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.SearchMedicationScreen
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
                    composable(MY_MEDICATION) {
                        MyMedicationScreen(navController)
                    }
                    composable(SEARCH_MEDICATION) {
                        SearchMedicationScreen(navController)
                    }
                    composable(MEDICATION_DETAILS) {
                        MedicationDetailScreen(navController)
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().signOut()
    }
}

class Routes {
    companion object {
        const val WELCOME_SCREEN = "welcome_screen"
        const val CREATE_ACCOUNT = "create_account"
        const val LOGIN = "login"
        const val MY_MEDICATION = "my_medication"
        const val SEARCH_MEDICATION = "search_medication"
        const val MEDICATION_DETAILS = "medication_details"
    }
}