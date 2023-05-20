package com.example.movieapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.data.model.Dates
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "movies")
data class MovieEntity(
    val adult: Boolean = true,
    val backdropPath: String = "",
    val genreIds: List<Int> = listOf(),
    @PrimaryKey val id: Int = 1,
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 1,
    @ColumnInfo(name = "credits") var credits: String,
    val movietypes: String
)

data class ResultsEntity(
    val dates: Dates = Dates("", ""),
    val page: Int = 1,
    val results: List<MovieEntity>,
    val total_pages: Int = 1,
    val total_results: Int = 1
)