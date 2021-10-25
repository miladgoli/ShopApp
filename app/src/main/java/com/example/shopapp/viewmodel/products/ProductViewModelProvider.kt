package com.example.shopapp.viewmodel.products

import ProductRepository
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.shopapp.model.database.products.ProductDao
import com.example.shopapp.model.database.products.ProductDatabase
import com.example.shopapp.model.repository.products.ProductRepositoryImp
import com.example.shopapp.model.utils.utils.DATABASE_NAME_PRODUCTS

class ProductViewModelProvider(private val context: Context) : ViewModelProvider.Factory {
    lateinit var dao: ProductDao
    lateinit var productRepository:ProductRepository
    lateinit var database:ProductDatabase


    fun initializeDatabase(){
        database=Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME_PRODUCTS).build()
        dao=database.getProductDao()
        productRepository=ProductRepositoryImp(dao)
    }



    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        initializeDatabase()
        return ProductViewModel(productRepository) as T
    }
}