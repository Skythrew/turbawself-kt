package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime

data class Booking(
    val id: String,
    val week: Short,
    val from: LocalDateTime,
    val to: LocalDateTime,
    val terminal: Terminal,
    val days: List<BookingDay>
)