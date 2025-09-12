package com.example.panganapp.model
import java.time.LocalDate
data class CalendarEvent(val date: LocalDate, val title: String, val severity: EwsSeverity)