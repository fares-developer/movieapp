package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.movieapp.MovieUiState
import com.example.movieapp.R
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Paddings
import com.example.movieapp.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navToDetails:()-> Unit = {},
    detailsArgs:NavHostController,
    vm: HomeViewModel = viewModel(factory = HomeViewModel.factory)
) {

    when(vm.cinemaUiState){
        is MovieUiState.Loading -> {LoadingScreen()}
        is MovieUiState.Success -> {
            MovieAppTheme {
                MyRowItems(
                    modifier = modifier,
                    movies = vm.listMovies,
                    detailsArgs = detailsArgs,
                    navToDetails = navToDetails
                )
            }
        }
        is MovieUiState.Error ->{ErrorScreen()}
    }

}

@Composable
fun MyRowItems(
    modifier: Modifier = Modifier,
    navToDetails:()-> Unit = {},
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
                    0 -> R.string.upcoming
                    1 -> R.string.toprated
                    else -> R.string.popular
                }
                MovieRows(modifier,
                    title,
                    movies = movies,
                    detailsArgs = detailsArgs,
                    navToDetails = navToDetails
                )
            }
        }

    }
}
