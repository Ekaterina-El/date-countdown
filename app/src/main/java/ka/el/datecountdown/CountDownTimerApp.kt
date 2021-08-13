package ka.el.datecountdown

import android.os.CountDownTimer

class CountDownTimerApp(
    millsInFeature: Long,
    val onTickTimer: () -> Unit,
    val onFinishTimer: () -> Unit,
    countDownInterval: Long = 1000,
) : CountDownTimer(millsInFeature, countDownInterval) {
    override fun onTick(millisUntilFinished: Long) {
        onTickTimer()
    }

    override fun onFinish() {
        onFinishTimer()
    }

}