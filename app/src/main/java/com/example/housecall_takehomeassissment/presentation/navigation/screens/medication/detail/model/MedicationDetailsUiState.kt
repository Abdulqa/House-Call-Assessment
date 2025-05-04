package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.detail.model

import com.example.housecall_takehomeassissment.data.models.ConceptProperty

data class MedicationDetailsUiState(
    val showAddBtn: Boolean = true,
    val medicineDetailObj: ConceptProperty? = null
)