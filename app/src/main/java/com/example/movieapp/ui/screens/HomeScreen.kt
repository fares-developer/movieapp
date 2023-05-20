package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.ui.HomeViewModel
import com.example.movieapp.ui.MovieUiState
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Paddings

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    detailsArgs:NavHostController,
    vm: HomeViewModel = viewModel(factory = HomeViewModel.factory)
) {

    val uiState = vm.cinemaUiState

    when(uiState){
        is MovieUiState.Loading -> {LoadingScreen()}
        is MovieUiState.Success -> {
            MovieAppTheme {
                MyRowItems(
                    modifier = modifier,
                    movies = vm.listMovies,
                    detailsArgs = detailsArgs,
                )
            }
        }
        is MovieUiState.Error ->{ErrorScreen()}
    }

}

@Composable
fun MyRowItems(
    modifier: Modifier = Modifier,
    detailsArgs:NavHostController,
    movies:List<List<MovieModel>> = listOf()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(
            Paddings.High.dp
        ),
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(Paddings.VeryLow.dp)
    ) {
        item {
            movies.forEachIndexed { index, movies ->
                val title = when(index){
                    0 -> R.string.now_playing
                    1 -> R.string.upcoming
                    2 -> R.string.toprated
                    else -> R.string.popular
                }
                MovieRows(modifier,
                    title,
                    movies = movies,
                    detailsArgs = detailsArgs,
                )
            }
        }

    }
}
