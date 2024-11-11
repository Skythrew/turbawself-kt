package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.SearchedEstablishment
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

fun decodeSearchedEstablishment(establishment: JsonObject): SearchedEstablishment {
  // Formatted as `{name} ({city})`
  var bundledName = (establishment["nom"]!!.jsonPrimitive.content).trim()

  var name = bundledName
  var city: String? = null

  // Remove the last `)` character.
  if (bundledName.last() == ')') {
    bundledName = bundledName.substring(0, bundledName.length - 1)

    val splitName = bundledName.split(" (")

    name = splitName[0]
    city = splitName[1]
  }

  return SearchedEstablishment(
      name = name,
      city = city,
      code = establishment["code2p5"]!!.jsonPrimitive.long,
      version = establishment["versionTS"]!!.jsonPrimitive.content)
}
