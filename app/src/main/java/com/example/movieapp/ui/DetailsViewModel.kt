package com.example.movieapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movieapp.MovieApp
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.remote.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(
    private var movieRepo: MovieRepository,
    private var idMovie: Int
) : ViewModel() {

    private var _uiState: MovieUiState by mutableStateOf(MovieUiState.Loading)

    init {
        getDetails(idMovie = idMovie)
    }

    val uiState get() = _uiState
    var detailMovie = MovieModel()

    fun getDetails(idMovie:Int) {
        _uiState = MovieUiState.Loading
        viewModelScope.launch {
            _uiState = try {
                detailMovie = movieRepo.getDetailsMovie(idMovie = idMovie)
                MovieUiState.Success
            } catch (e: IOException) {
                MovieUiState.Error
            }
        }
    }

    init {
        getDetails(idMovie = idMovie)
    }
    /*companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApp)
                val movieRepo = app.container.movieRepo
                DetailsViewModel(movieRepo = movieRepo)
            }
        }

    }*/
    class DetailsViewModelFactory(private val repo: MovieRepository,private val idMovie:Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                return DetailsViewModel(repo, idMovie) as T
            }
            throw IllegalArgumentException("Clase de ViewModel desconocida")
        }
    }

}