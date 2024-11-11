package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Session
import io.ktor.http.*
import kotlinx.serialization.json.*

suspend fun editPassword(auth: Authentication, session: Session, actualPassword: String, password: String): String {
    val request = Request(1, "utilisateurs/password")
    request.useAuthentication(auth)
    request.setJSON(
        buildJsonObject {
            put("id", session.userID)
            put("password", actualPassword)
            put("newPassword", password)
        }
    )
    request.setHttpMethod(HttpMethod.Put)

    val content = request.send(auth.client)

    return content.jsonArray[0].jsonObject["token"]!!.jsonPrimitive.content
}