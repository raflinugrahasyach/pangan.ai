package com.example.panganapp.model
import androidx.compose.ui.graphics.vector.ImageVector
data class Commodity(val id: String, val name: String, val icon: ImageVector, val currentPrice: Int, val priceChange: Int, val trend: PriceTrend)
enum class PriceTrend { UP, DOWN, STABLE }