package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeEstablishment
import ink.literate.turbawself.models.Establishment
import io.ktor.client.*
import kotlinx.serialization.json.jsonObject

suspend fun establishment (id: Long, client: HttpClient = HttpClient()): Establishment {
    val request = Request(1, "etablissements/etabId/$id")
    val content = request.send(client)

    return decodeEstablishment(content.jsonObject)
}