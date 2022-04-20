package com.example.myalquran.Dao.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myalquran.Dao.User
import com.example.myalquran.Dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class UserDb (): RoomDatabase(){
    abstract fun userDao() : UserDao

    companion object{
        private var INSTANCE: UserDb? = null

        fun getInstance(context: Context): UserDb?{
             if (INSTANCE == null){
                   synchronized(UserDb::class){
                       INSTANCE = Room.databaseBuilder(
                           context.applicationContext,
                           UserDb::class.java,"database.store"
                       ).build()
                   }
             }
            return INSTANCE


    }
    }
}