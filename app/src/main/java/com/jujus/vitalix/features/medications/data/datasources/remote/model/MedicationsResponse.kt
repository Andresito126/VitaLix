package com.jujus.vitalix.features.medications.data.datasources.remote.model


data class MedicationsResponse(
    val results:List<MedicationsDto>
)

data class MedicationsDto(
    val id : String,
    val name : String,
    val concentrationMg : Double,
    val volumeMl : Double,
    val category: String,
    val description:  String,
    val indications : String,
    val contraindications: String,
    val isActive : Boolean,
    val updateAt: String
)