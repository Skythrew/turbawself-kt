package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.Terminal
import kotlinx.serialization.json.*

fun decodeTerminal(terminal: JsonObject) =
    Terminal(
        id = terminal["id"]!!.jsonPrimitive.long,
        originalID = terminal["idOrig"]!!.jsonPrimitive.long,
        code = terminal["code2p5"]!!.jsonPrimitive.long,
        name = terminal["lib"]!!.jsonPrimitive.content,
        prices = terminal["prix"]!!.jsonArray.map { decodeTerminalPrice(it.jsonObject) })
