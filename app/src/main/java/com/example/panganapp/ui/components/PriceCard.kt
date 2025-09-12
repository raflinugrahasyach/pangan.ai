// LOKASI: app/src/main/java/com/example/panganapp/ui/components/PriceCard.kt
package com.example.panganapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.panganapp.model.Commodity
import com.example.panganapp.model.PriceTrend
import com.example.panganapp.ui.theme.*
import java.text.NumberFormat
import java.util.*

@Composable
fun PriceCard(commodity: Commodity) {
    val (trendColor, trendIcon, trendPrefix) = when (commodity.trend) {
        PriceTrend.UP -> Triple(AlertRed, Icons.Rounded.ArrowUpward, "+")
        PriceTrend.DOWN -> Triple(StableGreen, Icons.Rounded.ArrowDownward, "") // Minus sudah ada di angka
        PriceTrend.STABLE -> Triple(TextGray, Icons.Rounded.Remove, "")
    }
    val numberFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID")).apply {
        maximumFractionDigits = 0
    }

    Card(
        modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), spotColor = Color.Gray.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(52.dp).clip(RoundedCornerShape(16.dp)).background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = commodity.icon, contentDescription = commodity.name, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(32.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = commodity.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Text(text = "${numberFormat.format(commodity.currentPrice)}/kg", style = MaterialTheme.typography.bodyMedium, color = TextGray)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.End) {
                Icon(imageVector = trendIcon, contentDescription = "Trend", tint = trendColor, modifier = Modifier.size(24.dp))
                // FORMAT HARGA DIPERBAIKI
                Text(
                    text = "$trendPrefix ${numberFormat.format(commodity.priceChange)}",
                    style = MaterialTheme.typography.labelMedium.copy(color = trendColor, fontWeight = FontWeight.Bold, fontSize = 14.sp),
                )
            }
        }
    }
}