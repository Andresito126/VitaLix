package com.jujus.vitalix.features.medications.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jujus.vitalix.core.navigation.FeatureNavGraph
import com.jujus.vitalix.core.navigation.Medications
import com.jujus.vitalix.features.medications.di.MedicationsModule
import com.jujus.vitalix.features.medications.presentation.screens.MedicationScreen
import com.jujus.vitalix.features.medications.presentation.viewmodels.MedicationViewModel


class MedicationsNavGraph (private val medicationsModule: MedicationsModule): FeatureNavGraph  {

    override fun registerGraph(navGraphBuilder : NavGraphBuilder, navController: NavHostController){
        navGraphBuilder.composable<Medications>{
            val viewModel: MedicationViewModel =viewModel(
                factory = medicationsModule.provideGetMedicationsViewModelFactory()
            )
            MedicationScreen(
                viewModel = viewModel,
                navController
            )
        }
    }
}