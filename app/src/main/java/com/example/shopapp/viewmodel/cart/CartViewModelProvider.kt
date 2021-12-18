package com.example.shopapp.viewmodel.cart

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.shopapp.model.database.cart.CartDao
import com.example.shopapp.model.database.cart.CartDatabase
import com.example.shopapp.model.repository.cart.CartRepository
import com.example.shopapp.model.repository.cart.CartRepositoryImp
import com.example.shopapp.model.utils.Utils.DATABASE_NAME_CART

class CartViewModelProvider(private val context: Context) : ViewModelProvider.Factory {
    lateinit var dao: CartDao
    lateinit var cartRepository: CartRepository
    lateinit var database:CartDatabase


    fun initializeDatabase(){
        database=Room.databaseBuilder(context, CartDatabase::class.java, DATABASE_NAME_CART).build()
        dao=database.getCartDao()
        cartRepository=CartRepositoryImp(dao)
    }



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        initializeDatabase()
        return CartViewModel(cartRepository) as T
    }
}