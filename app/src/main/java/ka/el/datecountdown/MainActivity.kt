package ka.el.datecountdown

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    // TODO: делать запись в кеш о устаноавленной дате и при перезаходе устанавливать её

    private var sdf = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
    private var currentDate: Long = 0
    private var countToDate: Long = 0

    private lateinit var daysValue: TextView
    private lateinit var hoursValue: TextView
    private lateinit var minutesValue: TextView
    private lateinit var secondsValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFields()
    }

    private fun initFields() {
        daysValue = findViewById(R.id.daysValue)
        hoursValue = findViewById(R.id.hoursValue)
        minutesValue = findViewById(R.id.minutesValue)
        secondsValue = findViewById(R.id.secondsValue)
    }

    fun changeDate(view: View) {
        val datePicker = DatePickerFragment { year, month, day ->
            // For refactoring
            val timePicker = TimePickerFragment { hoursOfDay, minutes ->
                createNewCountdown(hoursOfDay, minutes, day, month, year)
            }
            timePicker.show(supportFragmentManager, "timePicker")
        }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    @SuppressLint("SimpleDateFormat")
    private fun createNewCountdown(
        hours: Int,
        minutes: Int,
        day: Int,
        month: Int,
        year: Int
    ) {
        countToDate = sdf.parse("$day-$month-$year $hours:$minutes").time
        saveDate(countToDate)

        currentDate = getCurrentDate()

        val diffs = getDiffBeetween(countToDate, currentDate)
        updateUI(diffs)
    }

    private fun saveDate(currentDate: Long) {
        val sp = getPreferences(Context.MODE_PRIVATE)
        val editable = sp.edit()
        editable.putLong(CURRENT_DATE_KEY, currentDate).apply()
    }


    private fun getCurrentDate(): Long {
        val calendar = Calendar.getInstance()

        val cday = calendar.get(Calendar.DAY_OF_MONTH)
        val cmonth = calendar.get(Calendar.MONTH)
        val cyear = calendar.get(Calendar.YEAR)
        val chours = calendar.get(Calendar.HOUR_OF_DAY)
        val cminutes = calendar.get(Calendar.MINUTE)

        return sdf.parse("$cday-$cmonth-$cyear $chours:$cminutes").time
    }

    private fun updateUI(diffs: Map<String, Long>) {
        daysValue.text = diffs[DAYS].toString()
        hoursValue.text = diffs[HOURS].toString()
        minutesValue.text = diffs[MINUTES].toString()
        secondsValue.text = diffs[SECONDS].toString()
    }
}