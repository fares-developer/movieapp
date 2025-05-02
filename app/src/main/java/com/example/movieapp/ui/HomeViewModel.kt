package com.example.movieapp.ui

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
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.remote.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    private var movieRepo: MovieRepository
) : ViewModel() {

    private var _cinemaUiState: MovieUiState by mutableStateOf(MovieUiState.Loading)
    init {getMovies()}

    val cinemaUiState get() = _cinemaUiState
    var listMovies = mutableListOf<List<MovieModel>>()

    private fun getMovies() {
        _cinemaUiState = MovieUiState.Loading
        viewModelScope.launch {
            _cinemaUiState = try {
                val nowPlaying = movieRepo.getNowPlayingMovies()
                val upcoming = movieRepo.getUpcomingMovies()
                val toprated = movieRepo.getTopRatedMovies()
                val popular = movieRepo.getPopularMovies()
                listMovies = mutableListOf(
                    nowPlaying.results,
                    upcoming.results,
                    toprated.results,
                    popular.results
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
                val movieRepo = app.container.movieRepo
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