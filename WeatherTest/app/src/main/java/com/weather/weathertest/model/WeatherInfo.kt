package com.weather.weathertest.model

import com.google.gson.annotations.SerializedName

// Represents the information o f Weather for a Lat/Long
class WeatherInfo {

    var latitude: Float = 0F

    var longitude: Float = 0F

    var timezone: String = ""

    // Use @SerializedName to especify a differente name in the JSON from the property in GSON.
    @SerializedName("currently")
    var currentWeatherInfo: CurrentWeatherInfo = CurrentWeatherInfo()

}