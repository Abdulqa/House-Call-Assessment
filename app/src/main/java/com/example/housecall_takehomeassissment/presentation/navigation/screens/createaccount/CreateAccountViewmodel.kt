package com.example.housecall_takehomeassissment.presentation.navigation.screens.createaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housecall_takehomeassissment.presentation.navigation.screens.createaccount.model.CreateAccountEvent
import com.example.housecall_takehomeassissment.presentation.navigation.screens.createaccount.model.CreateAccountScreenUiCallbacks
import com.example.housecall_takehomeassissment.presentation.navigation.screens.createaccount.model.CreateAccountUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewmodel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAccountUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiCallback = Channel<CreateAccountScreenUiCallbacks>()
    val uiCallback = _uiCallback.receiveAsFlow()

    fun onEvent(event: CreateAccountEvent) {
        when (event) {
            is CreateAccountEvent.OnCreateAccountClick -> {
                createAccount()
            }

            is CreateAccountEvent.OnEmailChange -> {
                _uiState.update { it.copy(email = event.email) }
            }

            is CreateAccountEvent.OnNameChange -> {
                _uiState.update { it.copy(name = event.name) }
            }

            is CreateAccountEvent.OnPasswordChange -> {
                _uiState.update { it.copy(password = event.password) }
            }
        }
    }

    private fun createAccount() {
        val auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(uiState.value.email, uiState.value.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(uiState.value.name) // fullName = "John Doe", for example
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { profileTask ->
                            if (profileTask.isSuccessful) {
                                viewModelScope.launch {
                                    _uiCallback.send(
                                        CreateAccountScreenUiCallbacks.AccountCreated
                                    )
                                }
                            }
                        }
                } else {
                    viewModelScope.launch {
                        _uiCallback.send(
                            CreateAccountScreenUiCallbacks.AccountCreateFailed(task.exception?.message.orEmpty())
                        )
                    }
                }
            }
    }
}