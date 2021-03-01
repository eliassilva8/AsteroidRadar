package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.Asteroid

@Entity
data class DatabaseAsteroid constructor(
        @PrimaryKey
        val id: String,
        val codename: String,
        val closeApproachDate: String,
        val absoluteMagnitude: String,
        val estimatedDiameter: String,
        val relativeVelocity: String,
        val distanceFromEarth: String,
        val isPotentiallyHazardous: Boolean
)

fun List<DatabaseAsteroid>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
                id = it.id.toLong(),
                codename = it.codename,
                closeApproachDate = it.closeApproachDate,
                absoluteMagnitude = it.absoluteMagnitude.toDouble(),
                estimatedDiameter = it.estimatedDiameter.toDouble(),
                relativeVelocity = it.relativeVelocity.toDouble(),
                distanceFromEarth = it.distanceFromEarth.toDouble(),
                isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

fun List<Asteroid>.asDatabaseModel(): Array<DatabaseAsteroid> {
    return map {
        DatabaseAsteroid(
                id = it.id.toString(),
                codename = it.codename,
                closeApproachDate = it.closeApproachDate,
                absoluteMagnitude = it.absoluteMagnitude.toString(),
                estimatedDiameter = it.estimatedDiameter.toString(),
                relativeVelocity = it.relativeVelocity.toString(),
                distanceFromEarth = it.distanceFromEarth.toString(),
                isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}
