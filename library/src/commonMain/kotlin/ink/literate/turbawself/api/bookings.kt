package ink.literate.turbawself.api

import ink.literate.turbawself.core.Request
import ink.literate.turbawself.decoders.decodeBooking
import ink.literate.turbawself.models.Authentication
import ink.literate.turbawself.models.Booking
import ink.literate.turbawself.models.Session
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

suspend fun bookings(auth: Authentication, session: Session, week: Short? = null): List<Booking> {
  val request =
      Request(
          1,
          "reservations/hotes/${session.hostID}/semaines${ when (week == null) { true -> "" false -> "?num=$week" } }")
  request.useAuthentication(auth)
  val content = request.send(auth.client)

  return content.jsonObject["rsvWebDto"]!!.jsonArray.map { decodeBooking(it.jsonObject) }
}
