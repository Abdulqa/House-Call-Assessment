package com.example.housecall_takehomeassissment.presentation.navigation.screens.createaccount.model

sealed interface CreateAccountEvent {
    data class OnNameChange(val name: String) : CreateAccountEvent
    data class OnPasswordChange(val password: String) : CreateAccountEvent
    data class OnEmailChange(val email: String) : CreateAccountEvent
    data object OnCreateAccountClick : CreateAccountEvent
}