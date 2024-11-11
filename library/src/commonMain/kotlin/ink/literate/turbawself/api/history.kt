package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeHistoryPayment
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.HistoryPayment
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

suspend fun history(auth: Authentication, session: Session): List<HistoryPayment> {
  val request = Request(1, "historiques/hotes/${session.hostID}")
  request.useAuthentication(auth)
  val content = request.send(auth.client)

  return content.jsonArray.map { decodeHistoryPayment(it.jsonObject) }
}
