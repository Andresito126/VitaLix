package com.jujus.vitalix.features.medications.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jujus.vitalix.core.navigation.AddMedication
import com.jujus.vitalix.core.shared.components.BottomBar
import com.jujus.vitalix.core.shared.components.NurseHeader
import com.jujus.vitalix.core.shared.components.TitleArrow
import com.jujus.vitalix.features.medications.presentation.components.MedicationCard
import com.jujus.vitalix.features.medications.presentation.components.SearchButton
import com.jujus.vitalix.features.medications.presentation.viewmodels.MedicationViewModel

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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AddMedication) },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Añadir medicamento")
            }
        }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.loadMedications() },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    TitleArrow(title = "Diccionario de Medicamentos")
                }
                
                item {
                    SearchButton(
                        query = uiState.searchQuery,
                        onQueryChange = { viewModel.onSearchQueryChange(it) }
                    )
                }

                when {
                    uiState.isLoading && uiState.medications.isEmpty() -> {
                        item {
                            Text(
                                text = "Cargando medicamentos...",
                                modifier = Modifier.padding(16.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    uiState.error != null -> {
                        item {
                            Text(
                                text = "Error: ${uiState.error}",
                                modifier = Modifier.padding(16.dp),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    uiState.filteredMedications.isEmpty() && uiState.searchQuery.isNotBlank() -> {
                        item {
                            Text(
                                text = "No se encontraron resultados para \"${uiState.searchQuery}\"",
                                modifier = Modifier.padding(24.dp),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    else -> {
                        items(uiState.filteredMedications) { medication ->
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
}