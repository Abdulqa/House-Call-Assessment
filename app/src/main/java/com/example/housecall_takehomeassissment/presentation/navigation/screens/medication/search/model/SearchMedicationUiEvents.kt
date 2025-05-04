package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.model

sealed interface SearchMedicationUiEvents {
    class OnQueryChange(val query: String) : SearchMedicationUiEvents
    data object OnSearchSubmit : SearchMedicationUiEvents
}