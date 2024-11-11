package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.BookingDay
import ink.literate.turbawself.models.Session
import io.ktor.http.*
import kotlinx.datetime.*
import kotlinx.serialization.json.*

suspend fun bookMeal(
    auth: Authentication,
    session: Session,
    bookId: String,
    day: Short,
    reservations: Short = 1,
    bookEvening: Boolean = false
): BookingDay {
  val request = Request(2, "hotes/${session.hostID}/reservations-jours")
  request.useAuthentication(auth)
  request.setJSON(
      buildJsonObject {
        put("dayOfWeek", day)
        put("dayReserv", reservations)
        putJsonObject("web") { put("id", bookId) }
        put("hasHoteResaSoirActive", bookEvening)
      })
  request.setHttpMethod(HttpMethod.Put)

  val content = request.send(auth.client)
  val bookingData = content.jsonObject

  return BookingDay(
      id = bookingData["id"]!!.jsonPrimitive.content,
      booked = bookingData["dayReserv"]!!.jsonPrimitive.int > 0,
      canBook = true,
      dayNumber = bookingData["dayOfWeek"]!!.jsonPrimitive.int.toShort(),
      message = bookingData["message"]?.jsonPrimitive?.content ?: "",
      reservations = bookingData["dayReserv"]!!.jsonPrimitive.int.toShort(),
      date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()))
}
