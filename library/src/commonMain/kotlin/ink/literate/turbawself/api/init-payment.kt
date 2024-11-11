package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Payment
import ink.literate.turbawself.models.PaymentStatus
import ink.literate.turbawself.models.Session
import io.ktor.http.*
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.*

suspend fun initPayment(auth: Authentication, session: Session, amount: Int): Payment {
    val request = Request(2, "hotes/${session.hostID}/paiements/init")
    request.useAuthentication(auth)
    request.setJSON(
        buildJsonArray {
            addJsonObject {
                    putJsonObject("paiementPayline") {
                        putJsonObject("hote") {
                            put("id", session.hostID)
                        }
                    }
                    put("montant", amount)

            }
        }
    )

    val content = request.send(auth.client)

    return Payment(
        id = null,
        hostID = session.hostID,
        amount = amount,
        status = PaymentStatus.INIT,
        token = content.jsonObject["token"]!!.jsonPrimitive.content,
        url = content.jsonObject["redirectURL"]!!.jsonPrimitive.content,
        cancelURL = "https://espacenumerique.turbo-self.com/PagePaiementRefuse.aspx?token=" + content.jsonObject["token"]!!.jsonPrimitive.content,
        returnURL = "https://espacenumerique.turbo-self.com/PagePaiementValide.aspx?token=" + content.jsonObject["token"]!!.jsonPrimitive.content,
        date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        message = ""
    )
}