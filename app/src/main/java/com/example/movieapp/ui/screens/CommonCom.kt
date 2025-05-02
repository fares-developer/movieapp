package com.example.movieapp.ui.screens

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.WifiTetheringError
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.data.AppContainerImplement
import com.example.movieapp.data.DataSource
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.ui.StartViewModel
import com.example.movieapp.ui.theme.Paddings
import com.example.movieapp.ui.theme.Shapes

@Composable
fun MovieRows(
    modifier: Modifier = Modifier,
    @StringRes headTitle: Int,
    detailsArgs: NavHostController,
    movies: List<MovieModel>
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
                items(movies) {
                    MyItem(
                        film = it,
                        detailsArgs = detailsArgs,
                    )
                }
            }
        )
    }
}

@Composable
fun MyItem(
    modifier: Modifier = Modifier,
    film: MovieModel,
    navToDetails: () -> Unit = {},
    detailsArgs: NavHostController,
) {
    ElevatedCard(
        modifier = modifier
            .wrapContentSize()
            .clickable(onClick = {
                detailsArgs.navigate(route = "${MovieScreens.Details.name}/${film.id}")
            }),
        shape = Shapes.large,
        elevation = CardDefaults.elevatedCardElevation(Paddings.VeryLow.dp),
        content = {
            AsyncImage(
                modifier = modifier
                    .size(height = 224.dp, width = 160.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original" + film.poster_path)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.loading_img),
                error = painterResource(id = R.drawable.baseline_broken_image_24),
                contentDescription = film.overview,
                contentScale = ContentScale.Crop,
            )
        }
    )
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
                label = { Text(text = stringResource(iconTopBar.title)) }
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var progress by rememberSaveable { mutableStateOf(0.1f) }
        val animatedProgress by animateFloatAsState(
            targetValue = progress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            progress = animatedProgress
        )
    }
}

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Rounded.WifiTetheringError,
            contentDescription = "Error"
        )
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

@Composable
fun myAsyncImage(
    modifier: Modifier = Modifier,
    backdrop: String?,
    description: String
) {
    if (backdrop != null) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(
                    256.dp
                )
                .padding(Paddings.Low.dp)
                .clip(RoundedCornerShape(Paddings.Medium.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(AppContainerImplement.IMG_BASE_URL + backdrop)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.baseline_broken_image_24),
            contentDescription = description,
            contentScale = ContentScale.Crop,
        )
    } else {
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(
                    256.dp
                )
                .padding(Paddings.Low.dp)
                .clip(RoundedCornerShape(Paddings.Medium.dp)),
            imageVector = Icons.Rounded.Error,
            contentDescription = description,
            contentScale = ContentScale.Crop
        )
    }
}