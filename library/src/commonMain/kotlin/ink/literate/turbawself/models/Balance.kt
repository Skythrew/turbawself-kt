package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime

data class Balance(
    val id: String,
    val hostId: Long,
    val amount: Double,
    val estimationAfterBooks: Double,
    val estimationDate: LocalDateTime
)