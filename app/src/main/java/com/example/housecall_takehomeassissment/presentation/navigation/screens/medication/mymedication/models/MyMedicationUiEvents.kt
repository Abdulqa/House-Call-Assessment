package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.models

sealed interface MyMedicationUiEvents {
    data object OnSearchClick : MyMedicationUiEvents
    data class OnDelete(val medicine: String) : MyMedicationUiEvents
}