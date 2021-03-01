package com.udacity.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.asDatabaseModel
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.service.AsteroidService
import com.udacity.asteroidradar.service.PictureOfDayService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * Created by Elias on 26/02/2021.
 */
class AsteroidsRepository(private val database: AsteroidsDatabase) {
    suspend fun refreshAsteroids() {
        try {
            withContext(Dispatchers.IO) {
                val jsonString =
                        AsteroidService.neo.getAsteroids(BuildConfig.NASA_API_KEY)
                val asteroids = parseAsteroidsJsonResult(JSONObject(jsonString))
                database.asteroidDao.insertAll(*asteroids.asDatabaseModel())
            }
        } catch (e: Exception) {
            Log.e("AsteroidRepository", e.message!!)
        }
    }

    suspend fun getPictureOfDay(): PictureOfDay? {
        try {
            var pictureOfDay: PictureOfDay?
            withContext(Dispatchers.IO) {
                pictureOfDay = PictureOfDayService.planetary.getPictureOfDay(BuildConfig.NASA_API_KEY)
            }
            return pictureOfDay
        } catch (e: Exception) {
            Log.e("AsteroidRepository", e.message!!)
        }
        return null
    }

    val asteroids = Transformations.map(database.asteroidDao.getAsteroids()) {
        it.asDomainModel()
    }
}