package ka.el.datecountdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    // TODO: делать запись в кеш о устаноавленной дате и при перезаходе устанавливать её

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFields()
    }

    private fun initFields() {

    }

    fun changeDate(view: View) {
        val timePicker = TimePickerFragment() { hoursOfDay, minutes ->
            val datePicker = DatePickerFragment() { year, month, day ->
                startNewCountdown(hoursOfDay, minutes, day, month, year)
            }
            datePicker.show(supportFragmentManager, "datePicker")
        }
        timePicker.show(supportFragmentManager, "timePicker")
    }

    private fun startNewCountdown(
        hours: Int,
        minutes: Int,
        day: Int,
        month: Int,
        year: Int
    ) {
        updateUI()
    }

    private fun updateUI() {

    }
}