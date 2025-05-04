package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.model

data class SearchMedicationUiState(
    val query: String = "",
    val medicineList: List<String> = emptyList()
)