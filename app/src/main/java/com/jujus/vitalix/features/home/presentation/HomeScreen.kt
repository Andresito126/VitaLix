package com.jujus.vitalix.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jujus.vitalix.R
import com.jujus.vitalix.core.shared.components.BottomBar
import com.jujus.vitalix.core.shared.components.NurseHeader
import com.jujus.vitalix.core.shared.components.TitleArrow
import com.jujus.vitalix.core.ui.theme.VitaLixTheme
import com.jujus.vitalix.features.home.presentation.components.CalculatorCard
import com.jujus.vitalix.features.home.presentation.components.WelcomeBanner

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    Scaffold(
        topBar = {
            NurseHeader()
        },
        bottomBar = {
            BottomBar(
                navController = navController,
                selectedRoute = "Home"
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            item { WelcomeBanner() }


            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TitleArrow(title = "Calculadoras Frecuentes")
                    Text(
                        text = "Ver todas",
                        color = Color(0xFF2563EB),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .clickable {  }
                    )
                }
            }

            item {
                CalculatorCard(
                    title = "Dosis por Peso",
                    subtitle = "Cálculo preciso según peso del paciente",
                    icon = painterResource(id = R.drawable.weight_icon),
                    iconBgColor = Color(0xFF3B82F6),
                    onClick = { }
                )
            }



        }

    }
}
