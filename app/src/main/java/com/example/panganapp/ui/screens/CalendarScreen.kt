// LOKASI: app/src/main/java/com/example/panganapp/ui/screens/CalendarScreen.kt
package com.example.panganapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.panganapp.data.MockData
import com.example.panganapp.model.CalendarEvent
import com.example.panganapp.model.EwsSeverity
import com.example.panganapp.ui.components.AppHeader
import com.example.panganapp.ui.theme.AlertRed
import com.example.panganapp.ui.theme.TextGray
import com.example.panganapp.ui.theme.WarningYellow
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@Composable
fun CalendarScreen() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val currentMonth = YearMonth.now()
    val eventsForMonth = MockData.calendarEvents.filter { it.date.year == currentMonth.year && it.date.month == currentMonth.month }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        AppHeader(
            title = "Kalender Pangan",
            subtitle = "Jadwal penting dan prediksi musiman"
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 180.dp) // PADDING KONTEN DISERAGAMKAN
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.background),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                MonthCalendar(yearMonth = currentMonth, selectedDate = selectedDate, onDateSelected = { selectedDate = it })
            }
            item {
                Text("Agenda Bulan Ini", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            }
            if (eventsForMonth.isNotEmpty()) {
                items(eventsForMonth) { event -> EventItem(event = event) }
            } else {
                item { Text("Tidak ada agenda penting bulan ini.", style = MaterialTheme.typography.bodyMedium, color = TextGray) }
            }
        }
    }
}

@Composable
private fun EventItem(event: CalendarEvent) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Icon(Icons.Outlined.Circle, contentDescription = null, tint = if (event.severity == EwsSeverity.SIAGA) AlertRed else WarningYellow, modifier = Modifier.size(12.dp))
            Spacer(Modifier.width(16.dp))
            Column {
                Text(text = event.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text(text = "Tanggal ${event.date.dayOfMonth} ${event.date.month.getDisplayName(TextStyle.FULL, Locale("id"))}", style = MaterialTheme.typography.bodySmall, color = TextGray)
            }
        }
    }
}

@Composable
fun MonthCalendar(yearMonth: YearMonth, selectedDate: LocalDate, onDateSelected: (LocalDate) -> Unit) {
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDayOfMonth = yearMonth.atDay(1).dayOfWeek.value % 7
    val dates = (1..daysInMonth).map { yearMonth.atDay(it) }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier.shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp), spotColor = Color.Gray.copy(alpha = 0.2f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${yearMonth.month.getDisplayName(TextStyle.FULL, Locale("id"))} ${yearMonth.year}", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                listOf("M", "S", "S", "R", "K", "J", "S").forEach { day ->
                    Text(text = day, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            for (week in 0 until (daysInMonth + firstDayOfMonth + 6) / 7) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (day in 0..6) {
                        val dateIndex = week * 7 + day - firstDayOfMonth
                        if (dateIndex >= 0 && dateIndex < dates.size) {
                            DateCell(date = dates[dateIndex], isSelected = dates[dateIndex] == selectedDate, onClick = { onDateSelected(dates[dateIndex]) })
                        } else {
                            Spacer(modifier = Modifier.weight(1f).aspectRatio(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.DateCell(date: LocalDate, isSelected: Boolean, onClick: () -> Unit) {
    val event = MockData.calendarEvents.find { it.date == date }
    val indicatorColor = when (event?.severity) {
        EwsSeverity.SIAGA -> AlertRed
        EwsSeverity.WASPADA -> WarningYellow
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier.weight(1f).aspectRatio(1f).padding(4.dp).clip(CircleShape)
            .background(if (isSelected) MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f) else Color.Transparent)
            .border(width = if (isSelected) 2.dp else 0.dp, color = if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = date.dayOfMonth.toString(), style = MaterialTheme.typography.bodyMedium, color = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface, fontWeight = if(isSelected) FontWeight.Bold else FontWeight.Normal)
            if (event != null) {
                Spacer(modifier = Modifier.height(2.dp))
                Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(indicatorColor))
            }
        }
    }
}