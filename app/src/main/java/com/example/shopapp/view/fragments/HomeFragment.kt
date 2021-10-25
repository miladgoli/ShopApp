package com.example.rezomemasoomie.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.view.adapters.HomeAdapter
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: ProductViewModel
    lateinit var adapter: HomeAdapter
    lateinit var sharedPreferences: CheckDataProducts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = HomeAdapter()
        //view model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // < init Methods
        initializeAndCheckViewModel()

        getProductsFromViewModel()

        setupRecycleView()
        // init Methods >

    }

    fun initializeAndCheckViewModel() {
        sharedPreferences = CheckDataProducts(requireContext())

        //initialize view model
        viewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(requireContext())
        ).get(ProductViewModel::class.java)

        //check first install or no
        if (!sharedPreferences.getSuccess()) {
            Toast.makeText(requireContext(), "Welcome", Toast.LENGTH_SHORT).show()
            sharedPreferences.setCheckedApp(true)
            val product = Product(0, 200000, 100000, "dsdsdsd", 0, "کفش مردانه  nike", false)
            viewModel.addProduct(product)
            viewModel.addProduct(product)
            viewModel.addProduct(product)
            viewModel.addProduct(product)
        }
    }

    fun getProductsFromViewModel() {
        viewModel.getAllProducts()

        viewModel.getProducts().observe(requireActivity(), Observer {
            adapter.setListProducts(it as ArrayList<Product>)
        })
    }

    fun setupRecycleView() {
        binding.recViewHome.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.recViewHome.adapter = adapter
    }
}