package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.models

import com.example.housecall_takehomeassissment.data.models.ConceptProperty

data class MyMedicationUiState(
    val medicineList: List<ConceptProperty> = emptyList()
)