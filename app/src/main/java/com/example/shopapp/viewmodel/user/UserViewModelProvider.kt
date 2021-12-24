package com.example.shopapp.viewmodel.user

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.shopapp.model.database.cart.CartDao
import com.example.shopapp.model.database.products.ProductDao
import com.example.shopapp.model.database.products.ProductDatabase
import com.example.shopapp.model.database.user.UserDao
import com.example.shopapp.model.database.user.UserDatabase
import com.example.shopapp.model.repository.products.ProductRepository
import com.example.shopapp.model.repository.products.ProductRepositoryImp
import com.example.shopapp.model.repository.user.UserRepository
import com.example.shopapp.model.repository.user.UserRepositoryImp
import com.example.shopapp.model.utils.Utils

class UserViewModelProvider(val context: Context): ViewModelProvider.Factory {

    lateinit var dao: UserDao
    lateinit var userRepository: UserRepository
    lateinit var database: UserDatabase

    fun initializeDatabase(){
        database=
            Room.databaseBuilder(context, UserDatabase::class.java, Utils.DATABASE_NAME_USER).build()
        dao=database.getUserDao()
        userRepository= UserRepositoryImp(dao)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        initializeDatabase()
        return UserViewModel(userRepository) as T
    }
}