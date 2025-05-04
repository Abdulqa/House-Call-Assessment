package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.detail.model

sealed interface MedicationDetailsUiCallbacks {
    data class FailedToAddInMyList(val msg: String) : MedicationDetailsUiCallbacks
}