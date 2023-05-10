package com.example.movieapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.MovieApp
import com.example.movieapp.R
import com.example.movieapp.data.AppContainerImplement
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
    vm:DetailsViewModel = viewModel(factory = DetailsViewModel.DetailsViewModelFactory(
        AppContainerImplement().movieRepo,idMovie!!.toInt()
    ))
) {

    val movie = vm.detailMovie
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
                        AsyncImage(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(
                                    256.dp
                                )
                                .padding(Paddings.Low.dp)
                                .clip(RoundedCornerShape(Paddings.Medium.dp)),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://image.tmdb.org/t/p/original" + movie.backdrop_path)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(id = R.drawable.loading_img),
                            error = painterResource(id = R.drawable.baseline_broken_image_24),
                            contentDescription = movie!!.overview,
                            contentScale = ContentScale.FillBounds,
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
                                    ActorItem()
                                    ActorItem()
                                    ActorItem()
                                    ActorItem()
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
fun ActorItem(modifier: Modifier = Modifier) {

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
                Image(
                    modifier = modifier.size(height = 198.dp, width = 168.dp),
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = stringResource(id = R.string.photo_actor),
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    modifier = modifier.padding(
                        start = Paddings.Low.dp,
                        bottom = Paddings.VeryLow.dp
                    ),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.name_actor),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = stringResource(id = R.string.character),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Light
                    )
                }
            }
        }
    )
}

/*@Composable
@Preview(showSystemUi = true, showBackground = true)
fun DetailsPreview() {
    MovieAppTheme {
        DetailsPreview()
    }
}*/

@Composable
@Preview(showBackground = true)
fun ActorItemPreview() {
    MovieAppTheme {
        ActorItem()
    }
}
