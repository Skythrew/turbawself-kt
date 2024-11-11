package ink.literate.turbawself.utils

import kotlinx.datetime.*

data class WeekRange(
    val from: LocalDateTime,
    val to: LocalDateTime
)

fun weekRange(weekNumber: Short, year: Int): WeekRange {
    val weekNb = weekNumber - 2
    val firstDayOfYear = LocalDate(year, Month.JANUARY, 1)
    val dayOfWeek = firstDayOfYear.dayOfWeek.value
    val daysToFirstMonday = when (dayOfWeek == 7) {true -> 1 false -> 8 - dayOfWeek}
    val firstMonday = firstDayOfYear.plus(daysToFirstMonday, DateTimeUnit.DAY)
    val weekStartDate = firstMonday.plus(weekNb, DateTimeUnit.WEEK)
    val weekEndDate = weekStartDate.plus(6, DateTimeUnit.DAY)
    val weekStartDateTime = LocalDateTime(weekStartDate, LocalTime(0, 0, 0, 0))
    val weekEndDateTime = LocalDateTime(weekEndDate, LocalTime(23, 59, 59, 999))

    return WeekRange(from = weekStartDateTime, to = weekEndDateTime)
}