package com.example.movieapp.data.repository.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movieapp.core.CommonUtils

@Entity("casting")
@TypeConverters(CommonUtils.CastConverters::class)
data class CastEntity(
    val idMovie: Int = 0,
    val adult: Boolean = false,
    val cast_id: Int = 1,
    val character: String = "",
    val credit_id: String = "",
    val gender: Int? = 1,
    @PrimaryKey @ColumnInfo("id") val idCast: Int = 1,
    val known_for_department: String = "",
    val name: String = "",
    val order: Int = 1,
    val original_name: String = "",
    val popularity: Double = 0.0,
    val profile_path: String? = ""
)