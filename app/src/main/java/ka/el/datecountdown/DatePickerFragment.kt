package ka.el.datecountdown

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val onSet: (Int, Int, Int) -> Unit) : DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(
        view: DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        onSet(year, month, dayOfMonth)
    }
}