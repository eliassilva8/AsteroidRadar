package com.udacity.asteroidradar.repository

import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.service.AsteroidService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.*

/**
 * Created by Elias on 26/02/2021.
 */
class AsteroidsRepository {
    val asteroidService = AsteroidService
    fun dummyData(): List<Asteroid> = mutableListOf<Asteroid>().apply {
        for (index in 1..10) {
            add(
                Asteroid(
                    id = index.toLong(),
                    codename = "Asteroid ${index}",
                    closeApproachDate = Date().time.toString(),
                    absoluteMagnitude = 0.0,
                    estimatedDiameter = 0.0,
                    relativeVelocity = 0.0,
                    distanceFromEarth = 0.0,
                    isPotentiallyHazardous = true
                )
            )
        }
    }

    suspend fun getAsteroids(): List<Asteroid> {
        var asteroids = emptyList<Asteroid>()
        withContext(Dispatchers.IO) {
            val jsonString =
                asteroidService.neows.getAsteroids("oUVSXvjpQyeA3PZSWMWOxOWEnxduSepgCE9ikPKk")
            asteroids = parseAsteroidsJsonResult(JSONObject(jsonString))
        }
        return asteroids
    }
}