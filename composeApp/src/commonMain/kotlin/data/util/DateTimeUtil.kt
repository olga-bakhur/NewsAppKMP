package data.util

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun toEpochMillis(inputDate: String): Long {
    val instant = Instant.parse(inputDate)
    val localDateTime = instant.toLocalDateTime(TimeZone.UTC)

    return localDateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

fun transformMillisToDateString(millis: Long): String {
    val instant = Instant.fromEpochMilliseconds(millis)
    val localDateTime = instant.toLocalDateTime(TimeZone.UTC)

    val month = localDateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
    val day = localDateTime.dayOfMonth
    val year = localDateTime.year
    val hour = if (localDateTime.hour < 10) "0${localDateTime.hour}" else localDateTime.hour
    val minute = if (localDateTime.minute < 10) "0${localDateTime.minute}" else localDateTime.minute

    return buildString {
        append(day)
        append(" ")
        append(month)
        append(" ")
        append(year)
        append(",")
        append("")
        append(hour)
        append(":")
        append(minute)
    }
}
