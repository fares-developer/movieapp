package com.example.movieapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Credits(
    val cast: List<Cast> = listOf(),
    val crew: List<Crew> = listOf(),
    val id: Int = 1
) : Parcelable