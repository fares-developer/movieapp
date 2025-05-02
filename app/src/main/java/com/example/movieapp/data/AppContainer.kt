package com.example.movieapp.data

import com.example.movieapp.data.remote.MovieApiService
import com.example.movieapp.data.remote.MovieRepoImplements
import com.example.movieapp.data.remote.MovieRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {

    val movieRepo: MovieRepository

}

class AppContainerImplement : AppContainer {

    companion object {
        private val BASE_URL = "https://api.themoviedb.org/3/movie/"
        val IMG_BASE_URL = "https://image.tmdb.org/t/p/original";
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }

    override val movieRepo: MovieRepository by lazy {
        MovieRepoImplements(retrofitService)
    }

}