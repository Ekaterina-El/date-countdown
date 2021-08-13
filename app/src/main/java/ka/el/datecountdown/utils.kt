package ka.el.datecountdown

import java.text.SimpleDateFormat
import java.util.*

const val DAYS = "days"
const val HOURS = "hours"
const val MINUTES = "minutes"
const val SECONDS = "seconds"

const val secondsInDay = 60 * 60 * 24
const val secondsInHour = 60 * 60
const val secondsInMinute = 60


val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())

const val CURRENT_DATE_KEY = "current_date"

fun getDiffBetween(countToDate: Long, currentDate: Long): Map<String, Long> {
    val dif = (countToDate - currentDate) / 1000

    val daysLast = dif / (secondsInDay)
    val difBeforeDays = dif - (daysLast * secondsInDay)

    val hoursLast = difBeforeDays / (secondsInHour)
    val difBeforeHours = difBeforeDays - (hoursLast * secondsInHour)

    val minutesLast = difBeforeHours / secondsInMinute
    val difBeforeMinutes = difBeforeHours - (minutesLast * secondsInMinute)

    val secondsLast = dif - difBeforeDays - difBeforeHours - difBeforeMinutes // Need fix

    return mapOf(
        DAYS to daysLast,
        HOURS to hoursLast,
        MINUTES to minutesLast,
        SECONDS to secondsLast
    )
}

fun getCurrentDate(): Long {
    val calendar = Calendar.getInstance()

    val cday = calendar.get(Calendar.DAY_OF_MONTH)
    val cmonth = calendar.get(Calendar.MONTH)
    val cyear = calendar.get(Calendar.YEAR)
    val chours = calendar.get(Calendar.HOUR_OF_DAY)
    val cminutes = calendar.get(Calendar.MINUTE)

    return sdf.parse("$cday-$cmonth-$cyear $chours:$cminutes").time
}