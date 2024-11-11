package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.HistoryPayment
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

fun decodeHistoryPayment(payment: JsonObject) = HistoryPayment(
    id = payment["id"]!!.jsonPrimitive.long,
    date = LocalDateTime.parse(payment["date"]!!.jsonPrimitive.content.dropLast(1)),
    details = payment["detail"]!!.jsonPrimitive.content,
    amount = (payment["debit"]!!.jsonPrimitive.doubleOrNull ?: 0).toInt(),
    credit = (payment["credit"]!!.jsonPrimitive.doubleOrNull ?: 0).toInt()
)