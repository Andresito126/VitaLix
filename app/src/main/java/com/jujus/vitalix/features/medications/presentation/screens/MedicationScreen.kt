package com.jujus.vitalix.features.medications.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jujus.vitalix.BuildConfig
import com.jujus.vitalix.core.shared.components.BottomBar
import com.jujus.vitalix.core.shared.components.NurseHeader
import com.jujus.vitalix.core.shared.components.TitleArrow
import com.jujus.vitalix.core.ui.theme.VitaLixTheme
import com.jujus.vitalix.features.medications.presentation.components.MedicationCard
import com.jujus.vitalix.features.medications.presentation.components.SearchButton
import com.jujus.vitalix.features.medications.presentation.viewmodels.MedicationViewModel
import com.jujus.vitalix.features.medications.presentation.viewmodels.MedicationViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationScreen(viewModel : MedicationViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            NurseHeader()
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                selectedRoute = "Medications"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            TitleArrow(title = "Diccionario de Medicamentos")
            SearchButton( )
            when {
                uiState.isLoading -> {
                    Text("Cargando medicamentos...")
                }

                uiState.error != null -> {
                    Text("Error: ${uiState.error}")
                }

                else -> {
                    uiState.medications.forEach { medication ->
                        MedicationCard(
                            name = medication.name,
                            category = medication.category,
                            description = medication.description,
                            indications = medication.indications,
                            concentrationMg = medication.concentrationMg.toInt(),
                            volumeMl = medication.volumeMl.toInt()
                        )
                    }
                }
            }
        }
    }
}