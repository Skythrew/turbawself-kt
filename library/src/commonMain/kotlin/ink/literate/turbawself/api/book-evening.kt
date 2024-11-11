package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.jsonPrimitive

suspend fun bookEvening (auth: Authentication, session: Session): Boolean {
    val request = Request(1, "hotes/${session.hostID}/resa-soir")
    request.useAuthentication(auth)

    val content = request.send(auth.client)

    return content.jsonPrimitive.boolean
}