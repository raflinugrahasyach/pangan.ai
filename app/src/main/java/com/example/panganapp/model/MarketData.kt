package com.example.panganapp.model
data class MarketDataPoint(val label: String, val actualPrice: Float, val predictedPrice: Float, val lowerBound: Float, val upperBound: Float)
data class FeatureImportance(val featureName: String, val score: Float)