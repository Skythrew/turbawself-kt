package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeSession
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.*
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
suspend fun login(auth: Authentication): Session {
    val request = Request(1, "auth/login").setJSON(
        buildJsonObject {
            put("username", auth.username)
            put("password", auth.password)
        }
    )

    val content = request.send(auth.client)

    auth.accessToken = content["access_token"]!!.jsonPrimitive.content
    val tokenData = auth.accessToken!!.split(".")[1]
    auth.accessTokenExpiration = Json.parseToJsonElement(Base64.Default.withPadding(Base64.PaddingOption.ABSENT).decode(tokenData).decodeToString()).jsonObject["exp"]!!.jsonPrimitive.long * 1000

    return decodeSession(content)
}