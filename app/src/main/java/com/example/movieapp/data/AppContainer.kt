package com.example.movieapp.data

import android.content.Context
import com.example.movieapp.data.repository.local.MovieDB
import com.example.movieapp.data.repository.local.MovieRepo
import com.example.movieapp.data.repository.local.MovieRepoImpl
import com.example.movieapp.data.repository.local.UserRepo
import com.example.movieapp.data.repository.local.UserRepoImpl
import com.example.movieapp.data.repository.remote.MovieApiService
import com.example.movieapp.data.repository.remote.MovieRepoImplements
import com.example.movieapp.data.repository.remote.MovieRepository
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {

    val remoteRepo: MovieRepository
    val localRepo: MovieRepo
    val userRepo: UserRepo
}

class AppContainerImplement(context : Context) : AppContainer {

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

    override val remoteRepo: MovieRepository by lazy {
        MovieRepoImplements(retrofitService)
    }

    override val userRepo: UserRepoImpl by lazy {
        UserRepoImpl(MovieDB.getDatabase(context).userdao())
    }

    override val localRepo: MovieRepo by lazy {
        MovieRepoImpl(MovieDB.getDatabase(context).moviedao())
    }

}