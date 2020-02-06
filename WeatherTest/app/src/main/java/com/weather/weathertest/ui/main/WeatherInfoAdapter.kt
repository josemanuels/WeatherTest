package com.weather.weathertest.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weather.weathertest.R
import com.weather.weathertest.model.WeatherInfo
import java.util.ArrayList

class WeatherInfoAdapter(weatherInfo: WeatherInfo) : RecyclerView.Adapter<WeatherInfoAdapter.ViewHolder>() {

    // Holds the Title of the row (in this case, property name)
    private val itemsTitle: ArrayList<String> = ArrayList()
    // Holds the Title of the row (in this case, property value)
    private val itemsValue: ArrayList<String> = ArrayList()

    init {
        // Populates the data with the WeatherInfo values
        itemsTitle.add("Nearest Storm Distance")
        itemsValue.add(weatherInfo.currentWeatherInfo.nearestStormDistance.toString())

        itemsTitle.add("Precip. Intensity")
        itemsValue.add(weatherInfo.currentWeatherInfo.precipIntensity.toString())

        itemsTitle.add("Precip. Probability")
        itemsValue.add(weatherInfo.currentWeatherInfo.precipProbability.toString())

        itemsTitle.add("Temperature")
        itemsValue.add(weatherInfo.currentWeatherInfo.temperature.toString())

        itemsTitle.add("Apparent Temperature")
        itemsValue.add(weatherInfo.currentWeatherInfo.apparentTemperature.toString())

        itemsTitle.add("Dew Point")
        itemsValue.add(weatherInfo.currentWeatherInfo.dewPoint.toString())

        itemsTitle.add("Humidity")
        itemsValue.add(weatherInfo.currentWeatherInfo.humidity.toString())

        itemsTitle.add("Pressure")
        itemsValue.add(weatherInfo.currentWeatherInfo.pressure.toString())

        itemsTitle.add("Wind Speed")
        itemsValue.add(weatherInfo.currentWeatherInfo.windSpeed.toString())

        itemsTitle.add("Wind Gust")
        itemsValue.add(weatherInfo.currentWeatherInfo.windGust.toString())

        itemsTitle.add("Wind Bearing")
        itemsValue.add(weatherInfo.currentWeatherInfo.windBearing.toString())

        itemsTitle.add("Cloud Cover")
        itemsValue.add(weatherInfo.currentWeatherInfo.cloudCover.toString())

        itemsTitle.add("UV Index")
        itemsValue.add(weatherInfo.currentWeatherInfo.uvIndex.toString())

        itemsTitle.add("Visibility")
        itemsValue.add(weatherInfo.currentWeatherInfo.visibility.toString())

        itemsTitle.add("Ozone")
        itemsValue.add(weatherInfo.currentWeatherInfo.ozone.toString())

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Obtain the components from the layout list_item layout
        var title: TextView = view.findViewById(R.id.title)
        var subtitle: TextView = view.findViewById(R.id.subtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflates the ViewHolder with the list_item layout
        return ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Sets the values for the cell
        holder.title.text =  itemsTitle[position];
        holder.subtitle.text =  itemsValue[position];
    }

    override fun getItemCount(): Int {
        // Return the number of elements in the RecyclerView
        return itemsTitle.size
    }
}