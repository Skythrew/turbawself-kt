package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.JsonElement

data class Payment(
    val id: Long,
    val hostID: Long,
    val date: LocalDateTime,
    val updateDate: LocalDateTime? = null,
    val type: JsonElement? = null,
    val amount: Double,
    val status: PaymentStatus,
    val transactionID: String? = null,
    val token: String,
    val message: String
)