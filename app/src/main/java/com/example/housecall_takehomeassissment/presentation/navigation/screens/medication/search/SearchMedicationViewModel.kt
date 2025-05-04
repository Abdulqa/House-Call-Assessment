package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search

import androidx.lifecycle.ViewModel
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.model.SearchMedicationUiEvents
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.model.SearchMedicationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchMedicationViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(SearchMedicationUiState())
    val uiState = _uiState.asStateFlow()


    fun onEvent(event: SearchMedicationUiEvents) {
        when (event) {
            is SearchMedicationUiEvents.OnQueryChange -> {
                _uiState.update {
                    it.copy(query = event.query)
                }
            }

            SearchMedicationUiEvents.OnSearchSubmit -> {
                search()
            }
        }
    }

    private fun search() {
        _uiState.update {
            it.copy(medicineList = List(20) { "Medicine $it" })
        }
    }
}