package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.Payment
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.*

fun decodePayment(payment: JsonObject): Payment {
  val paymentToken = payment["token"]!!.jsonPrimitive.content

  return Payment(
      id = payment["id"]!!.jsonPrimitive.long,
      hostID = payment["hote"]!!.jsonObject["id"]!!.jsonPrimitive.long,
      date = LocalDateTime.parse(payment["date"]!!.jsonPrimitive.content.dropLast(1)),
      updateDate =
          if (payment["dateMaj"] != null)
              LocalDateTime.parse(payment["dateMaj"]!!.jsonPrimitive.content.dropLast(1))
          else null,
      type = payment["type"]!!,
      amount = payment["montant"]!!.jsonPrimitive.int,
      status = decodePaymentStatus(payment["statut"]!!.jsonPrimitive.content),
      transactionID = payment["idTransaction"]!!.jsonPrimitive.content,
      token = paymentToken,
      message = payment["msg"]!!.jsonPrimitive.content,
      cancelURL =
          "https://espacenumerique.turbo-self.com/PagePaiementRefuse.aspx?token=$paymentToken",
      returnURL =
          "https://espacenumerique.turbo-self.com/PagePaiementValide.aspx?token=$paymentToken",
  )
}
