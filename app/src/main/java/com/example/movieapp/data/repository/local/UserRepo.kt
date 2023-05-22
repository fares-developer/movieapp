package com.example.movieapp.data.repository.local

import com.example.movieapp.data.repository.local.daos.UserDao
import com.example.movieapp.data.repository.local.entities.UserEntity

interface UserRepo {

    suspend fun insert(user: UserEntity)

    suspend fun update(user: UserEntity)

    suspend fun delete(user: UserEntity)

    suspend fun getUser(id: String): UserEntity
}