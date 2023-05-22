package com.example.movieapp.data.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movieapp.core.CommonUtils

@Entity(tableName = "movies")
@TypeConverters(CommonUtils.CastConverters::class)
data class MovieEntity(
    val backdrop_path: String = "",
    @PrimaryKey val id: Int = 1,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 1,
    var cast: List<CastEntity>,
    val groupmovie: String = ""
)


@TypeConverters(CommonUtils.CastConverters::class)
data class CastEntity(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int?,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)