package com.example.shopapp.model.database.products

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shopapp.model.entitys.Product
import io.reactivex.Completable


interface ProductDao {
   @Insert
   fun addProduct(product: Product): Completable

   @Update
   fun updateProduct(product: Product): Completable

   @Query("Delete from tbl_product")
   fun deleteAllProducts(): Completable

   @Delete
   fun deleteProduct(product: Product): Completable

   @Query("Select * from tbl_product")
   fun getAllProducts(): LiveData<List<Product>>
}