package com.example.shopapp.model.repository.cart

import com.example.shopapp.model.database.cart.CartDao
import com.example.shopapp.model.database.products.ProductDao
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import io.reactivex.Completable
import io.reactivex.Single

class CartRepositoryImp(val dao:CartDao) : CartRepository {

    override fun deleteCart(cart: Cart): Completable {
        return dao.deleteCart(cart)
    }

    override fun addCart(cart: Cart): Completable {
        return dao.addCart(cart)
    }

    override fun updateCart(cart: Cart): Completable {
        return dao.updateCart(cart)
    }

    override fun deleteAllCart(): Completable {
        return dao.deleteAllCart()
    }

    override fun getCart(): Single<List<Cart>> {
        return dao.getAllCart()
    }

}