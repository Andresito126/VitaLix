package com.jujus.vitalix.features.legalnotice.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.jujus.vitalix.core.shared.components.BottomBar
import com.jujus.vitalix.core.shared.components.NurseHeader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jujus.vitalix.features.legalnotice.presentation.components.AppLogoBadge
import com.jujus.vitalix.features.legalnotice.presentation.components.LegalNoticeCard
import com.jujus.vitalix.features.legalnotice.presentation.components.SupportSection

@Composable
fun LegalNoticeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { NurseHeader() },
        bottomBar = {
            BottomBar(navController = navController, selectedRoute = "Info")
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF8FAFC)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                AppLogoBadge()
                Text(
                    text = "VitaLix",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
                )
                Text(
                    text = "Versión 1.0.4 (Build 2026)",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                LegalNoticeCard()
            }

            item {
                SupportSection()
            }

            item {
                Text(
                    text = "Por Andre Gutierrez",
                    modifier = Modifier.padding(vertical = 24.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.LightGray
                )
            }
        }
    }
}