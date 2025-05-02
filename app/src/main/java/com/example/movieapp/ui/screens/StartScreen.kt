package com.example.movieapp.ui.screens

import android.os.Bundle
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.ui.StartViewModel
import com.example.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

enum class MovieScreens {
    Splash,
    Home,
    Auth,
    Login,
    Register,
    Details,
    TV,
    Anime
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
) {

    //TODO: Create navController
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val backS = backStackEntry?.destination?.route

    val currentScreen = when (backS) {
        "${MovieScreens.Details.name}/{idMovie}" -> {
            MovieScreens.Details
        }

        else -> MovieScreens.valueOf(
            backStackEntry?.destination?.route ?: MovieScreens.Home.name
        )
    }
    /*val currentScreen = MovieScreens.valueOf(
        backStackEntry?.destination?.route?: MovieScreens.Home.name
    )*/

    val viewModel: StartViewModel = viewModel()
    val startUIState by viewModel.startState.collectAsState()

    fun navigateToScreen(screen: MovieScreens) {
        navController.popBackStack()
        navController.navigate(screen.name)
        viewModel.changeSelectedNavItem(screen.ordinal)
    }

    val destinationsNavBar = listOf(
        { navigateToScreen(MovieScreens.Home) },
        { navigateToScreen(MovieScreens.TV) },
        { navigateToScreen(MovieScreens.Anime) }
    )

    //TODO: Create NavHost
    MovieAppTheme {
        Scaffold(
            topBar = {
                AnimatedVisibility(visible = startUIState.topBar) {
                    MyTopAppBar(
                        currentScreen = currentScreen,
                        navigateUp = { navController.navigateUp() },
                        canNavigateBack = navController.previousBackStackEntry != null
                    )
                }
            },
            bottomBar = {
                AnimatedVisibility(visible = startUIState.navBar) {
                    MyBottomBar(destinations = destinationsNavBar, vm = viewModel)
                }
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = MovieScreens.Splash.name,
                    modifier = modifier.padding(it)
                ) {

                    //Check if currentScreen is Home, Tv or Anime for show topbar and navBar
                    val showBars = when (currentScreen) {
                        MovieScreens.Home,
                        MovieScreens.Anime,
                        MovieScreens.TV -> listOf(true, true)

                        MovieScreens.Details -> listOf(true, false)
                        else -> listOf(false, false)
                    }

                    viewModel.showBars(showBars[0], showBars[1])
                    startUIState.selectedNavItem = when (currentScreen) {
                        MovieScreens.TV -> 1
                        MovieScreens.Anime -> 2
                        else -> 0
                    }

                    composable(route = MovieScreens.Splash.name, content = {
                        SplashScreen()
                        LaunchedEffect(key1 = true) {
                            delay(1000)
                            navController.popBackStack()
                            navController.navigate(MovieScreens.Auth.name)
                        }
                    })

                    composable(route = MovieScreens.Auth.name, content = {
                        AuthScreen(
                            modifier = modifier,
                            navController = navController,
                            destiny = MovieScreens.Home,
                            navigate = listOf(
                                {
                                    navController.popBackStack()
                                    navController.navigate(MovieScreens.Login.name)
                                },
                                {
                                    navController.popBackStack()
                                    navController.navigate(MovieScreens.Register.name)
                                },
                            )
                        )
                    })

                    composable(route = MovieScreens.Login.name, content = {
                        LoginScreen(
                            navController = navController,
                            destiny = MovieScreens.Home,
                            onclickToSigUp = { navController.navigate(MovieScreens.Register.name) }
                        )
                    })

                    composable(route = MovieScreens.Register.name, content = {
                        RegisterScreen(
                            navController = navController,
                            destiny = MovieScreens.Home
                        )
                    })

                    composable(
                        route = MovieScreens.Home.name,
                        content = {
                            HomeScreen(detailsArgs = navController)
                        })

                    composable(
                        route = "${MovieScreens.Details.name}/{idMovie}",
                        content = { backStackEntry ->
                            val idMovie = backStackEntry.arguments?.getString("idMovie")
                            DetailsScreen(idMovie = idMovie)
                        }
                    )

                    composable(route = MovieScreens.TV.name, content = {
                        TvScreen()
                    })

                    composable(route = MovieScreens.Anime.name, content = {
                        AnimeScreen()
                    })
                }
            }
        )
    }
}


//TODO(): implemnting custom navArg type

val MovieNavType = object : NavType<MovieModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): MovieModel? {
        return bundle.getParcelable(key) as MovieModel?
    }

    override fun parseValue(value: String): MovieModel {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: MovieModel) {
        bundle.putParcelable(key, value)
    }

}