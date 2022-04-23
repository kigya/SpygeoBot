package data.remote.repository

import data.remote.api.ReversedGeocodingApi
import data.remote.api.WeatherApi
import data.remote.models.CurrentWeather
import data.remote.models.ReversedCountry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository(

    private val weatherApi: WeatherApi,
    private val reversedGeocodingApi: ReversedGeocodingApi
) {

    suspend fun getCurrentWeather(apiKey: String, queryCountry: String, isAqiNeeded: String): CurrentWeather {
        return withContext(Dispatchers.IO) {
            weatherApi.getCurrentWeatherAsync(apiKey, queryCountry, isAqiNeeded)
        }.await()
    }

    suspend fun getCountryNameByCoordinates(latitude: String, longitude: String, format: String): ReversedCountry {
        return withContext(Dispatchers.IO) {
            reversedGeocodingApi.getCountryNameByCoordinates(latitude, longitude, format)
        }.await()
    }

}