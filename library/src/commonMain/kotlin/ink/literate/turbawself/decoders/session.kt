package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

fun decodeSession(session: JsonObject) =
    Session(
        userID = session["userId"]!!.jsonPrimitive.long,
        hostID = session["hoteId"]!!.jsonPrimitive.long)
