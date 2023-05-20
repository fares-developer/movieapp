package com.example.movieapp.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.movieapp.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user:UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Update
    suspend fun update(user:UserEntity)

    @Query("select * from users where id = :id")
    suspend fun getUser(id:Int): Flow<UserEntity>
}