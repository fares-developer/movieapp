package com.example.movieapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.R
import com.example.movieapp.data.AppContainerImplement
import com.example.movieapp.data.model.Cast
import com.example.movieapp.ui.DetailsViewModel
import com.example.movieapp.ui.MovieUiState
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Paddings
import com.example.movieapp.ui.theme.Shapes
import com.example.movieapp.ui.theme.readex

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    idMovie: String?,
    vm: DetailsViewModel = viewModel(
        factory = DetailsViewModel.DetailsViewModelFactory(
            AppContainerImplement().movieRepo, idMovie!!.toInt()
        )
    )
) {

    val movie by vm.detailMovie.collectAsState()
    val uiState = vm.uiState
    Log.d("MovieDetails", movie.toString())

    when (uiState) {
        is MovieUiState.Loading -> {
            LoadingScreen()
        }

        is MovieUiState.Error -> {
            ErrorScreen()
        }

        is MovieUiState.Success -> {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                LazyColumn(
                    modifier = modifier
                        .padding(bottom = Paddings.Low.dp)
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.background
                        ),
                    verticalArrangement = Arrangement.Top
                ) {
                    items(1) {
                        myAsyncImage(
                            backdrop = movie!!.backdrop_path,
                            description = movie!!.overview
                        )
                        Text(
                            modifier = modifier.padding(
                                top = Paddings.Low.dp,
                                bottom = Paddings.Medium.dp,
                                start = Paddings.Medium.dp
                            ),
                            text = movie!!.title,
                            style = MaterialTheme.typography.headlineSmall,
                            fontFamily = readex,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold
                        )

                        Column(
                            modifier = modifier.padding(horizontal = Paddings.Medium.dp),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(Paddings.Low.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.overview),
                                style = MaterialTheme.typography.titleLarge,
                                fontFamily = readex
                            )
                            Text(
                                text = movie.overview,
                                style = MaterialTheme.typography.bodyMedium,
                                fontFamily = readex,
                                textAlign = TextAlign.Justify
                            )
                        }

                        Column(
                            modifier = modifier.padding(
                                top = Paddings.VeryLow.dp,
                                start = Paddings.Medium.dp
                            ),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(Paddings.Low.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.cast),
                                style = MaterialTheme.typography.titleLarge,
                            )
                            LazyRow {
                                items(1) {
                                    movie!!.credits.cast.forEachIndexed { index, cast ->
                                        ActorItem(cast = cast)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun ActorItem(modifier: Modifier = Modifier, cast: Cast) {

    ElevatedCard(
        modifier = modifier
            .wrapContentSize()
            .padding(end = Paddings.Low.dp),
        shape = Shapes.extraLarge,
        elevation = CardDefaults.elevatedCardElevation(Paddings.VeryLow.dp),
        content = {
            Column(
                modifier = modifier.wrapContentSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(Paddings.VeryLow.dp)
            ) {
                myAsyncImage(backdrop = cast.profile_path, description = cast.name)
                Column(
                    modifier = modifier.padding(
                        start = Paddings.Low.dp,
                        bottom = Paddings.VeryLow.dp
                    ),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = cast.name,
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = cast.character,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    )
}
