package com.example.rezomemasoomie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.view.adapters.HomeAdapter
import com.example.shopapp.viewmodel.products.ProductViewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: ProductViewModel
    lateinit var  adapter:HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        adapter= HomeAdapter()
        //view model

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //initialize view model
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)


/*
        val product=Product(0,200000,100000,"dsdsdsd",0,"کفش",false)
        viewModel.addProduct(product)
        viewModel.addProduct(product)
        viewModel.addProduct(product)
        viewModel.addProduct(product)
        viewModel.addProduct(product)
        viewModel.addProduct(product)
        viewModel.addProduct(product)
        viewModel.addProduct(product)
        viewModel.getProducts().observe(requireActivity(), Observer {
            adapter.setListProducts(it as ArrayList<Product>)
        })

        binding.recViewHome.layoutManager=LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.recViewHome.adapter=adapter
        */

    }

}