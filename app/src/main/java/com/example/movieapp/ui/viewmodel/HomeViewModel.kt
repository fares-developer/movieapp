package com.example.movieapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movieapp.MovieApp
import com.example.movieapp.data.repository.local.entities.MovieEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException
import com.example.movieapp.data.repository.local.MovieRepo as local

class HomeViewModel(
    private var movieRepo: local 
) : ViewModel() {

    private var _cinemaUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
    init {getMovies()}

    val cinemaUiState get() = _cinemaUiState
    var listMovies = mutableListOf<List<MovieEntity>>()

    private fun getMovies() {
        _cinemaUiState = MovieUiState.Loading
        viewModelScope.launch {
            _cinemaUiState = try {
                listMovies = mutableListOf(
                    movieRepo.getNowPlayingMovies("now_playing").first(),
                    movieRepo.getUpcomingMovies("upcoming").first(),
                    movieRepo.getTopRatedMovies("toprated").first(),
                    movieRepo.getPopularMovies("popular").first()
                )
                MovieUiState.Success
            } catch (e: IOException) {
                MovieUiState.Error
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as MovieApp)
                val movieRepo = app.container.localRepo
                HomeViewModel(movieRepo = movieRepo)
            }
        }
    }
}



sealed interface MovieUiState {
    object Success : MovieUiState
    object Loading : MovieUiState
    object Error : MovieUiState
}