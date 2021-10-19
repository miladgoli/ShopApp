package com.example.shopapp.viewmodel.products

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.shopapp.model.database.products.ProductDao
import com.example.shopapp.model.database.products.ProductDatabase
import com.example.shopapp.model.repository.products.ProductRepository
import com.example.shopapp.model.repository.products.ProductRepositoryImp
import com.example.shopapp.model.utils.utils.DATABASE_NAME_PRODUCTS

class ProductViewModelProvider(context: Context) : ViewModelProvider.Factory {
    var dao: ProductDao?=null
    lateinit var productRepository: ProductRepository

    init {

        dao=Room.databaseBuilder(context, ProductDatabase::class.java, DATABASE_NAME_PRODUCTS).build().getProductDao()
        productRepository = ProductRepositoryImp(dao!!)
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductViewModel(productRepository) as T
    }
}