package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = AsteroidsRepository(database)

    private val _clickedAsteroid = MutableLiveData<Asteroid?>()
    val clickedAsteroid: MutableLiveData<Asteroid?>
        get() = _clickedAsteroid

    private val _pictureOfDay = MutableLiveData<PictureOfDay?>()
    val pictureOfDay: MutableLiveData<PictureOfDay?>
        get() = _pictureOfDay

    val asteroids = repository.asteroids

    init {
        refreshAsteroids()
        getPictureOfDay()
    }

    fun displayAsteroidDetails(asteroid: Asteroid?) {
        _clickedAsteroid.value = asteroid
    }

    fun getPictureOfDay() {
        viewModelScope.launch {
            _pictureOfDay.value = repository.getPictureOfDay()
        }
    }

    fun refreshAsteroids() {
        viewModelScope.launch {
            repository.refreshAsteroids()
        }
    }
}