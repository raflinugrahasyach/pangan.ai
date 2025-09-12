// LOKASI: app/src/main/java/com/example/panganapp/ui/screens/EwsScreen.kt
package com.example.panganapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.panganapp.data.MockData
import com.example.panganapp.ui.components.AppHeader
import com.example.panganapp.ui.components.EwsCard

@Composable
fun EwsScreen() {
    var selectedChipIndex by remember { mutableStateOf(0) }
    val chipLabels = listOf("Semua", "Siaga", "Waspada")

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppHeader(
            title = "Peringatan Dini",
            subtitle = "Pantau potensi fluktuasi harga"
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 180.dp) // PADDING KONTEN DISERAGAMKAN
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    chipLabels.forEachIndexed { index, label ->
                        val isSelected = selectedChipIndex == index
                        OutlinedButton(
                            onClick = { selectedChipIndex = index },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent,
                                contentColor = if (isSelected) Color.Black else MaterialTheme.colorScheme.primary
                            ),
                            border = BorderStroke(1.dp, if(isSelected) Color.Transparent else MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        ) {
                            Text(label, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
            items(MockData.ewsAlerts) { alert -> EwsCard(alert = alert) }
        }
    }
}