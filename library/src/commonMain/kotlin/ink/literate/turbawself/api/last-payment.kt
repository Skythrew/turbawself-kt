package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodePayment
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Payment
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.*

suspend fun lastPayment(auth: Authentication, session: Session): Payment? {
  val request = Request(2, "hotes/${session.hostID}/paiements-payline/latest")
  request.useAuthentication(auth)

  val content = request.send(auth.client)

  if (content.jsonPrimitive.contentOrNull == null) return null

  return decodePayment(content.jsonObject)
}
