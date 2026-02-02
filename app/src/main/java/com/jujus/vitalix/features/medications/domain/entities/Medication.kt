package com.jujus.vitalix.features.medications.domain.entities

data class Medication (
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


