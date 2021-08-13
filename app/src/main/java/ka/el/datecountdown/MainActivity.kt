package ka.el.datecountdown

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // TODO: делать запись в кеш о устаноавленной дате и при перезаходе устанавливать её

    private var timer: CountDownTimer? = null
    private lateinit var preferences: SharedPreferences
    private var currentDate: Long = 0
    private var countToDate: Long = 0

    private lateinit var daysValue: TextView
    private lateinit var hoursValue: TextView
    private lateinit var minutesValue: TextView
    private lateinit var secondsValue: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getPreferences(Context.MODE_PRIVATE)
        getDateTo()

        initFields()
    }

    private fun initFields() {
        daysValue = findViewById(R.id.daysValue)
        hoursValue = findViewById(R.id.hoursValue)
        minutesValue = findViewById(R.id.minutesValue)
        secondsValue = findViewById(R.id.secondsValue)
    }

    private fun getDateTo() {
        countToDate = preferences.getLong(CURRENT_DATE_KEY, 0)
        if (countToDate != 0L) {
            startTimer()
        }
    }

    private fun startTimer() {
        currentDate = getCurrentDate()
        val difMSeconds = countToDate - currentDate
        if (difMSeconds > 10) {
            timer = CountDownTimerApp(
                difMSeconds,
                { onTickTimer() },
                { finishTimer() })
            timer?.start()
        }
    }

    private fun onTickTimer() {
        var currentDate = getCurrentDate()
        val diffs = getDiffBetween(countToDate, currentDate)
        updateUI(diffs)
    }


    private fun finishTimer() {
        Toast.makeText(this, "End!", Toast.LENGTH_LONG).show()
    }


    fun changeDate(view: View) {
        val datePicker = DatePickerFragment { year, month, day ->
            createTimePicker(
                day,
                month,
                year
            )
        }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun createTimePicker(
        day: Int,
        month: Int,
        year: Int
    ) {
        val timePicker = TimePickerFragment { hoursOfDay, minutes ->
            createNewCountdown(hoursOfDay, minutes, day, month, year)
        }
        timePicker.show(supportFragmentManager, "timePicker")
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

        timer?.cancel()
        startTimer()
    }

    private fun saveDate(currentDate: Long) {
        val editable = preferences.edit()
        editable.putLong(CURRENT_DATE_KEY, currentDate).apply()
    }


    private fun updateUI(diffs: Map<String, Long>) {
        daysValue.text = diffs[DAYS].toString()
        hoursValue.text = diffs[HOURS].toString()
        minutesValue.text = diffs[MINUTES].toString()
        secondsValue.text = diffs[SECONDS].toString()
    }
}