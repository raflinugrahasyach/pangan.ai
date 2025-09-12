package com.example.panganapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.example.panganapp.model.*
import java.time.LocalDate

object MockData {
    val commodities = listOf(
        Commodity("c1", "Beras Premium", Icons.Outlined.RiceBowl, 15775, 250, PriceTrend.UP),
        Commodity("c2", "Daging Sapi", Icons.Outlined.KebabDining, 135000, -1500, PriceTrend.DOWN),
        Commodity("c3", "Cabai Merah", Icons.Outlined.LocalFireDepartment, 79793, 5200, PriceTrend.UP),
        Commodity("c4", "Telur Ayam", Icons.Outlined.Egg, 31000, 0, PriceTrend.STABLE),
        Commodity("c5", "Bawang Putih", Icons.Outlined.Grass, 38500, -200, PriceTrend.DOWN),
        Commodity("c6", "Minyak Goreng", Icons.Outlined.WaterDrop, 18500, 100, PriceTrend.UP)
    )
    val ewsAlerts = listOf(
        EwsAlert("e1", "Cabai Merah", "Jawa Timur", EwsSeverity.SIAGA, "Diprediksi naik tajam 21% dalam 3 hari ke depan dampak cuaca ekstrem.", "20-23 Jul 2025"),
        EwsAlert("e2", "Daging Ayam", "Nasional", EwsSeverity.WASPADA, "Potensi kenaikan 8% mendekati periode Idul Adha.", "25 Jul - 5 Agu 2025"),
        EwsAlert("e3", "Beras Medium", "Jawa Barat", EwsSeverity.WASPADA, "Harga diperkirakan naik 5% akibat gangguan distribusi.", "19-25 Jul 2025"),
        EwsAlert("e4", "Bawang Merah", "Jawa Tengah", EwsSeverity.SIAGA, "Gagal panen berpotensi menaikkan harga hingga 30% dalam 2 minggu.", "21 Jul - 4 Agu 2025")
    )
    val calendarEvents = listOf(
        CalendarEvent(LocalDate.now().plusDays(2), "Potensi Kenaikan Harga Cabai", EwsSeverity.SIAGA),
        CalendarEvent(LocalDate.now().plusDays(3), "Potensi Kenaikan Harga Cabai", EwsSeverity.SIAGA),
        CalendarEvent(LocalDate.now().plusDays(7), "Awal Periode Kritis Idul Adha", EwsSeverity.WASPADA)
    )
    fun getMarketData(): List<MarketDataPoint> {
        return listOf(
            MarketDataPoint("Jan", 120f, 122f, 118f, 126f),
            MarketDataPoint("Feb", 122f, 124f, 120f, 128f),
            MarketDataPoint("Mar", 125f, 126f, 122f, 130f),
            MarketDataPoint("Apr", 130f, 128f, 124f, 132f),
            MarketDataPoint("Mei", 128f, 130f, 126f, 134f),
            MarketDataPoint("Jun", 135f, 133f, 129f, 137f),
            MarketDataPoint("Jul", 138f, 140f, 136f, 144f),
        )
    }
    fun getFeatureImportance(): List<FeatureImportance> {
        return listOf(
            FeatureImportance("Harga Kemarin (y_lag_1)", 81.54f),
            FeatureImportance("Periode Ramadan", 2.21f),
            FeatureImportance("Harga 7 Hari Lalu (y_lag_7)", 2.15f),
            FeatureImportance("Harga 30 Hari Lalu (y_lag_30)", 1.26f),
            FeatureImportance("Idul Fitri", 1.03f),
            FeatureImportance("Momentum Libur Nasional", 0.82f),
        ).sortedByDescending { it.score }
    }
}