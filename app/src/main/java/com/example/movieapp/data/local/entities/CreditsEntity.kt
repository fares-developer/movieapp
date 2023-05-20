package com.example.movieapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credits")
data class CreditsEntity(
    @PrimaryKey val id: Int = 1,
    val cast: List<CastEntity> = listOf(),
    val crew: List<CrewEntity> = listOf(),
)

@Entity("crews")
data class CrewEntity(
    val adult: Boolean,
    val credit_id: String,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
)