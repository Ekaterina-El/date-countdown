package ka.el.datecountdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeDate(view: View) {
        val datePicker = DatePickerFragment() { hoursOfDay, minutes ->
            Log.d("DATE_COUNT", "hourOfDay: $hoursOfDay | minute: $minutes")
        }
        datePicker.show(supportFragmentManager, "timePicker")

    }
}