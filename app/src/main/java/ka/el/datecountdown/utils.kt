package ka.el.datecountdown

val DAYS = "days"
val HOURS = "hours"
val MINUTES = "minutes"
val SECONDS = "seconds"

 val CURRENT_DATE_KEY = "current_date"

fun getDiffBeetween(countToDate: Long, currentDate: Long): Map<String, Long> {
    val dif = (countToDate - currentDate) / 1000

    val daysLast = dif / (60 * 60 * 24)
    val difBeforeDays = dif - (daysLast * 60 * 60 * 24)

    val hoursLast = difBeforeDays / (60 * 60)
    val difBeforeHours = difBeforeDays - (hoursLast * 60 * 60)

    val minutesLast = difBeforeHours / 60
    val difBeforeMinutes = difBeforeHours - (minutesLast * 60)

    val secondsLast = dif - difBeforeDays - difBeforeHours - difBeforeMinutes

    return mapOf(
        DAYS to daysLast,
        HOURS to hoursLast,
        MINUTES to minutesLast,
        SECONDS to secondsLast
    )
}