package com.example.panganapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.panganapp.model.EwsAlert
import com.example.panganapp.model.EwsSeverity
import com.example.panganapp.ui.theme.AlertRed
import com.example.panganapp.ui.theme.WarningYellow

@Composable
fun EwsCard(alert: EwsAlert, modifier: Modifier = Modifier) {
    val severityColor = if (alert.severity == EwsSeverity.SIAGA) AlertRed else WarningYellow

    Card(
        modifier = modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), spotColor = Color.Gray.copy(alpha = 0.2f)),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.width(6.dp).fillMaxHeight().background(severityColor))
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Outlined.Warning, contentDescription = "Severity", tint = severityColor, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${alert.severity} - ${alert.province}", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = severityColor)
                }
                Text(text = alert.commodityName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(text = alert.message, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Periode: ${alert.dateRange}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
            }
        }
    }
}