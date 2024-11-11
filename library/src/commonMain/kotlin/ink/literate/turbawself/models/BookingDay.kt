package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime

data class BookingDay(
    val id: String? = null,
    val booked: Boolean,
    val canBook: Boolean,
    val dayNumber: Short,
    val message: String,
    val reservations: Short,
    val date: LocalDateTime
)