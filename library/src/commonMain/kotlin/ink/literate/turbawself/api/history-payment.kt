package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeHistoryPayment
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.HistoryPayment
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.jsonObject

suspend fun historyPayment(auth: Authentication, session: Session, paymentId: Long): HistoryPayment {
    val request = Request(2, "hotes/${session.hostID}/historiques/${paymentId}")
    request.useAuthentication(auth)
    val content = request.send(auth.client)

    return decodeHistoryPayment(content.jsonObject)
}