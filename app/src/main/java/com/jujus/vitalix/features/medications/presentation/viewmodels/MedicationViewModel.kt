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


    fun onNameChange(newValue: String) = _uiState.update { it.copy(name = newValue) }
    fun onConcentrationChange(newValue: String) = _uiState.update { it.copy(concentrationMg = newValue) }
    fun onVolumeChange(newValue: String) = _uiState.update { it.copy(volumeMl = newValue) }
    fun onCategoryChange(newValue: String) = _uiState.update { it.copy(category = newValue) }
    fun onDescriptionChange(newValue: String) = _uiState.update { it.copy(description = newValue) }
    fun onIndicationsChange(newValue: String) = _uiState.update { it.copy(indications = newValue) }
    fun onContraindicationsChange(newValue: String) = _uiState.update { it.copy(contraindications = newValue) }

    fun createMedication(onSuccess: () -> Unit) {
        val currentState = _uiState.value
        val medication = Medication(
            name = currentState.name,
            concentrationMg = currentState.concentrationMg.toDoubleOrNull() ?: 0.0,
            volumeMl = currentState.volumeMl.toDoubleOrNull() ?: 0.0,
            category = currentState.category,
            description = currentState.description,
            indications = currentState.indications,
            contraindications = currentState.contraindications
        )

        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = createMedicationUseCase(medication)
            result.onSuccess {
                resetForm() // Limpiamos el formulario después de éxito
                loadMedications()
                onSuccess()
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, error = error.message) }
            }
        }
    }

    private fun resetForm() {
        _uiState.update { it.copy(
            name = "", concentrationMg = "", volumeMl = "",
            category = "", description = "", indications = "",
            contraindications = ""
        )}
    }
}