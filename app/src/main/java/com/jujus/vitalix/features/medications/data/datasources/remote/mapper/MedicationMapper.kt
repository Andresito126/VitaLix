package com.jujus.vitalix.features.medications.data.datasources.remote.mapper

import com.jujus.vitalix.features.medications.data.datasources.remote.model.MedicationsDto
import com.jujus.vitalix.features.medications.domain.entities.Medication

fun MedicationsDto.toDomain(): Medication {
    return Medication(
        id = this.id,
        name = this.name,
        concentrationMg = this.concentrationMg,
        volumeMl = this.volumeMl,
        category = this.category,
        description = this.description,
        indications = this.indications,
        contraindications = this.contraindications,
        isActive = this.isActive,
        updateAt = this.updateAt
    )
}