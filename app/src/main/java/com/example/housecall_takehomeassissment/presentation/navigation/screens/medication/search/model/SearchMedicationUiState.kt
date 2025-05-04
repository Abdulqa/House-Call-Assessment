package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.model

import com.example.housecall_takehomeassissment.data.models.ConceptProperty

data class SearchMedicationUiState(
    val query: String = "",
    val medicineList: List<ConceptProperty> = emptyList()
)