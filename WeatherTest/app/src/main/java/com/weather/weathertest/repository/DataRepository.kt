package com.weather.weathertest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weather.weathertest.model.WeatherInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Use the object to represent only one instance of the Repository (like a Singleton pattern)
object DataRepository {
    private var weatherLocatorService: WeatherLocatorService = createWeatherLocatorService()
    private var weatherInformation: MutableLiveData<WeatherInfo> = MutableLiveData()

    fun getWeatherInformation() : LiveData<WeatherInfo>{
        return weatherInformation
    }

    private fun createWeatherLocatorService() : WeatherLocatorService{
        // Creates a Retrofit instance with the base value of the webservice with the WeatherLocatorService.
        // Also, uses GSON for JSON Serialization/Deserialization
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.darksky.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WeatherLocatorService::class.java)
    }

    suspend fun getWeatherInfoForLocation(latitude: String, longitude: String) : LiveData<WeatherInfo> {
        // Creates a coroutine to call the Retrofit service.
        GlobalScope.launch() {
            try {
                // Call the Retrofit service
                val response = weatherLocatorService?.getCurrentWeatherForLocation(latitude, longitude)
                when{
                    response!!.isSuccessful -> {
                        // If the call is successful, update the LiveData holding the Weather information
                        weatherInformation.postValue(response.body())
                    }
                }
            }catch (exception : Exception){
                // Catch and handle an exception
            }
        }

        // Return a LiveData<WeatherInfo> that gets updated when the Retrofit service call returns.
        // Also, returning this LiveData<WeatherInfo> allows the View to observe changes in this Data Source.
        return weatherInformation
    }
}