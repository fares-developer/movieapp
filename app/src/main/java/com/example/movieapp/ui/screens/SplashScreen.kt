package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.Cabin
import com.example.movieapp.ui.theme.MovieAppTheme

@Composable
fun SplashScreen() {
    MovieAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(12.dp)
                    .size(192.dp),
                painter = painterResource(id = R.drawable.camara),
                contentDescription = stringResource(id = R.string.desc_logo_app)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = Cabin
            )
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    MovieAppTheme(darkTheme = false) {
        SplashScreen()
    }
}