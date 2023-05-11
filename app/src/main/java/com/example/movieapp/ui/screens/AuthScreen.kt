package com.example.movieapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.ui.AuthViewModel
import com.example.movieapp.ui.theme.Paddings

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navigate: List<() -> Unit>,
    vm: AuthViewModel = viewModel(),
    navController: NavController,
    destiny: MovieScreens
) {

    val authUiState by vm.authState.collectAsState()
    if (authUiState.navigateToHome) {
        navController.popBackStack()
        navController.navigate(destiny.name)
    }else {
        Column(
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            content = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(Paddings.Low.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.padding(vertical = Paddings.High.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                            .size(192.dp)
                            .fillMaxWidth(),
                        painter = painterResource(id = R.drawable.camara),
                        contentDescription = stringResource(id = R.string.desc_logo_app)
                    )

                    Text(
                        modifier = modifier.padding(horizontal = Paddings.VeryHigh.dp),
                        text = stringResource(id = R.string.welcome),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 3,
                    )
                }

                Column(
                    modifier = modifier
                        .padding(horizontal = Paddings.VeryHigh.dp),
                    verticalArrangement = Arrangement.spacedBy(Paddings.Low.dp),
                ) {
                    Button(
                        onClick = navigate.get(0),
                        modifier = modifier
                            .fillMaxWidth()
                            .height(Paddings.VeryHigh.dp),
                        elevation = ButtonDefaults.buttonElevation(Paddings.VeryLow.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.login),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }

                    ElevatedButton(
                        onClick = navigate.get(1),
                        modifier = modifier
                            .fillMaxWidth()
                            .height(Paddings.VeryHigh.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.register),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        )
    }
}

/*@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AuthPreview() {
    MovieAppTheme() {
        AuthScreen(navigate = listOf())
    }
}*/
