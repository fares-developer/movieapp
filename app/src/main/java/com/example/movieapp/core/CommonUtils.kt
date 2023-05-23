package com.example.movieapp.core

import androidx.room.TypeConverter
import com.example.movieapp.data.model.Cast
import com.example.movieapp.data.model.MovieModel
import com.example.movieapp.data.repository.local.entities.CastEntity
import com.example.movieapp.data.repository.local.entities.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CommonUtils {

    private val gson = Gson()

    fun toMovieEntity(movie: MovieModel, group: String) = MovieEntity(
        backdrop_path = movie.backdrop_path,
        id = movie.id,
        original_language = movie.original_language,
        original_title = movie.original_title,
        overview = movie.overview,
        popularity = movie.popularity,
        poster_path = movie.poster_path,
        release_date = movie.release_date,
        title = movie.title,
        vote_average = movie.vote_average,
        vote_count = movie.vote_count,
        cast = toCastEntityList(movie.credits.cast, movie.id),
        groupmovie = group
    )

    fun toCastEntityList(list: List<Cast>, idMovie: Int): List<CastEntity> {
        return toCastEntityList(list.map { toCastEntity(it, idMovie) })
    }

    @TypeConverter
    fun toCastEntityList(list: List<CastEntity>): List<CastEntity> {
        return list
    }

    fun toCastEntity(cast: Cast, idMovie: Int) = CastEntity(
        idMovie = idMovie,
        adult = cast.adult,
        cast_id = cast.id,
        character = cast.character,
        credit_id = cast.credit_id,
        gender = cast.gender,
        idCast = cast.id,
        known_for_department = cast.known_for_department,
        name = cast.name,
        order = cast.order,
        original_name = cast.original_name,
        popularity = cast.popularity,
        profile_path = cast.profile_path
    )

    //Converters
    object MovieCoverters {

        @TypeConverter
        fun fromJson(json: String): MovieEntity {
            val type = object : TypeToken<MovieEntity>() {}.type
            return gson.fromJson(json, type)
        }

        @TypeConverter
        fun toJson(data: MovieEntity): String {
            return gson.toJson(data)
        }
    }

    object CastConverters {

        @TypeConverter
        fun fromJson(json: String): List<CastEntity> {
            val type = object : TypeToken<List<CastEntity>>() {}.type
            return gson.fromJson(json, type)
        }

        @TypeConverter
        fun toJson(data: List<CastEntity>): String {
            return gson.toJson(data)
        }
    }

}