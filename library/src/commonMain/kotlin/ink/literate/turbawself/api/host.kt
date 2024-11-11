package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeHost
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Host
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.jsonObject

suspend fun host(auth: Authentication, session: Session): Host {
  val request = Request(1, "hotes/${session.hostID}")
  request.useAuthentication(auth)
  val content = request.send(auth.client)

  return decodeHost(content.jsonObject)
}
