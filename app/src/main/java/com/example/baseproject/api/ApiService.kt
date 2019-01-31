package com.example.baseproject.api

import com.example.baseproject.api.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getMoviesByGenre(@Query("with_genres")genre : Int,
                         @Query("page") page : Int): Single<MovieResponse>

    @GET("search/movie")
    fun searchMovies(@Query("query")query : String,
                     @Query("page") page : Int) : Single<MovieResponse>

    @GET("search/movie")
    fun searchMovies2(@Query("query")query : String,
                     @Query("page") page : Int) : Observable<MovieResponse>
}