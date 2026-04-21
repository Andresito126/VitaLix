package com.jujus.vitalix.features.medications.domain.usecases

import com.jujus.vitalix.features.medications.domain.entities.Medication
import com.jujus.vitalix.features.medications.domain.repositories.MedicationsRepository

class CreateMedicationUseCase(
    private val repository: MedicationsRepository
) {
    suspend operator fun invoke(medication: Medication): Result<Medication> {
        return try {
            val result = repository.createMedication(medication)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
