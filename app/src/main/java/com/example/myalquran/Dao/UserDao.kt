package com.example.myalquran.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE email like :email AND password like password")
    fun loginUser(email: String, password: String):Boolean

    @Insert(onConflict = REPLACE)
    fun insertUser(user: User):Long
}