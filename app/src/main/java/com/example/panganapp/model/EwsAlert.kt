package com.example.panganapp.model
data class EwsAlert(val id: String, val commodityName: String, val province: String, val severity: EwsSeverity, val message: String, val dateRange: String)
enum class EwsSeverity { SIAGA, WASPADA }