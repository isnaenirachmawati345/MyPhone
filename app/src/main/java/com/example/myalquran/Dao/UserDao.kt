package com.example.myalquran.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE username like :username AND password like :password")
    fun loginUser(username: String, password: String):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Long
}