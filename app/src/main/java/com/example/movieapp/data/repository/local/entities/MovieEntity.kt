package com.example.movieapp.data.repository.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.movieapp.core.CommonUtils

@Entity(tableName = "movies")
@TypeConverters(CommonUtils.CastConverters::class,CommonUtils.MovieCoverters::class)
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
    var cast: List<CastEntity> = listOf(),
    val groupmovie: String = ""
)

data class MovieAndDetails(
    @Embedded val movie: MovieEntity = MovieEntity(),
    @Relation(
        parentColumn = "id",
        entityColumn = "idMovie"
    )
    val cast: List<CastEntity> = movie.cast
)