package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.models

import com.example.housecall_takehomeassissment.data.models.ConceptProperty

sealed interface MyMedicationUiEvents {
    data class OnDelete(val medicine: ConceptProperty) : MyMedicationUiEvents
}