package com.jujus.vitalix.features.medications.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jujus.vitalix.features.medications.domain.usecases.GetMedicationsUseCase
import com.jujus.vitalix.features.medications.presentation.screens.MedicationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MedicationViewModel(private val getMedicationsUseCase: GetMedicationsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(MedicationUiState())

    val uiState = _uiState.asStateFlow()

    init {
        loadMedications()
    }

    private fun loadMedications() {
        _uiState.update { it.copy(isLoading = true)}

        viewModelScope.launch {
            val result = getMedicationsUseCase()
            _uiState.update { currentState ->
                result.fold(
                    onSuccess = { list ->
                        currentState.copy(isLoading = false, medications = list)
                    },
                    onFailure = { error ->
                        currentState.copy(isLoading = false, error = error.message)
                    }
                )
            }
        }

    }
}