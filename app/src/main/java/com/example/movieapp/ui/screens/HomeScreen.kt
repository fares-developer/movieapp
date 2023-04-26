package com.example.movieapp.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.R
import com.example.movieapp.data.DataSource.films
import com.example.movieapp.data.Film
import com.example.movieapp.ui.StartViewModel
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Paddings
import com.example.movieapp.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: () -> Unit = {},
    viewModel: StartViewModel = viewModel()
) {

    MovieAppTheme {
        Scaffold {
            it
            MyRowItems(
                navigateToDetails = navigateToDetails,
                modifier = modifier
            )
        }
    }
}

@Composable
fun MyRowItems(
    modifier: Modifier = Modifier,
    navigateToDetails: () -> Unit = {},
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
            MovieRows(modifier, R.string.upcoming, navigateToDetails = navigateToDetails)
            MovieRows(modifier, R.string.toprated, navigateToDetails = navigateToDetails)
            MovieRows(modifier, R.string.popular, navigateToDetails = navigateToDetails)
        }

    }
}

@Composable
private fun MovieRows(
    modifier: Modifier = Modifier,
    @StringRes headTitle: Int,
    navigateToDetails: () -> Unit = {}
) {
    Column(
        modifier = modifier.padding(top = Paddings.Medium.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(Paddings.VeryLow.dp)
    ) {
        Text(
            text = stringResource(id = headTitle),
            modifier = Modifier.padding(Paddings.VeryLow.dp),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(Paddings.VeryLow.dp),
            content = {
                items(films) {
                    MyItem(film = it, navigateToDetails = navigateToDetails)
                }
            }
        )
    }
}

@Composable
fun MyItem(
    modifier: Modifier = Modifier,
    film: Film,
    navigateToDetails: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier
            .wrapContentSize()
            .clickable(onClick = navigateToDetails),
        shape = Shapes.large,
        elevation = CardDefaults.elevatedCardElevation(Paddings.VeryLow.dp),
        content = {
            Image(
                modifier = modifier
                    .size(height = 224.dp, width = 160.dp),
                painter = painterResource(id = film.image),
                contentDescription = stringResource(id = film.description),
                contentScale = ContentScale.FillBounds
            )
        }
    )
}


/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    MovieAppTheme {
        HomeScreen()
    }
}*/
