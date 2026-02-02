package com.jujus.vitalix.features.medications.domain.repositories

import com.jujus.vitalix.features.medications.domain.entities.Medication

interface MedicationsRepository {

    suspend fun getMedications() : List<Medication>

}
