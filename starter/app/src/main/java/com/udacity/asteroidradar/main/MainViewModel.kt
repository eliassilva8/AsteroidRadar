package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.repository.AsteroidsRepository

class MainViewModel : ViewModel() {
    private val repository = AsteroidsRepository()

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    private val _clickedAsteroid = MutableLiveData<Asteroid?>()
    val clickedAsteroid: MutableLiveData<Asteroid?>
        get() = _clickedAsteroid

    init {
        _asteroids.value = repository.dummyData()
    }

    fun displayAsteroidDetails(asteroid: Asteroid?) {
        _clickedAsteroid.value = asteroid
    }
}