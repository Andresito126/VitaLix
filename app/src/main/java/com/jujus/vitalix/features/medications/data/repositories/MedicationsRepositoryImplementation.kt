package com.jujus.vitalix.features.medications.data.repositories

import com.jujus.vitalix.core.network.VitaLixApi
import com.jujus.vitalix.features.medications.data.datasources.remote.mapper.toDomain
import com.jujus.vitalix.features.medications.data.datasources.remote.mapper.toRequest
import com.jujus.vitalix.features.medications.domain.entities.Medication
import com.jujus.vitalix.features.medications.domain.repositories.MedicationsRepository

class MedicationsRepositoryImplementation(
    private val api: VitaLixApi
) : MedicationsRepository {

    override suspend fun getMedications(): List<Medication>{
        val response = api.getMedications()
        return response.map { it.toDomain() }
    }

    override suspend fun createMedication(medication: Medication): Medication {
        val request = medication.toRequest()
        val response = api.createMedication(request)
        return response.toDomain()
    }
}
