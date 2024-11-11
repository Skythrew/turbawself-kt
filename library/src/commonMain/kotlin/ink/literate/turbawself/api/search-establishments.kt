package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeSearchedEstablishment
import ink.literate.turbawself.models.SearchedEstablishment
import io.ktor.client.*
import java.net.URLEncoder
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

data class EstablishmentsQuery(
    val city: String? = null,
    val code: Long? = null,
    /**
     * Limits the number of results.
     *
     * You can use `-1` to get all results even though the behavior is a bit weird.
     *
     * @default 10
     */
    val limit: Long = 10
)

suspend fun searchEstablishments(
    query: EstablishmentsQuery,
    client: HttpClient = HttpClient()
): List<SearchedEstablishment> {
  val request =
      Request(
          1,
          "etablissements?q=${URLEncoder.encode(query.city ?: "", Charsets.UTF_8)}&code2p5=${query.code ?:  ""}&limit=${query.limit}")
  val content = request.send(client)
  return content.jsonArray.map { decodeSearchedEstablishment(it.jsonObject) }
}
