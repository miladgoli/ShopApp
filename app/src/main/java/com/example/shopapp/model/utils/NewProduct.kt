package com.example.shopapp.model.utils

import android.content.Context
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.view.adapters.HomeAdapter
import com.example.shopapp.viewmodel.products.ProductViewModel

class NewProduct(val context: Context) {

    lateinit var viewModel: ProductViewModel
    lateinit var adapter: HomeAdapter


    fun addNewProduct(list: ArrayList<Product>){

        adapter= HomeAdapter()

    }
}