package com.example.movieapp.data.repository.local

import com.example.movieapp.data.repository.local.daos.UserDao
import com.example.movieapp.data.repository.local.entities.UserEntity

class UserRepoImpl(private val userdao: UserDao) : UserRepo {

    override suspend fun insert(user: UserEntity) {
        userdao.insert(user)
    }

    override suspend fun update(user: UserEntity) {
        userdao.update(user)
    }

    override suspend fun delete(user: UserEntity) {
        userdao.delete(user)
    }

    override suspend fun getUser(id: String): UserEntity {
        return userdao.getUser(id)
    }
}