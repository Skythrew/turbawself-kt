package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.Summary
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

fun decodeSummary(summary: JsonObject) =
    Summary(
        latestPayment =
            if (summary["latestPaiement"] != null)
                decodePayment(summary["latestPaiement"]!!.jsonObject)
            else null,
        history =
            if (summary["historiques"] != null)
                summary["historiques"]!!.jsonArray.map { decodeHistoryPayment(it.jsonObject) }
            else null,
        balances = summary["comptesHote"]!!.jsonArray.map { decodeBalance(it.jsonObject) })
