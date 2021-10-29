package com.example.shopapp.model.repository.cart
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import io.reactivex.Completable
import io.reactivex.Single

interface CartRepository {

    fun deleteCart(cart: Cart): Completable

    fun addCart(cart: Cart): Completable

    fun updateCart(cart: Cart): Completable

    fun deleteAllCart(): Completable

    fun getCart(): Single<List<Cart>>
}