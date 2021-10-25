package com.example.shopapp.model.repository.products

import com.example.shopapp.model.database.products.ProductDao
import com.example.shopapp.model.entitys.Product
import io.reactivex.Completable
import io.reactivex.Single

class ProductRepositoryImp(dao:ProductDao) : ProductRepository {

    var dao: ProductDao = dao

    override fun deleteProduct(product: Product): Completable {
        return dao.deleteProduct(product)
    }

    override fun addProduct(product: Product): Completable {
        return dao.addProduct(product)
    }

    override fun updateProduct(product: Product): Completable{
        return dao.updateProduct(product)
    }

    override fun deleteAllProducts(): Completable {
        return dao.deleteAllProducts()
    }

    override fun getProducts(): Single<List<Product>> {
        return dao.getAllProducts()
    }


}