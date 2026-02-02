package com.jujus.vitalix.features.medications.di

import com.jujus.vitalix.core.di.AppContainer
import com.jujus.vitalix.features.medications.domain.usecases.GetMedicationsUseCase
import com.jujus.vitalix.features.medications.presentation.viewmodels.MedicationViewModelFactory

class MedicationsModule(
    private val appContainer : AppContainer){

    private fun provideGeMedicationsUseCase(): GetMedicationsUseCase{
        return GetMedicationsUseCase(appContainer.medicationsRepository)
    }

    fun provideGetMedicationsViewModelFactory() : MedicationViewModelFactory {
        return MedicationViewModelFactory(
            getMedicationsUseCase = provideGeMedicationsUseCase()
        )
    }
}