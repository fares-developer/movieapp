package com.example.movieapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MovieModel(
    @SerialName("adult")val adult: Boolean = true,
    @SerialName("backdrop_path")val backdropPath: String = "",
    @SerialName("genre_ids")val genreIds: List<Int> = listOf(),
    @SerialName("id")val id: Int = 1,
    @SerialName("original_language")val originalLanguage: String = "",
    @SerialName("original_title")val originalTitle: String = "",
    @SerialName("overview")val overview: String = "",
    @SerialName("popularity")val popularity: Double = 0.0,
    @SerialName("poster_path")val posterPath: String = "",
    @SerialName("release_date")val releaseDate: String = "",
    @SerialName("title")val title: String = "",
    @SerialName("video")val video: Boolean = false,
    @SerialName("vote_average")val voteAverage: Double = 0.0,
    @SerialName("vote_count")val voteCount: Int = 1,
    @SerialName("credits")var credits: Credits = Credits()
) : Parcelable
