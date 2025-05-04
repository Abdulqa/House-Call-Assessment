package com.example.housecall_takehomeassissment.presentation.navigation.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.housecall_takehomeassissment.presentation.Routes
import com.example.housecall_takehomeassissment.presentation.navigation.screens.login.model.LoginScreenUiCallbacks
import com.example.housecall_takehomeassissment.presentation.navigation.screens.login.model.LoginUiEvent
import com.example.housecall_takehomeassissment.presentation.theme.Blue
import com.example.housecall_takehomeassissment.presentation.theme.Gray_C200
import com.example.housecall_takehomeassissment.presentation.theme.White
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewmodel = hiltViewModel()
) {

    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val uiEvents = viewModel.uiCallback

    LaunchedEffect(Unit) {
        uiEvents.collectLatest {
            when (it) {
                is LoginScreenUiCallbacks.LoginFailed -> {
                    Toast.makeText(context, it.msg, Toast.LENGTH_SHORT).show()
                }

                LoginScreenUiCallbacks.LoginSuccess -> {

                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Blue,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        TextField(
            value = uiState.email,
            onValueChange = { viewModel.onEvent(LoginUiEvent.OnEmailChange(it)) },
            label = { Text("Email") },
            placeholder = { Text("example@gmail.com") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(6.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color(0xFFE9ECF1),
                focusedContainerColor = Color(0xFFE9ECF1),
                focusedLabelColor = Gray_C200
            )
        )

        TextField(
            value = uiState.password,
            onValueChange = { viewModel.onEvent(LoginUiEvent.OnPasswordChange(it)) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(6.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color(0xFFE9ECF1),
                focusedContainerColor = Color(0xFFE9ECF1),
                focusedLabelColor = Gray_C200
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.onEvent(LoginUiEvent.OnLoginClick) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Blue)
        ) {
            Text(text = "Create Account", color = Color.White)
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}