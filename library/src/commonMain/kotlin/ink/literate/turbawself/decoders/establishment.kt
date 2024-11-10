package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.Establishment
import ink.literate.turbawself.models.Permissions
import ink.literate.turbawself.models.Reservation
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.json.*

fun decodeEstablishment (establishment: JsonObject) = Establishment(
    code = establishment["code2p5"]!!.jsonPrimitive.long,
    name = establishment["nom"]!!.jsonPrimitive.content,
    version = establishment["versionTS"]!!.jsonPrimitive.content,
    id = establishment["id"]!!.jsonPrimitive.long,
    address1 = establishment["adr1"]?.jsonPrimitive?.content,
    address2 = establishment["adr2"]?.jsonPrimitive?.content,
    postalCode = establishment["cp"]!!.jsonPrimitive.content,
    city = establishment["ville"]?.jsonPrimitive?.content,
    phone = establishment["tel"]?.jsonPrimitive?.content,
    website = establishment["configuration"]!!.jsonObject["url"]!!.jsonPrimitive.content,
    email = establishment["configuration"]!!.jsonObject["email"]!!.jsonPrimitive.content,
    login = establishment["login"]!!.jsonPrimitive.content,
    password = establishment["mdp"]!!.jsonPrimitive.content,
    firstSyncDate = LocalDateTime.parse(establishment["datePremSynchro"]!!.jsonPrimitive.content.dropLast(1)),
    lastSyncDate = LocalDateTime.parse(establishment["dateDernSynchro"]!!.jsonPrimitive.content.dropLast(1)),
    lastSelfSyncDate = LocalDateTime.parse(establishment["configurationSelf"]!!.jsonObject["dateDernSynchro"]!!.jsonPrimitive.content.dropLast(1)),
    turboselfID = establishment["idTurboself"]!!.jsonPrimitive.long,
    currencySymbol = establishment["currencySymbol"]!!.jsonPrimitive.content,
    hideHistory = establishment["configuration"]!!.jsonObject["cacherHistorique"]!!.jsonPrimitive.boolean,

    reservations = establishment["configurationsReservation"]!!.jsonArray.map {
        Reservation(
            id = it.jsonObject["id"]!!.jsonPrimitive.long,
            usage = it.jsonObject["usage"]!!.jsonPrimitive.long,
            elecom = it.jsonObject["elecom"]!!.jsonPrimitive.long,
            endReservation = it.jsonObject["finReserv"]?.jsonPrimitive?.content
        )
    },

    uai = establishment["numEtab"]!!.jsonPrimitive.content,
    disabled = establishment["desactive"]!!.jsonPrimitive.boolean,
    serverMAC = establishment["pcServeur"]!!.jsonPrimitive.content,
    maximumTransactionAllowed = establishment["nbTransactionAutorise"]!!.jsonPrimitive.double,
    totalReservations = establishment["nbReservationsTotal"]?.jsonPrimitive?.long ?: 0,
    welcomeMessage = establishment["configuration"]!!.jsonObject["msgAccueil"]?.jsonPrimitive?.content,

    minimumMeals = establishment["configuration"]!!.jsonObject["nbRepasMini"]?.jsonPrimitive?.longOrNull ?: 0,
    minimumClaim = (establishment["configuration"]!!.jsonObject["creanceMini"]?.jsonPrimitive?.doubleOrNull ?: 0).toDouble(),
    minimumCredit = (establishment["configuration"]!!.jsonObject["montantCreditMini"]?.jsonPrimitive?.doubleOrNull ?: 0).toDouble(),

    permissions = Permissions(
        canStudentUseQrCode = establishment["configuration"]!!.jsonObject["autoriseQrCodeEleve"]!!.jsonPrimitive.boolean,
        canCompanionUseQrCode = establishment["configuration"]!!.jsonObject["autoriseQrCodeCommensal"]!!.jsonPrimitive.boolean,
        canInternUseQrCode = establishment["configuration"]!!.jsonObject["autoriseQrCodeStagiaire"]!!.jsonPrimitive.boolean,
    )
)