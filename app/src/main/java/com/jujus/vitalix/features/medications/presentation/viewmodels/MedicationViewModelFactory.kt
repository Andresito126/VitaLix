package com.jujus.vitalix.features.medications.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jujus.vitalix.features.medications.domain.usecases.GetMedicationsUseCase

class MedicationViewModelFactory (private val getMedicationsUseCase: GetMedicationsUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) :T {
        if (modelClass.isAssignableFrom(MedicationViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MedicationViewModel(getMedicationsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}