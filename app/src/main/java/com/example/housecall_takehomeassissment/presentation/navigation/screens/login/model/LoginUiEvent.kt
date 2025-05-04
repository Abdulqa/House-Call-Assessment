package com.example.housecall_takehomeassissment.presentation.navigation.screens.login.model

sealed interface LoginUiEvent {
    data class OnPasswordChange(val password: String) : LoginUiEvent
    data class OnEmailChange(val email: String) : LoginUiEvent
    data object OnLoginClick : LoginUiEvent
}