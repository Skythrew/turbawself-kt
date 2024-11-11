package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeHost
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Host
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

suspend fun hostSiblings(auth: Authentication, session: Session): List<Host> {
  val request = Request(1, "hotes/${session.hostID}/freres-soeurs")
  request.useAuthentication(auth)
  val content = request.send(auth.client)

  return content.jsonArray.map { decodeHost(it.jsonObject) }
}
