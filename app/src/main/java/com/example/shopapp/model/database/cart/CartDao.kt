package com.example.shopapp.model.database.cart

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface CartDao {
   @Insert
   fun addCart(cart: Cart): Completable

   @Update
   fun updateCart(cart: Cart): Completable

   @Query("Delete from tbl_cart")
   fun deleteAllCart(): Completable

   @Delete
   fun deleteCart(cart: Cart): Completable

   @Query("Select * from tbl_cart")
   fun getAllCart(): Single<List<Cart>>
}