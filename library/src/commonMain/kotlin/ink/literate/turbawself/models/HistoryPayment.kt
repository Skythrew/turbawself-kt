package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime

data class HistoryPayment(
    val id: Long,
    val date: LocalDateTime,
    val details: String,
    val amount: Int,
    val credit: Int
)