package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieapp.ui.screens.StartScreen
import com.example.movieapp.ui.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartScreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AuthViewModel(MovieApp().container.userRepo).auth.signOut()
    }
}