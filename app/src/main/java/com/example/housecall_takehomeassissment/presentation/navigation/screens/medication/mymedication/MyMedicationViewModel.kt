package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication

import androidx.lifecycle.ViewModel
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.models.MyMedicationUiEvents
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.models.MyMedicationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyMedicationViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MyMedicationUiState())
    val uiState = _uiState.asStateFlow()


    fun onEvent(event: MyMedicationUiEvents) {
        when (event) {
            MyMedicationUiEvents.OnSearchClick -> {

            }

            is MyMedicationUiEvents.OnDelete -> {
                delete(event.medicine)
            }
        }
    }

    private fun delete(medicine: String) {
        _uiState.update {
            it.copy(medicineList = it.medicineList - medicine)
        }
    }

    private fun search() {
        _uiState.update {
            it.copy(medicineList = List(20) { "Medicine $it" })
        }
    }
}