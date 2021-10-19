package com.example.shopapp.model.database.products

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.model.entitys.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
}