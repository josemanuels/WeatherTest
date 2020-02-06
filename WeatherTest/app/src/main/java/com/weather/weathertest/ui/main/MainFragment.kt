package com.weather.weathertest.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.weather.weathertest.databinding.MainFragmentBinding
import com.weather.weathertest.model.WeatherInfo
import com.weather.weathertest.viewmodel.WeatherLocatorViewModel
import java.util.*
import com.google.android.gms.common.api.Status
import com.weather.weathertest.R
import kotlinx.android.synthetic.main.main_fragment.view.*


class MainFragment : Fragment(){

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: WeatherLocatorViewModel
    private var recyclerViewWeatherInfo: RecyclerView? = null
    private var textViewWeatherIcon: TextView? = null
    private var autocompleteFragment: AutocompleteSupportFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout and obtain components refefences
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerViewWeatherInfo = view?.recyclerViewWeatherInfo
        textViewWeatherIcon = view?.textViewWeatherIcon
        val autocompleteFragment = childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?

        autocompleteFragment?.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
        autocompleteFragment!!.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) { // TODO: Get info about the selected place.
                println("Place: " + place.name + ", " + place.id + ", "+ place.latLng?.latitude + ", " + place.latLng?.longitude)
                viewModel.setLatitude(place.latLng?.latitude.toString())
                viewModel.setLongitude(place.latLng?.longitude.toString())
            }

            override fun onError(status: Status) { // TODO: Handle the error.
                println("An error occurred: $status")
            }
        })

        // Get a reference of the View Model associated with this fragment.
        viewModel = ViewModelProviders.of(this).get(WeatherLocatorViewModel::class.java)

        // Set up Databing between the view and the View Model
        var binding = DataBindingUtil.bind<MainFragmentBinding>(view)
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel;

        return binding!!.main
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Observe the changes for the LiveData<WeatherInfo> object to make changes in the fragment UI.
        viewModel.getWeatherInfoData().observe(this, Observer<WeatherInfo>{ weatherInfo ->
            viewModel.isLoading.value = false
            if (weatherInfo != null){
                viewModel.hasWeatherData.value = true

                // For simplicity, the details of the current weather are shown in a RecyclerView with a Custom Adapter
                // This RecyclerView arranges the rows in 2 columns.
                var weatherInfoAdapter: WeatherInfoAdapter = WeatherInfoAdapter(weatherInfo)
                recyclerViewWeatherInfo?.layoutManager = GridLayoutManager(this.context, 2)
                recyclerViewWeatherInfo?.adapter = weatherInfoAdapter

                // Sets the weather icon using a text value from the string.xml resources file
                textViewWeatherIcon?.text = getString(weatherInfo.currentWeatherInfo.iconId)
            }else{
                viewModel.hasWeatherData.value = false
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
