package com.udacity.asteroidradar.repository

import android.util.Log
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.service.AsteroidService
import com.udacity.asteroidradar.service.PictureOfDayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * Created by Elias on 26/02/2021.
 */
class AsteroidsRepository {
    suspend fun getAsteroids(): List<Asteroid> {
        var asteroids = emptyList<Asteroid>()
        withContext(Dispatchers.IO) {
            val jsonString =
                AsteroidService.neo.getAsteroids(BuildConfig.NASA_API_KEY)
            asteroids = parseAsteroidsJsonResult(JSONObject(jsonString))
        }
        return asteroids
    }

    suspend fun getPictureOfDay(): PictureOfDay? {
        var pictureOfDay: PictureOfDay? = null
        withContext(Dispatchers.IO) {
            pictureOfDay = PictureOfDayService.planetary.getPictureOfDay(BuildConfig.NASA_API_KEY)
        }
        Log.d("AsteroidsRepository", pictureOfDay.toString())
        return pictureOfDay
    }
}