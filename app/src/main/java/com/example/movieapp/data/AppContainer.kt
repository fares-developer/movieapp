package com.example.movieapp.data

import android.content.Context
import com.example.movieapp.data.local.MovieDB
import com.example.movieapp.data.local.MovieRepoImp
import com.example.movieapp.data.remote.MovieApiService
import com.example.movieapp.data.remote.MovieRepoImplements
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.movieapp.data.local.repository.MovieRepository as local
import com.example.movieapp.data.remote.MovieRepository as remote

interface AppContainer {

    val remoteRepo: remote
    val localRepo:local

}

class AppContainerImplement(private val context: Context) : AppContainer {

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

    override val remoteRepo: remote by lazy {
        MovieRepoImplements(retrofitService)
    }

    override val localRepo: local by lazy {
        MovieRepoImp(MovieDB.getDatabase(context).movieDao())
    }

}