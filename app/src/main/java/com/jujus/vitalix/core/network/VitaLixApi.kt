package com.jujus.vitalix.core.network

import com.jujus.vitalix.features.medications.data.datasources.remote.model.CreateMedicationRequest
import com.jujus.vitalix.features.medications.data.datasources.remote.model.MedicationsDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface VitaLixApi {


    @GET("medications")
    suspend fun getMedications(): List<MedicationsDto>

    @POST("medications")
    suspend fun createMedication(@Body medication: CreateMedicationRequest): MedicationsDto

    /*
          @GET("medications/{id}")
          suspend fun getMedicationById(@Path("id") medicationId: String): Medication


          @POST("calculations")
          suspend fun saveCalculation(@Body request: CalculationRequest): CalculationResponse

          @GET("calculations")
          suspend fun getUserHistory(@Query("userId") userId: String): List<CalculationResponse>

           */
}