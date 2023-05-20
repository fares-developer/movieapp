package com.example.movieapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val password: String,
    val email: String,
    val authenticated: Boolean
)
