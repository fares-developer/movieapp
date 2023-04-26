package com.example.movieapp.ui.screens

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.data.DataSource
import com.example.movieapp.ui.StartViewModel
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Paddings
import kotlinx.coroutines.delay

enum class MovieScreens(@StringRes val title: Int) {
    Splash(title = R.string.app_name),
    Home(title = R.string.home),
    Auth(title = R.string.authenticate),
    Login(title = R.string.login),
    Register(title = R.string.register),
    Details(title = R.string.moviedetails),
    TV(title = R.string.tv),
    Anime(title = R.string.anime)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(modifier: Modifier = Modifier) {

    //TODO: Create navController
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MovieScreens.valueOf(
        backStackEntry?.destination?.route ?: MovieScreens.Home.name
    )

    val viewModel: StartViewModel = viewModel()
    val startUIState by viewModel.startState.collectAsState()

    val destinationsTopBar = listOf(
        {
            navController.navigate(MovieScreens.Home.name)
            viewModel.changeSelectedNavItem(0)
        },
        {
            navController.navigate(MovieScreens.TV.name)
            viewModel.changeSelectedNavItem(1)
        },
        {
            navController.navigate(MovieScreens.Anime.name)
            viewModel.changeSelectedNavItem(2)
        }
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
                    MyBottomBar(destinations = destinationsTopBar, vm = viewModel)
                }
            },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = MovieScreens.Splash.name,
                    modifier = modifier.padding(it)
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
                            onclickLoginButton = {
                                navController.navigate(MovieScreens.Home.name)
                                startUIState.topBar = true
                                startUIState.navBar = true
                            },
                            onclickToSigUp = { navController.navigate(MovieScreens.Register.name) }
                        )
                    })

                    composable(route = MovieScreens.Register.name, content = {
                        RegisterScreen(onclickRegister = {
                            navController.navigate(MovieScreens.Home.name)
                            startUIState.topBar = true
                            startUIState.navBar = true
                        })
                    })

                    composable(route = MovieScreens.Home.name, content = {
                        HomeScreen(
                            navigateToDetails = { navController.navigate(MovieScreens.Details.name) },
                        )
                    })

                    composable(route = MovieScreens.Details.name, content = {
                        DetailsScreen()
                    })

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    currentScreen: MovieScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    icon: ImageVector = Icons.Rounded.ArrowBack
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = currentScreen.name)
        },
        navigationIcon = {
            if (canNavigateBack && currentScreen == MovieScreens.Details) {
                IconButton(onClick = navigateUp) {
                    Image(imageVector = icon, contentDescription = null)
                    //TODO: Implement icon app
                }
            }
        },
    )
}

@Composable
fun MyBottomBar(
    modifier: Modifier = Modifier,
    destinations: List<() -> Unit>,
    vm: StartViewModel = viewModel()
) {
    NavigationBar(modifier = modifier) {
        DataSource.iconsTopBar.forEachIndexed { i, iconTopBar ->
            NavigationBarItem(
                //TODO: IMPLEMENT BEHAVIOR SELECTED ICON
                selected = vm.startState.value.selectedNavItem == i,
                onClick = destinations.get(i),
                icon = {
                    Icon(
                        imageVector = iconTopBar.imageVector,
                        contentDescription = stringResource(id = iconTopBar.description)
                    )
                },
                label = {Text(text = stringResource(iconTopBar.title))}
            )
        }
    }
}

@Composable
fun SocialMedia(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(Paddings.Low.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        content = {
            for (l in DataSource.logosAuth) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = l.image),
                        contentDescription = l.description,
                        modifier = modifier.size(Paddings.High.dp)
                    )
                }
            }
        }
    )
}