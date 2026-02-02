package com.jujus.vitalix.features.medications.presentation.screens
import com.jujus.vitalix.features.medications.domain.entities.Medication

data class MedicationUiState(
    val isLoading: Boolean = false,
    val medications: List<Medication> = emptyList(),
    val error: String? = null,
    val isRefreshing: Boolean = false
)
