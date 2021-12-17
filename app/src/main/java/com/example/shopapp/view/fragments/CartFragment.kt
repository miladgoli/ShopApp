package com.example.rezomemasoomie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentCartBinding
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.view.adapters.CartAdapter
import com.example.shopapp.view.adapters.HomeAdapter
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider

class CartFragment : Fragment(), CartAdapter.CartAdapterListener {

    lateinit var binding: FragmentCartBinding
    lateinit var viewModel: CartViewModel
    lateinit var adapter: CartAdapter
    lateinit var sharedPreferences: CheckDataProducts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        adapter = CartAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeAndCheckViewModel()

        getProductsFromViewModel()

        setupRecycleView()

        buttonListener()

    }

    fun initializeAndCheckViewModel() {
        //initialize view model
        viewModel = ViewModelProvider(
            this,
            CartViewModelProvider(requireContext())
        ).get(CartViewModel::class.java)
    }

    fun getProductsFromViewModel() {
        viewModel.getAllCarts()
        viewModel.getCarts().observe(requireActivity(), Observer {
            adapter.setListProducts(it as ArrayList<Cart>)
        })
    }

    fun setupRecycleView() {
        binding.cartRecyclerViewCart.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.cartRecyclerViewCart.adapter = adapter
    }

    fun buttonListener() {
        binding.btnClearCart.setOnClickListener {
            viewModel.deleteAllProduct()
            adapter.deleteAllProductCart()
        }
    }

    override fun onClickItemListener(cart: Cart) {
        val bundle = Bundle()
        val navHostFragment = findNavController()
        bundle.putParcelable("cart", cart)
        navHostFragment.navigate(R.id.action_cart_to_product_cart, bundle)
    }

    override fun onLongClickItemListener(cart: Cart) {
        viewModel.deleteCart(cart)
        adapter.deleteProductCart(cart)
    }
}