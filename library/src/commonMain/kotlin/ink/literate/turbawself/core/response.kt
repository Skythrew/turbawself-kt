package ink.literate.turbawself.core

import io.ktor.client.statement.*
import kotlinx.serialization.json.*

suspend fun handleResponse(response: HttpResponse): JsonElement {
    val content = Json.parseToJsonElement(response.bodyAsText())

    if (response.status.value < 200 || response.status.value > 299) {
        throw Exception("${content.jsonObject["message"]!!.jsonPrimitive.content} (${response.status.value}")
    }

    return content
}