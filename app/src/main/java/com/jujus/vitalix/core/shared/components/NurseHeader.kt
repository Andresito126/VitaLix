package com.jujus.vitalix.core.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NurseHeader(
) {
    Row(
        modifier = Modifier

            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(44.dp),
                color = Color(0xFF2563EB),
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 2.dp
            ) {
//logo
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Vitalix",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF1E293B)
                    )
                )
                Text(
                    text = "Calculadora Clínica",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF2563EB),
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

    }
}

@Preview
@Composable
fun Previeeew(){
    NurseHeader()
}