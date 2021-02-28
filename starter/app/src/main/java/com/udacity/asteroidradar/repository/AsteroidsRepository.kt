package com.udacity.asteroidradar.repository

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.service.AsteroidService
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
                    AsteroidService.neows.getAsteroids(BuildConfig.NASA_API_KEY)
            asteroids = parseAsteroidsJsonResult(JSONObject(jsonString))
        }
        return asteroids
    }
}