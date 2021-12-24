package com.example.shopapp.model.database.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.entitys.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}