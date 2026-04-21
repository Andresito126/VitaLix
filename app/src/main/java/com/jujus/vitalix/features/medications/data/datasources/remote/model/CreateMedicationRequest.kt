package com.jujus.vitalix.features.medications.data.datasources.remote.model

data class CreateMedicationRequest(
    val name: String,
    val concentrationMg: Double,
    val volumeMl: Double,
    val category: String,
    val description: String,
    val indications: String,
    val contraindications: String
)
