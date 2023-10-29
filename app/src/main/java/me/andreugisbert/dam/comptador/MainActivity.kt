package me.andreugisbert.dam.comptador

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.andreugisbert.dam.comptador.ui.theme.ComptadorTheme

class MainActivity : ComponentActivity() {

    private val INITIAL_TIME = 60

    private val TAG = MainActivity::class.java.simpleName

    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    internal var counter = 0
    internal var time = INITIAL_TIME

    internal var appStarted = false
    internal lateinit var countdownTimer : CountDownTimer
    //internal val initialCountDownTimer: Long = 60000
    internal val initialCountDownTimer : Long = time.toLong() *1000
    internal val intervalCountDownTimer : Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"Hola mon! onCreate")
        Log.d(TAG,counter.toString())
        Log.d(TAG,time.toString())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.couterTextView)

        tapMeButton.setOnClickListener{ view ->
            if (!appStarted){
                startGame()
            }
            val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
            view.startAnimation(bounceAnimation)
            incrementCounter()
        }
        timeTextView.text = getString(R.string.timeText, time)
    }

    private fun startGame() {
        countdownTimer.start()
        appStarted = true
    }

    private fun initCountdown() {
        countdownTimer = object : CountDownTimer(initialCountDownTimer, intervalCountDownTimer){
            override fun onTick(millisUntilFinished: Long) {
                val timeleft = millisUntilFinished / 1000
                timeTextView.text = timeleft.toString()
            }

            override fun onFinish() {
                endGame()
            }
        }
    }
    private fun incrementCounter() {
        val blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink)

        counterTextView.startAnimation(blinkAnimation)

        counterTextView.postDelayed({
            counter += 1
            counterTextView.text = counter.toString()
            counterTextView.clearAnimation()
        }, blinkAnimation.duration)
    }

    private fun endGame() {
        Toast.makeText(this,getString(R.string.endGame, counter), Toast.LENGTH_LONG).show()
            resetGame()
    }
    private fun resetGame() {
        // RESET PUNTUACIÓ A ZERO
        counter = 0
        counterTextView.text = counter.toString()

        // REINICIALITZAR EL COMPTADOR
        time = INITIAL_TIME
        timeTextView.text = time.toString()
        initCountdown()

        // GAME STARTED A FALSE
        appStarted = false
    }
}


