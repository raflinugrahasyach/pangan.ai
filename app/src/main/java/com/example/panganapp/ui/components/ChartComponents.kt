package com.example.panganapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.panganapp.model.FeatureImportance
import com.example.panganapp.model.MarketDataPoint
import com.example.panganapp.ui.theme.*

@Composable
fun PriceLineChart(data: List<MarketDataPoint>) {
    val maxPrice = data.maxOfOrNull { maxOf(it.actualPrice, it.predictedPrice, it.upperBound) } ?: 1f
    val minPrice = data.minOfOrNull { minOf(it.actualPrice, it.predictedPrice, it.lowerBound) } ?: 0f

    Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        val stepX = size.width / (data.size - 1)
        val scaleY = size.height / (maxPrice - minPrice)

        val confidencePath = Path().apply {
            moveTo(0f, size.height - (data.first().lowerBound - minPrice) * scaleY)
            data.forEachIndexed { index, point -> lineTo(index * stepX, size.height - (point.lowerBound - minPrice) * scaleY) }
            for (i in data.size - 1 downTo 0) { lineTo(i * stepX, size.height - (data[i].upperBound - minPrice) * scaleY) }
            close()
        }
        drawPath(path = confidencePath, color = ForestGreen.copy(alpha = 0.1f))

        val predictedPath = Path().apply {
            moveTo(0f, size.height - (data.first().predictedPrice - minPrice) * scaleY)
            data.forEachIndexed { index, point -> lineTo(index * stepX, size.height - (point.predictedPrice - minPrice) * scaleY) }
        }
        drawPath(path = predictedPath, color = Gold, style = Stroke(width = 6f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 10f), 0f)))

        val actualPath = Path().apply {
            moveTo(0f, size.height - (data.first().actualPrice - minPrice) * scaleY)
            data.forEachIndexed { index, point -> lineTo(index * stepX, size.height - (point.actualPrice - minPrice) * scaleY) }
        }
        drawPath(path = actualPath, color = ForestGreen, style = Stroke(width = 8f))
    }
}

@Composable
fun FeatureImportanceBarChart(features: List<FeatureImportance>) {
    val maxScore = features.firstOrNull()?.score ?: 1f
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        features.forEach { feature ->
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = feature.featureName, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(0.45f))
                Row(modifier = Modifier.weight(0.55f), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.fillMaxWidth(feature.score / maxScore).height(12.dp).clip(RoundedCornerShape(6.dp))
                        .background(if (feature.score > 50f) ForestGreen else Gold.copy(alpha = 0.8f))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(text = "%.2f%%".format(feature.score), style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, fontSize = 12.sp))
            }
        }
    }
}

@Composable
fun LegendItem(color: Color, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(color))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodySmall)
    }
}