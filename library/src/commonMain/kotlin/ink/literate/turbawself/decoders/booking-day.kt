package ink.literate.turbawself.decoders

import ink.literate.turbawself.models.BookingDay
import ink.literate.turbawself.utils.WeekRange
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.*

fun decodeBookingDay(bookingDay: JsonObject, weekRange: WeekRange): BookingDay {
  val dayOfWeek = bookingDay["dayOfWeek"]!!.jsonPrimitive.int.toShort()
  val date =
      weekRange.from
          .toInstant(TimeZone.currentSystemDefault())
          .plus((dayOfWeek - 1).toDuration(DurationUnit.DAYS))
          .toLocalDateTime(TimeZone.currentSystemDefault())

  return BookingDay(
      id =
          if (bookingDay["web"] != null)
              bookingDay["web"]!!.jsonObject["id"]!!.jsonPrimitive.content
          else null,
      booked = bookingDay["dayReserv"]!!.jsonPrimitive.int > 0,
      canBook = bookingDay["autorise"]!!.jsonPrimitive.boolean,
      dayNumber = dayOfWeek,
      message = bookingDay["msg"]?.jsonPrimitive?.content ?: "",
      reservations = bookingDay["dayReserv"]!!.jsonPrimitive.int.toShort(),
      date = date)
}
