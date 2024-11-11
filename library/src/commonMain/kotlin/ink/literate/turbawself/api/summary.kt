package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeSummary
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Session
import ink.literate.turbawself.models.Summary
import kotlinx.serialization.json.jsonObject

suspend fun summary(auth: Authentication, session: Session): Summary {
  val request = Request(2, "hotes/${session.hostID}/accueil")
  request.useAuthentication(auth)
  val content = request.send(auth.client)

  return decodeSummary(content.jsonObject)
}
