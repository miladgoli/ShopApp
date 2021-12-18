package com.example.rezomemasoomie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentCartBinding
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.model.utils.Dialogs
import com.example.shopapp.view.adapters.CartAdapter
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider

class CartFragment : Fragment(), CartAdapter.CartAdapterListener {

    lateinit var binding: FragmentCartBinding
    lateinit var viewModel: CartViewModel
    lateinit var adapter: CartAdapter
    lateinit var sharedPreferences: CheckDataProducts
    lateinit var cartsList: ArrayList<Cart>

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
            cartsList = ArrayList()
            cartsList = it as ArrayList<Cart>
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
            if (cartsList.size > 0) {

                Dialogs.showDeleteAllCartsDialog(requireContext(), View.OnClickListener {

                    viewModel.deleteAllProduct()
                    adapter.deleteAllProductCart()

                })
            }
        }
    }

    override fun onItemClickItemListener(cart: Cart) {

        val bundle = Bundle()
        val navHostFragment = findNavController()
        bundle.putParcelable("cart", cart)
        navHostFragment.navigate(R.id.action_cart_to_product_cart, bundle)

    }

    override fun onAddCartClickItemListener(cart: Cart) {

        val newCart = Cart(
            cart.id,
            cart.previousPrice,
            cart.currentPrice,
            cart.image,
            cart.status,
            cart.title,
            cart.info,
            cart.isFavorite,
            ++cart.count
        )

        viewModel.updateCart(newCart)
        adapter.updateCart(newCart)

    }

    override fun onLowCartClickItemListener(cart: Cart) {

        if (cart.count == 1) {

            Dialogs.showDeleteCartDialog(requireContext(), View.OnClickListener {
                viewModel.deleteCart(cart)
                adapter.deleteProductCart(cart)
                cartsList.remove(cart)
            })
        } else {

            val newCart = Cart(
                cart.id,
                cart.previousPrice,
                cart.currentPrice,
                cart.image,
                cart.status,
                cart.title,
                cart.info,
                cart.isFavorite,
                --cart.count
            )

            viewModel.updateCart(newCart)
            adapter.updateCart(newCart)

        }

    }

    override fun onLongClickItemListener(cart: Cart) {

        Dialogs.showDeleteCartDialog(requireContext(), View.OnClickListener {
            viewModel.deleteCart(cart)
            adapter.deleteProductCart(cart)
        })


    }
}