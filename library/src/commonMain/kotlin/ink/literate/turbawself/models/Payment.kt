package ink.literate.turbawself.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.JsonElement

data class Payment(
    val id: Long? = null,
    val hostID: Long,
    val date: LocalDateTime,
    val updateDate: LocalDateTime? = null,
    val type: JsonElement? = null,
    val amount: Int,
    val status: PaymentStatus,
    val transactionID: String? = null,
    val token: String,
    val message: String,
    val url: String? = null,
    val cancelURL: String,
    val returnURL: String
)