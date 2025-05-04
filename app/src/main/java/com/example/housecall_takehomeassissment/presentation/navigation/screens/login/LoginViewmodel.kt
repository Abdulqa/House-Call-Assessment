package com.example.housecall_takehomeassissment.presentation.navigation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housecall_takehomeassissment.presentation.navigation.screens.login.model.LoginScreenUiCallbacks
import com.example.housecall_takehomeassissment.presentation.navigation.screens.login.model.LoginUiEvent
import com.example.housecall_takehomeassissment.presentation.navigation.screens.login.model.LoginUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiCallback = Channel<LoginScreenUiCallbacks>()
    val uiCallback = _uiCallback.receiveAsFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnLoginClick -> {
                login()
            }

            is LoginUiEvent.OnEmailChange -> {
                _uiState.update { it.copy(email = event.email) }
            }

            is LoginUiEvent.OnPasswordChange -> {
                _uiState.update { it.copy(password = event.password) }
            }
        }
    }

    private fun login() {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(uiState.value.email, uiState.value.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModelScope.launch {
                        _uiCallback.send(LoginScreenUiCallbacks.LoginSuccess)
                    }
                } else {
                    viewModelScope.launch {
                        _uiCallback.send(
                            LoginScreenUiCallbacks.LoginFailed(task.exception?.message.orEmpty())
                        )
                    }
                }
            }
    }
}