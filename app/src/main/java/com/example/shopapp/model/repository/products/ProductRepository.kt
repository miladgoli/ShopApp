package com.example.shopapp.model.repository.products
import com.example.shopapp.model.entitys.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {

    fun deleteProduct(product: Product): Completable

    fun addProduct(product: Product): Completable

    fun updateProduct(product: Product): Completable

    fun deleteAllProducts(): Completable

    fun getProducts(): Single<List<Product>>
}