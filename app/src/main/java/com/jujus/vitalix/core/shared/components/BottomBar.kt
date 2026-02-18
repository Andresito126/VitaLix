package com.jujus.vitalix.core.shared.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jujus.vitalix.core.navigation.Home
import com.jujus.vitalix.core.navigation.Medications

@Composable
fun BottomBar(
    navController: NavHostController,
    selectedRoute: String = "home",
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                label = "Inicio",
                icon = Icons.Default.Home,
                isSelected = selectedRoute == "home",
                onClick = { navController.navigate(Home) })
            BottomNavItem(
                label = "Calculadora",
                Icons.Default.Build,
                isSelected = selectedRoute == "home",
                onClick = { navController.navigate(Home) })
            BottomNavItem(
                label = "Medicamentos",
                Icons.Default.AccountBox,
                isSelected = selectedRoute == "medications",
                onClick = { navController.navigate(Medications) })
            BottomNavItem(
                label = "Hisotorial",
                Icons.Default.Create,
                isSelected = selectedRoute == "home",
                onClick = { navController.navigate(Home) })
            BottomNavItem(
                label = "Info",
                Icons.Default.Info,
                isSelected = selectedRoute == "home",
                onClick = { navController.navigate(Home) })
        }
    }
}

@Composable
fun BottomNavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = if (isSelected) Color(0xFF2563EB) else Color.Transparent
    val iconColor = if (isSelected) Color.White else Color(0xFF94A3B8)
    val textColor = if (isSelected) Color(0xFF2563EB) else Color(0xFF94A3B8)

    Column(
        modifier = Modifier
            .clickable { onClick() }
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(contentColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = textColor
        )
    }
}