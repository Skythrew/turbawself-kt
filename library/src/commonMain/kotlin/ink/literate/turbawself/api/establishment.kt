package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeEstablishment
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Establishment
import io.ktor.client.*
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

suspend fun establishmentBy2P5(auth: Authentication, code2p5: Long): Establishment {
    val request = Request(2, "etablissements?code2p5=$code2p5")
    request.useAuthentication(auth)

    val content = request.send(auth.client)

    return decodeEstablishment(content.jsonArray[0].jsonObject)
}

suspend fun establishmentByID(id: Long, client: HttpClient = HttpClient()): Establishment {
    val request = Request(1, "etablissements/etabId/$id")
    val content = request.send(client)

    return decodeEstablishment(content.jsonObject)
}