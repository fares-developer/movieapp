package com.example.movieapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Animation
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieapp.R

object DataSource {

    val iconsBottomBar = listOf(
        IconTopBar(Icons.Rounded.Movie, R.string.cinema, R.string.desc_default),
        IconTopBar(Icons.Rounded.Tv, R.string.tv, R.string.desc_default),
        IconTopBar(Icons.Rounded.Animation, R.string.anime, R.string.desc_default),
    )
}

data class Film(@DrawableRes val image: Int, val title: String, @StringRes val description: Int)
data class LogoInfo(@DrawableRes val image: Int, val description: String, val action: () -> Unit = {})
data class IconTopBar(
    val imageVector: ImageVector,
    @StringRes val title: Int,
    @StringRes val description: Int
)