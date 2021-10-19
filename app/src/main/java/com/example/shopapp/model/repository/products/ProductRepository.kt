package com.example.shopapp.model.repository.products

import androidx.lifecycle.LiveData
import com.example.shopapp.model.entitys.Product
import io.reactivex.Completable

interface ProductRepository {

    fun deleteProduct(product: Product): Completable

    fun addProduct(product: Product): Completable

    fun updateProduct(product: Product): Completable

    fun deleteAllProducts(): Completable

    fun getProducts(): LiveData<List<Product>>
}