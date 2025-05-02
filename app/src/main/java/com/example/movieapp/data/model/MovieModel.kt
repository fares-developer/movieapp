package com.example.movieapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MovieModel(
    val adult: Boolean = true,
    val backdrop_path: String = "",
    val genre_ids: List<Int> = listOf(),
    val id: Int = 1,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Int = 1,
    var credits: Credits = Credits()
) : Parcelable
