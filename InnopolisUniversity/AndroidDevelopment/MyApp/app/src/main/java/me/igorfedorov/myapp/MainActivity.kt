package me.igorfedorov.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import me.igorfedorov.myapp.feature.weather_screen.ui.WeatherScreenActivity

class MainActivity : AppCompatActivity() {

    private val mainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val weatherButton = findViewById<Button>(R.id.weatherButton)
        val settingsButton = findViewById<Button>(R.id.settingsButton)

        weatherButton.setOnClickListener {
            startActivity(Intent(this, WeatherScreenActivity::class.java))
        }

    }
}