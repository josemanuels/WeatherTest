package com.weather.weathertest.model

// Represents a Weather Information for a Lat/Long
class CurrentWeatherInfo{

    var time: Int = 0
    var summary: String = ""
    var icon: String = ""
    var iconId: Int = 0
    var nearestStormDistance: Float= 0F
    var precipIntensity: Float = 0F
    var precipIntensityError: Float = 0F
    var precipProbability: Float = 0F
    var precipType: String = ""
    var temperature: Float = 0F
    var apparentTemperature: Float = 0F
    var dewPoint: Float = 0F
    var humidity: Float = 0F
    var pressure: Float = 0F
    var windSpeed: Float = 0F
    var windGust: Float = 0F
    var windBearing: Int = 0
    var cloudCover: Float = 0F
    var uvIndex: Int = 0
    var visibility: Float = 0F
    var ozone: Float = 0F
}