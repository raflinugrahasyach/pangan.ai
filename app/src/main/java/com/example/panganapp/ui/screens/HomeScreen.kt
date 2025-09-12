// LOKASI: app/src/main/java/com/example/panganapp/ui/screens/HomeScreen.kt
package com.example.panganapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.panganapp.data.MockData
import com.example.panganapp.ui.components.AppHeader
import com.example.panganapp.ui.components.PriceCard
import com.example.panganapp.ui.components.SectionHeader

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppHeader(
            title = "E-PANGAN",
            subtitle = "Ketahanan Pangan dalam Genggaman"
        )

        LazyColumn(
            modifier = Modifier
                .padding(top = 180.dp) // PADDING KONTEN DISESUAIKAN
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    OutlinedTextField(
                        value = "", onValueChange = {}, placeholder = { Text("Cari komoditas...") },
                        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Search") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                }
            }
            item { SectionHeader(title = "Harga Terkini", onSeeAllClick = {}) }
            items(MockData.commodities) { commodity ->
                PriceCard(commodity = commodity)
            }
        }
    }
}