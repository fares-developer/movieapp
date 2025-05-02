package com.example.movieapp

import android.app.Application
import com.example.movieapp.data.AppContainerImplement

class MovieApp() : Application() {

    lateinit var container:AppContainerImplement

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImplement()
    }
}