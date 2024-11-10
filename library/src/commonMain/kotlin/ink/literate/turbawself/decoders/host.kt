package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.Host
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.*

fun decodeHost(content: JsonObject) = Host(
    id = content["id"]!!.jsonPrimitive.long,
    originalID = content["idOrig"]!!.jsonPrimitive.long,

    establishment = decodeEstablishment(content["etab"]!!.jsonObject),
    firstName = content["prenom"]!!.jsonPrimitive.content,
    lastName = content["nom"]!!.jsonPrimitive.content,
    mode = content["mode"]!!.jsonPrimitive.content,
    quality = content["qualite"]!!.jsonPrimitive.content,
    division = content["division"]!!.jsonPrimitive.content,
    lunchPrice = content["prixDej"]!!.jsonPrimitive.double,

    paymentPermission = content["droitPaiement"]!!.jsonPrimitive.boolean,
    reservationPermission = content["droitReservation"]!!.jsonPrimitive.boolean,
    cafeteriaPermission = content["droitCafeteria"]!!.jsonPrimitive.boolean,

    lastSyncDate = LocalDateTime.parse(content["dateDernSynchro"]!!.jsonPrimitive.content.dropLast(1)),
    disabled = content["desactive"]!!.jsonPrimitive.boolean,
    privatePassword = content["mdpPrive"]!!.jsonPrimitive.boolean,
    allowInsufficientCredit = content["autoriseReservSoldeIns"]!!.jsonPrimitive.boolean,
    codedCard = content["carteCodee"]!!.jsonPrimitive.long
)