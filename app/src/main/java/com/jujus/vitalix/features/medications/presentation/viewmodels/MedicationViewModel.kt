package com.jujus.vitalix.features.medications.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jujus.vitalix.features.medications.domain.entities.Medication
import com.jujus.vitalix.features.medications.domain.usecases.CreateMedicationUseCase
import com.jujus.vitalix.features.medications.domain.usecases.GetMedicationsUseCase
import com.jujus.vitalix.features.medications.presentation.screens.MedicationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MedicationViewModel(
    private val getMedicationsUseCase: GetMedicationsUseCase,
    private val createMedicationUseCase: CreateMedicationUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(MedicationUiState())

    val uiState = _uiState.asStateFlow()

    init {
        loadMedications()
    }

    fun loadMedications() {
        _uiState.update { it.copy(isLoading = true)}

        viewModelScope.launch {
            val result = getMedicationsUseCase()
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { list ->
                        currentState.copy(
                            isLoading = false,
                            medications = list,
                            filteredMedications = filterList(list, currentState.searchQuery),
                            error = null
                        )
                    },
                    onFailure = { error ->
                        currentState.copy(isLoading = false, error = error.message)
                    }
                )
            }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = newQuery,
                filteredMedications = filterList(currentState.medications, newQuery)
            )
        }
    }

    private fun filterList(medications: List<Medication>, query: String): List<Medication> {
        if (query.isBlank()) return medications
        return medications.filter { 
            it.name.contains(query, ignoreCase = true) || 
            it.category.contains(query, ignoreCase = true)
        }
    }

    fun createMedication(medication: Medication, onSuccess: () -> Unit) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = createMedicationUseCase(medication)
            result.onSuccess {
                loadMedications()
                onSuccess()
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.message) }
            }
        }
    }
}