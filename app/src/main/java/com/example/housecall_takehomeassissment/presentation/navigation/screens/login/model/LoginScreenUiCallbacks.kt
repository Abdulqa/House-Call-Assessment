package com.example.housecall_takehomeassissment.presentation.navigation.screens.login.model

sealed interface LoginScreenUiCallbacks {
    data object LoginSuccess : LoginScreenUiCallbacks
    data class LoginFailed(val msg: String) : LoginScreenUiCallbacks
}