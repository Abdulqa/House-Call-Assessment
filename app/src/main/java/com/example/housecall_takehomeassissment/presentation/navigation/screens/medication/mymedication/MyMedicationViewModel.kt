package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housecall_takehomeassissment.data.mapper.toConceptProperty
import com.example.housecall_takehomeassissment.data.mapper.toEntity
import com.example.housecall_takehomeassissment.data.models.ConceptProperty
import com.example.housecall_takehomeassissment.data.repo.MedicationRepository
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.models.MyMedicationUiEvents
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.mymedication.models.MyMedicationUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMedicationViewModel @Inject constructor(val repository: MedicationRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(MyMedicationUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchMyMedicationFromDb()
    }

    private fun fetchMyMedicationFromDb() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        viewModelScope.launch {
            repository.getAllDrugs(userId).onEach { meds ->
                _uiState.value =
                    _uiState.value.copy(medicineList = meds.map { it.toConceptProperty() })
            }.launchIn(this)
        }
    }

    fun onEvent(event: MyMedicationUiEvents) {
        when (event) {
            is MyMedicationUiEvents.OnDelete -> {
                delete(event.medicine)
            }
        }
    }

    private fun delete(medicine: ConceptProperty) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        viewModelScope.launch {
            repository.deleteDrug(medicine.toEntity(userId) ?: return@launch)
        }
    }

}