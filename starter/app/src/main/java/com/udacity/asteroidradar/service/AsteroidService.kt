package com.udacity.asteroidradar.service


import com.udacity.asteroidradar.Constants
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Elias on 26/02/2021.
 */
interface IAsteroidService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(@Query("api_key") apiKey: String): String


}


object AsteroidService {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

    val neows: IAsteroidService by lazy { retrofit.create(IAsteroidService::class.java) }
}