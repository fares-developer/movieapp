package com.example.movieapp.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.data.DataSource
import com.example.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.delay

enum class MovieScreens(@StringRes val title: Int) {
    Splash(title = R.string.app_name),
    Home(title = R.string.home),
    Auth(title = R.string.authenticate),
    Login(title = R.string.login),
    Register(title = R.string.register),
    Details(title = R.string.moviedetails)
}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {

    //TODO: Create navController
    val navController = rememberNavController()

    //TODO: Create NavHost
    NavHost(
        navController = navController,
        startDestination = MovieScreens.Splash.name,
        modifier = modifier
    ) {
        composable(route = MovieScreens.Splash.name, content = {
            SplashScreen()
            LaunchedEffect(key1 = true) {
                delay(2000)
                navController.popBackStack()
                navController.navigate(MovieScreens.Auth.name)
            }
        })

        composable(route = MovieScreens.Auth.name, content = {
            AuthScreen(modifier = modifier, navController = navController)
        })

        composable(route = MovieScreens.Login.name, content = {
            LoginScreen(
                onclickLoginButton = { navController.navigate(MovieScreens.Home.name) },
                onclickToSigUp = { navController.navigate(MovieScreens.Register.name) }
            )
        })

        composable(route = MovieScreens.Register.name, content = {
            RegisterScreen(onclickRegister = { navController.navigate(MovieScreens.Home.name) })
        })

        composable(route = MovieScreens.Home.name, content = {
            HomeScreen()
        })

        composable(route = MovieScreens.Details.name, content = {
            DetailsScreen()
        })
    }
}

@Composable
private fun AuthScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(vertical = 32.dp)
            ) {
                Image(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(192.dp)
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.camara),
                    contentDescription = stringResource(id = R.string.desc_logo_app)
                )

                Text(
                    modifier = modifier.padding(horizontal = 48.dp),
                    text = stringResource(id = R.string.welcome),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                )
            }

            Column(
                modifier = modifier
                    .padding(horizontal = 48.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Button(
                    onClick = { navController.navigate(MovieScreens.Login.name) },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.login))
                }

                ElevatedButton(
                    onClick = { navController.navigate(MovieScreens.Register.name) },
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.register))
                }
            }
        }
    )
}

@Composable
fun SocialMedia(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        content = {
            for (l in DataSource.logosAuth) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = l.image),
                        contentDescription = l.description,
                        modifier = modifier.size(32.dp)
                    )
                }
            }
        }
    )
}

@Composable
@Preview
fun AuthPreview() {
    MovieAppTheme() {
        AuthScreen()
    }
}
