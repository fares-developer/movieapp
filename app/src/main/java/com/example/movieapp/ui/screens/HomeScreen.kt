package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.data.DataSource.films
import com.example.movieapp.data.Film
import com.example.movieapp.ui.theme.MovieAppTheme

@Composable
fun HomeScreen() {
    MyRowItems()
}

@Composable
fun MyRowItems(modifier: Modifier = Modifier) {
    LazyColumn {
        item {
            Text(text = "Upcoming",modifier = Modifier.padding(8.dp))
            LazyRow(
                modifier = modifier,
                content = {
                    items(films) {
                        MyItem(film = it)
                    }
                })
            Text(text = "Popular",modifier = Modifier.padding(8.dp))
            LazyRow(
                modifier = modifier,
                content = {
                    items(films) {
                        MyItem(film = it)
                    }
                })
            Text(text = "Popular",modifier = Modifier.padding(8.dp))
            LazyRow(
                modifier = modifier,
                content = {
                    items(films) {
                        MyItem(film = it)
                    }
                })
        }

    }
}

@Composable
fun MyItem(modifier: Modifier = Modifier, film: Film) {
    ElevatedCard(
        modifier = modifier
            .wrapContentSize()
            .padding(8.dp),
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    modifier = modifier
                        .size(224.dp)
                        .padding(16.dp),
                    painter = painterResource(id = film.image),
                    contentDescription = stringResource(id = film.description)
                )
                Text(
                    modifier = modifier.padding(16.dp),
                    text = film.title
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    //showSystemUi = true
)
@Composable
fun MyItemPreview() {
    MovieAppTheme {
        MyItem(film = films[0])
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    MovieAppTheme {
       HomeScreen()
    }
}