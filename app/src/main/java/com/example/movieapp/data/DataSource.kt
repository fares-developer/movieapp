package com.example.movieapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.movieapp.R

object DataSource{

    val logosAuth = listOf(
        LogoInfo(R.drawable.google,"Google Logo"),
        LogoInfo(R.drawable.apple,"Apple Logo"),
        LogoInfo(R.drawable.facebook,"Meta Logo"),
        LogoInfo(R.drawable.round_phone_24,"Phone Logo")
    )

    val films = listOf(
        Film(R.drawable.pelicula,"Black Panther: Wakanda Forever", R.string.wakanda_desc),
        Film(R.drawable.camara,"Black Panther", R.string.wakanda_desc),
        Film(R.drawable.ver_la_pelicula,"Avengers", R.string.wakanda_desc),
        Film(R.drawable.apple,"Avengers", R.string.wakanda_desc),
    )
}

data class Film(@DrawableRes val image: Int, val title:String, @StringRes val description: Int)
data class LogoInfo(@DrawableRes val image:Int,val description:String)