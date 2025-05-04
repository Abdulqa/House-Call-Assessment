package com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housecall_takehomeassissment.data.repo.MedicationRepository
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.model.SearchMedicationUiEvents
import com.example.housecall_takehomeassissment.presentation.navigation.screens.medication.search.model.SearchMedicationUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchMedicationViewModel @Inject constructor(private val repository: MedicationRepository) :
    ViewModel() {

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
        viewModelScope.launch(Dispatchers.IO) {
            val medicines = repository.getDrugsByName(_uiState.value.query)
            withContext(Dispatchers.IO) {
                _uiState.update {
                    it.copy(medicineList = medicines)
                }
            }
        }
    }
}