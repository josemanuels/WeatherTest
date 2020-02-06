package com.weather.weathertest

import android.R.attr.apiKey
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.weather.weathertest.ui.main.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        // Initialize the Places SDK
        Places.initialize(applicationContext, getString(R.string.google_places_api_key))

        // Create a new Places client instance
        val placesClient = Places.createClient(this)
    }
}
