package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime

data class BookingDay(
    val token: String,
    val hostId: Long,
    val id: String,
    val booked: Boolean,
    val canBook: Boolean,
    val dayNumber: Short,
    val message: String,
    val reservations: Short,
    val date: LocalDateTime
)