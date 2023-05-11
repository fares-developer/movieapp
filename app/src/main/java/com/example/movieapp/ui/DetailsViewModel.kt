package com.example.movieapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.remote.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(
    private var movieRepo: MovieRepository,
    idMovie: Int
) : ViewModel() {

    private var _uiState: MovieUiState by mutableStateOf(MovieUiState.Loading)

    val uiState get() = _uiState
    var detailMovie:MutableStateFlow<MovieModel> = MutableStateFlow(MovieModel())

    init { getDetails(idMovie = idMovie) }

    private fun getDetails(idMovie:Int) {
        _uiState = MovieUiState.Loading
        viewModelScope.launch {
            _uiState = try {
                detailMovie = MutableStateFlow(movieRepo.getDetailsMovie(idMovie = idMovie))
                MovieUiState.Success
            } catch (e: IOException) {
                MovieUiState.Error
            }
        }
    }

    class DetailsViewModelFactory(private val repo: MovieRepository,private val idMovie:Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                return DetailsViewModel(repo, idMovie) as T
            }
            throw IllegalArgumentException("Clase de ViewModel desconocida")
        }
    }

}