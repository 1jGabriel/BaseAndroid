package com.example.baseproject.ui.main

import com.example.baseproject.api.model.MovieResponse
import com.example.baseproject.repository.Repository
import com.example.baseproject.util.SchedulerProvider
import io.reactivex.Single

class MainActivityViewModel(private val repository: Repository, private val schedulerProvider: SchedulerProvider) {

    fun showDataFromApi(): Single<MovieResponse> = repository.getDataFromApi()
        .compose(schedulerProvider.getSchedulersForSingle())

    fun showDataFromSearch(search: String) = repository.getMoviesFromSearch(search)
        .compose(schedulerProvider.getSchedulersForSingle())

    fun showDataFromSearch2(search: String) = repository.getMoviesFromSearch2(search)
        .compose(schedulerProvider.getSchedulersForObservable())
}