package ka.el.datecountdown

import android.os.CountDownTimer

class CountDownTimerApp(
    millsInFeature: Long,
    val onTickTimer: (Long) -> Unit,
    val onFinishTimer: () -> Unit,
    countDownInterval: Long = 1000,
) : CountDownTimer(millsInFeature, countDownInterval) {
    override fun onTick(millisUntilFinished: Long) {
        onTickTimer(millisUntilFinished)
    }

    override fun onFinish() {
        onFinishTimer()
    }

}