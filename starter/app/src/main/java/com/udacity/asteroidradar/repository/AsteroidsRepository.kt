package com.udacity.asteroidradar.repository

import com.udacity.asteroidradar.Asteroid
import java.util.*

/**
 * Created by Elias on 26/02/2021.
 */
class AsteroidsRepository {
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
                            isPotentiallyHazardous = true)
            )
        }
    }


}