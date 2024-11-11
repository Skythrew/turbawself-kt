package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import io.ktor.client.*
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

suspend fun passwordReset(email: String, client: HttpClient = HttpClient()): Boolean {
  val request = Request(1, "utilisateurs/password?email=$email")
  val content = request.send(client)

  if (content.jsonObject["rejected"]!!.jsonArray.size != 0)
      throw Exception(
          "Failed to send password reset email to ${
            content.jsonObject["rejected"]!!.jsonArray.joinToString(
                ","
            ) { it.jsonPrimitive.content }
        }")

  return true
}
