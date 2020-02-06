package com.weather.weathertest.repository

import com.weather.weathertest.model.WeatherInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// Declares the Service Interface for the Weather Service
interface WeatherLocatorService {

    @GET("forecast/f3bbe50984497c134e5192963e55c165/{latitude},{longitude}")
    suspend fun getCurrentWeatherForLocation(@Path("latitude") lat: String, @Path("longitude") lon: String): Response<WeatherInfo>
}