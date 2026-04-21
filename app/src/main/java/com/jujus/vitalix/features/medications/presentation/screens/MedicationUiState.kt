package com.jujus.vitalix.features.medications.presentation.screens
import com.jujus.vitalix.features.medications.domain.entities.Medication

data class MedicationUiState(
    val isLoading: Boolean = false,
    val medications: List<Medication> = emptyList(),
    val filteredMedications: List<Medication> = emptyList(),
    val searchQuery: String = "",
    val error: String? = null,
    val isRefreshing: Boolean = false,
    
    // Estado para AddMedication
    val name: String = "",
    val concentrationMg: String = "",
    val volumeMl: String = "",
    val category: String = "",
    val description: String = "",
    val indications: String = "",
    val contraindications: String = ""
)
