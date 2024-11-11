package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodePayment
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Payment
import kotlinx.serialization.json.jsonObject

suspend fun payment(auth: Authentication, token: String): Payment {
  val request = Request(1, "paiements-payline/${token}")
  request.useAuthentication(auth)
  val content = request.send(auth.client)

  return decodePayment(content.jsonObject)
}
