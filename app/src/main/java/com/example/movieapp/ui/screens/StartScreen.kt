package com.example.movieapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.core.MyBottomBar
import com.example.movieapp.core.MyTopAppBar
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.viewmodel.StartViewModel
import kotlinx.coroutines.delay

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

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
) {

    //TODO: Create navController
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = when (backStackEntry?.destination?.route) {
        "${MovieScreens.Details.name}/{idMovie}/{group}" -> {
            MovieScreens.Details
        }

        else -> MovieScreens.valueOf(
            backStackEntry?.destination?.route ?: MovieScreens.Home.name
        )
    }

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
                        route = "${MovieScreens.Details.name}/{idMovie}/{group}",
                        content = { backStackEntry ->
                            val idMovie = backStackEntry.arguments?.getString("idMovie")
                            val group = backStackEntry.arguments?.getString("group")
                            DetailsScreen(idMovie = idMovie, group = group)
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