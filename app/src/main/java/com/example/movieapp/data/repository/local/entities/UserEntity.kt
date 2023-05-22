package com.example.movieapp.data.repository.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val email: String,
    val password: String
)
