package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housecall_takehomeassissment.data.mapper.toEntity
import com.example.housecall_takehomeassissment.data.models.ConceptProperty
import com.example.housecall_takehomeassissment.data.repo.MedicationRepository
import com.example.housecall_takehomeassissment.presentation.Routes.Companion.MEDICATION_DETAILS
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.detail.model.MedicationDetailsUiEvents
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.detail.model.MedicationDetailsUiState
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicationDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val repository: MedicationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MedicationDetailsUiState(
            showAddBtn = savedStateHandle.get<Boolean>(MEDICATION_DETAILS.SHOW_ADD_TO_DB_BTN_KEY)
                ?: true,
            medicineDetailObj = savedStateHandle.get<String>(MEDICATION_DETAILS.DETAILS_OBJ_KEY)
                ?.let {
                    Gson().fromJson(it, ConceptProperty::class.java)
                })
    )
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: MedicationDetailsUiEvents) {
        when (event) {
            is MedicationDetailsUiEvents.OnAddToMyListClick -> {
                addToMyList(event.obj)
            }
        }
    }

    private fun addToMyList(obj: ConceptProperty?) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val entityObj = obj?.toEntity(userId) ?: return

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.insertDrugs(entityObj)
            if (result != -1L) {
                _uiState.update { it.copy(showAddBtn = false) }
            }
        }
    }
}