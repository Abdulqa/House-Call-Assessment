package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.detail.model

import com.example.housecall_takehomeassissment.data.models.ConceptProperty

sealed interface MedicationDetailsUiEvents {
    data class OnAddToMyListClick(val obj: ConceptProperty?) : MedicationDetailsUiEvents
}