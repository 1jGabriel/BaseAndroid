package com.example.baseproject.repository

import com.example.baseproject.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getDataFromApi() = apiService.getMoviesByGenre(page = 1, genre = 878)
    fun getMoviesFromSearch(searchString : String) = apiService.searchMovies(searchString, 1)
    fun getMoviesFromSearch2(searchString : String) = apiService.searchMovies2(searchString, 1)

}