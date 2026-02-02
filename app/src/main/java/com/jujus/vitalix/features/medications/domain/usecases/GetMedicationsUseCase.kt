package com.jujus.vitalix.features.medications.domain.usecases

import com.jujus.vitalix.features.medications.domain.entities.Medication
import com.jujus.vitalix.features.medications.domain.repositories.MedicationsRepository

class GetMedicationsUseCase (private val repository: MedicationsRepository){

    suspend operator fun invoke() : Result<List<Medication>>{
        return try{
            val medications = repository.getMedications()

            val filteredMedications = medications.filter { it.id.isNotBlank()}

            if(filteredMedications.isEmpty()){
                Result.failure(Exception("No se encontraron medicamentos"))
            }else{
                Result.success(filteredMedications)
            }
        }catch (e: Exception){
            Result.failure(e)

        }
    }
}