package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = AsteroidsRepository()

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    private val _clickedAsteroid = MutableLiveData<Asteroid?>()
    val clickedAsteroid: MutableLiveData<Asteroid?>
        get() = _clickedAsteroid

    private val _pictureOfDay = MutableLiveData<PictureOfDay?>()
    val pictureOfDay: MutableLiveData<PictureOfDay?>
        get() = _pictureOfDay

    init {
        getAsteroids()
        getPictureOfDay()
    }

    fun displayAsteroidDetails(asteroid: Asteroid?) {
        _clickedAsteroid.value = asteroid
    }

    fun getAsteroids() {
        viewModelScope.launch {
            _asteroids.value = repository.getAsteroids()
        }
    }

    fun getPictureOfDay() {
        viewModelScope.launch {
            _pictureOfDay.value = repository.getPictureOfDay()
        }
    }
}