package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.PaymentStatus
import ink.literate.turbawself.models.errors.UnknownEnumValue

fun decodePaymentStatus (value: String): PaymentStatus {
    when (value) {
        "ANNULE" -> return PaymentStatus.CANCELLED
        "INIT" -> return PaymentStatus.INIT
        else -> throw UnknownEnumValue("PaymentStatus", value)
    }
}