package me.andreugisbert.dam.comptador

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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

    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    internal var counter = 0
    internal var time = 60
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.couterTextView)

        tapMeButton.setOnClickListener{
            incrementCounter()
            // TODO -> Iniciar el comptador
        }
//        timeTextView.text = time.toString()
        timeTextView.text = getString(R.string.timeText, time)
    }
    private fun incrementCounter(){
        counter += 1
        counterTextView.text = counter.toString()
    }
}

