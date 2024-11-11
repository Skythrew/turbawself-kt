package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.Booking
import ink.literate.turbawself.utils.weekRange
import kotlinx.serialization.json.*

fun decodeBooking(booking: JsonObject): Booking {
  val week = booking["semaine"]!!.jsonPrimitive.int.toShort()
  val year = booking["annee"]!!.jsonPrimitive.int
  val weekRange = weekRange(week, year)

  return Booking(
      id = booking["id"]!!.jsonPrimitive.content,
      week = week,
      from = weekRange.from,
      to = weekRange.to,
      terminal = decodeTerminal(booking["borne"]!!.jsonObject),
      days = booking["jours"]!!.jsonArray.map { decodeBookingDay(it.jsonObject, weekRange) })
}
