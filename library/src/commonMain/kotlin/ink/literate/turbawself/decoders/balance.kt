package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.Balance
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.*

fun decodeBalance(balance: JsonObject): Balance {
  // Formatted as "Montant estimé au 12/08/2024"
  val estimationDate =
      LocalDateTime.parse(
          balance["montantEstimeMsg"]!!
              .jsonPrimitive
              .content
              .slice(18..<balance["montantEstimeMsg"]!!.jsonPrimitive.content.length)
              .split('/')
              .reversed()
              .joinToString("-") + "T12:00:00")

  return Balance(
      id = balance["id"]!!.jsonPrimitive.content,
      hostId = balance["hote"]!!.jsonObject["id"]!!.jsonPrimitive.long,
      amount = balance["montant"]!!.jsonPrimitive.double,
      estimationAfterBooks = balance["montantEstime"]!!.jsonPrimitive.double,
      estimationDate)
}
