package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Paddings

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    MovieAppTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.surface
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier.size(192.dp),
                painter = painterResource(id = R.drawable.camara),
                contentDescription = stringResource(id = R.string.desc_logo_app)
            )
            Text(
                modifier = modifier.padding(top = Paddings.Low.dp),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    MovieAppTheme() {
        SplashScreen()
    }
}