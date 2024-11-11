package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeBalance
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Balance
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

suspend fun balances (auth: Authentication, session: Session): List<Balance> {
    // NOTE: Not sure why the `1` is appended to the
    // URL path since it also returns the same output with /0, /2, ...
    val request = Request(1, "comptes/hotes/${session.hostID}/1")
    request.useAuthentication(auth)
    val content = request.send(auth.client)

    return content.jsonArray.map { decodeBalance(it.jsonObject) }
}