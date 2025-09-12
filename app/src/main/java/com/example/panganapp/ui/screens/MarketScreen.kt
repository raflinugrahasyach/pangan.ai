// LOKASI: app/src/main/java/com/example/panganapp/ui/screens/MarketScreen.kt
package com.example.panganapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.panganapp.data.MockData
import com.example.panganapp.ui.components.AppHeader
import com.example.panganapp.ui.components.FeatureImportanceBarChart
import com.example.panganapp.ui.components.LegendItem
import com.example.panganapp.ui.components.PriceLineChart
import com.example.panganapp.ui.theme.Gold
import com.example.panganapp.ui.theme.TextGray
import androidx.compose.ui.unit.dp

@Composable
fun MarketScreen() {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppHeader(
            title = "Analisis Pasar",
            subtitle = "Data peramalan dan faktor harga"
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 180.dp) // PADDING KONTEN DISERAGAMKAN
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                OutlinedTextField(value = "Daging Sapi - Sulawesi Tenggara", onValueChange = {}, readOnly = true, label = { Text("Komoditas & Wilayah") }, modifier = Modifier.fillMaxWidth())
            }
            item {
                Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), spotColor = Color.Gray.copy(alpha = 0.2f))) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Outlined.Analytics, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Grafik Peramalan", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.height(8.dp))
                        Text("Akurasi model rata-rata (MAPE): 4.88%", style = MaterialTheme.typography.bodySmall, color = TextGray)
                        Spacer(Modifier.height(16.dp))
                        PriceLineChart(data = MockData.getMarketData())
                        Spacer(Modifier.height(16.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                            LegendItem(color = MaterialTheme.colorScheme.primary, text = "Harga Aktual")
                            LegendItem(color = Gold, text = "Harga Prediksi")
                        }
                    }
                }
            }
            item {
                Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), spotColor = Color.Gray.copy(alpha = 0.2f))) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Outlined.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Faktor Pendorong Harga (XAI)", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                        }
                        Spacer(Modifier.height(16.dp))
                        FeatureImportanceBarChart(features = MockData.getFeatureImportance())
                    }
                }
            }
        }
    }
}