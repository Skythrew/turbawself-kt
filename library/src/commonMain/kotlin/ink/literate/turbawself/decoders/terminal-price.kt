package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.TerminalPrice
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

fun decodeTerminalPrice (price: JsonObject): TerminalPrice = TerminalPrice(
    id = price["id"]!!.jsonPrimitive.long,
    originalID = price["idOrig"]!!.jsonPrimitive.long,
    name = price["lib"]!!.jsonPrimitive.content,
    price = price["prix"]!!.jsonPrimitive.int
)