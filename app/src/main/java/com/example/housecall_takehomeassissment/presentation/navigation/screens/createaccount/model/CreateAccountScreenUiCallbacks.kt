package com.example.housecall_takehomeassissment.presentation.navigation.screens.createaccount.model

sealed interface CreateAccountScreenUiCallbacks {
    data object AccountCreated : CreateAccountScreenUiCallbacks
    data class AccountCreateFailed(val msg: String) : CreateAccountScreenUiCallbacks
}