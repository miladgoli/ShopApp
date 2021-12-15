package com.example.rezomemasoomie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.view.adapters.HomeAdapter
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider
import com.google.android.material.snackbar.Snackbar


class HomeFragment() : Fragment(), HomeAdapter.CallBackHomeAdapter {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var adapter: HomeAdapter
    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = HomeAdapter(this)
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

        //initialize view model
        viewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(requireContext())
        ).get(ProductViewModel::class.java)

        cartViewModel = ViewModelProvider(
            this,
            CartViewModelProvider(requireContext())
        ).get(CartViewModel::class.java)

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

    fun onSNACK(view: View) {
        //Snackbar(view)
        val snackbar = Snackbar.make(
            view, "Added",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackbar.setActionTextColor(resources.getColor(R.color.m_white))
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(resources.getColor(R.color.m_black))
        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(resources.getColor(R.color.m_white))
        textView.textSize = 18f
        snackbar.show()
    }


    override fun onLongCartClickListenerHomeFragment(product: Product) {
        /*
     cartViewModel.addCart(
         Cart(
             0,
             product.previousPrice,
             product.currentPrice,
             product.image,
             product.status,
             product.title,
             product.isFavorite
         )
     )

     onSNACK(requireView())

      */
    }

    override fun onCartClickListenerHomeFragment(product: Product) {
       findNavController().navigate(R.id.action_home_to_product)
    }


}


