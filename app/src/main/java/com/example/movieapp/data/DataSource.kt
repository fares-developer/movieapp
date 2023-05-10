package com.example.movieapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Animation
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R

object DataSource{

    val logosAuth = listOf(
        LogoInfo(R.drawable.google,"Google Logo"),
        LogoInfo(R.drawable.meta,"Apple Logo"),
        LogoInfo(R.drawable.twitter,"Meta Logo"),
    )

    val films = listOf(
        Film(R.drawable.camara,"Black Panther: Wakanda Forever", R.string.wakanda_desc),
        Film(R.drawable.camara,"Black Panther", R.string.wakanda_desc),
        Film(R.drawable.camara,"Avengers", R.string.wakanda_desc),
        Film(R.drawable.apple,"Avengers", R.string.wakanda_desc),
    )
    
    val iconsTopBar = listOf(
        IconTopBar(Icons.Rounded.Movie,R.string.cinema, R.string.desc_default),
        IconTopBar(Icons.Rounded.Tv,R.string.tv, R.string.desc_default),
        IconTopBar(Icons.Rounded.Animation,R.string.anime, R.string.desc_default),
    )
}
data class Film(@DrawableRes val image: Int, val title:String, @StringRes val description: Int)
data class LogoInfo(@DrawableRes val image:Int,val description:String)
data class IconTopBar(val imageVector: ImageVector,@StringRes val title: Int,@StringRes val description: Int )