package com.example.movieapp.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repository.local.MovieRepo
import com.example.movieapp.data.repository.local.entities.CastEntity
import com.example.movieapp.data.repository.local.entities.MovieAndDetails
import com.example.movieapp.data.repository.local.entities.MovieEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException

class DetailsViewModel(
    private var movieRepo: MovieRepo,
    idMovie: Int,
    group: String
) : ViewModel() {

    private var _uiState: MovieUiState by mutableStateOf(MovieUiState.Loading)

    val uiState get() = _uiState
    var detailMovie: MutableStateFlow<MovieAndDetails> = MutableStateFlow(MovieAndDetails())

    init {
        getDetails(idMovie = idMovie, group = group)
    }

    private fun getDetails(idMovie: Int, group: String) {
        _uiState = MovieUiState.Loading
        viewModelScope.launch {
            _uiState = try {
                detailMovie = MutableStateFlow(
                    movieRepo.getDetailsMovie(id = idMovie, groupmovie = group).first()
                )
                MovieUiState.Success
            } catch (e: IOException) {
                MovieUiState.Error
            }
        }
    }

    class DetailsViewModelFactory(
        private val repo: MovieRepo,
        private val idMovie: Int,
        private val group: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                return DetailsViewModel(repo, idMovie, group) as T
            }
            throw IllegalArgumentException("Clase de ViewModel desconocida")
        }
    }

}