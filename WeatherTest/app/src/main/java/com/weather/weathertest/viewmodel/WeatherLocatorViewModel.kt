package com.weather.weathertest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.weather.weathertest.R
import com.weather.weathertest.model.WeatherInfo
import com.weather.weathertest.repository.DataRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// ViewModel for the Weather Locator screen
class WeatherLocatorViewModel : ViewModel() {

    var latitude: MutableLiveData<String> = MutableLiveData("")
    var longitude: MutableLiveData<String> = MutableLiveData("")
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()
    var hasWeatherData: MutableLiveData<Boolean> = MutableLiveData()

    fun getIsLoading() : LiveData<Boolean>{
        return isLoading
    }

    fun getHasData() : LiveData<Boolean>{
        return hasWeatherData
    }

    fun setLatitude(newLatitude: String){
        latitude.postValue(newLatitude)
    }

    fun setLongitude(newLongitude: String){
        longitude.postValue(newLongitude)
    }

    fun getWeatherInfoData() : LiveData<WeatherInfo>{
        // Use a transformation to find the icon for the current weather
        return Transformations.map(DataRepository.getWeatherInformation(), ::getIconForWeather)
    }

    // Gets the Weather for the location data set in the ViewModel.
    fun getWeatherForLocation(){
        isLoading.value = true;
        hasWeatherData.value = false
        // Use a coroutine to get the Weather Information from the Repository.
        GlobalScope.launch {
            try {
                DataRepository.getWeatherInfoForLocation(latitude.value!!, longitude.value!!)
            }catch (exception : Exception){
            }
        }
    }

    // Tries to match the current weather to an icon in the icons font.
    private fun getIconForWeather(weatherInfo: WeatherInfo) : WeatherInfo{
        weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_unknown

        when {
            weatherInfo.currentWeatherInfo.icon.contains("clear", true) -> weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_clear
            weatherInfo.currentWeatherInfo.icon.contains("cloudy", true) -> weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_cloudy
            weatherInfo.currentWeatherInfo.icon.contains("flurries", true) -> weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_flurries
            weatherInfo.currentWeatherInfo.icon.contains("hazy", true) -> weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_hazy
            weatherInfo.currentWeatherInfo.icon.contains("rain", true) -> weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_rain
            weatherInfo.currentWeatherInfo.icon.contains("sleat", true) -> weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_sleat
            weatherInfo.currentWeatherInfo.icon.contains("snow", true) -> weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_snow
            weatherInfo.currentWeatherInfo.icon.contains("sunny", true) -> weatherInfo.currentWeatherInfo.iconId = R.string.wi_wu_sunny
        }

        return weatherInfo
    }
}
