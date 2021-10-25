package com.example.shopapp.model.database.cart

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product

@Database(
    entities = [Cart::class],
    version = 1,
    exportSchema = false
)
abstract class CartDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDao
}