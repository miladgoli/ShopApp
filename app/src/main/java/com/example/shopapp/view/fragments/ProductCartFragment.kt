package com.example.rezomemasoomie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentProductCartBinding
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.utils.Utils
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class ProductCartFragment: Fragment() {

    private lateinit var binding: FragmentProductCartBinding
    private lateinit var cart:Cart
    private lateinit var cartViewModel: CartViewModel
    private lateinit var mainViewModel: ProductViewModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cart= arguments?.getParcelable("cart")!!
        Utils.isFavoriteProduct=cart.isFavorite
        bindItems()
    }

    fun bindItems(){

        binding.titleProductProductFragment.text=cart.title

        Picasso.with(requireContext()).load(cart.image).error(R.drawable.error)
            .placeholder(R.drawable.ic_nike_logo).centerInside().fit().into(binding.imageviewProductFragment)

        binding.infoProductProductFragment.text=cart.info

        if(Utils.isFavoriteProduct){
            binding.imageviewFavoriteProductCartFragment.setImageResource(R.drawable.ic_star_favorite_fill)
        }else{
            binding.imageviewFavoriteProductCartFragment.setImageResource(R.drawable.ic_star_favorite)
        }

        binding.previousCurrentFragmentProductCart.text= DecimalFormat("###,###,###").format(cart.previousPrice)
        binding.nowCurrentFragmentProductCart.text= DecimalFormat("###,###,###").format(cart.currentPrice)

        cartViewModel = ViewModelProvider(
            this,
            CartViewModelProvider(requireContext())
        ).get(CartViewModel::class.java)

        mainViewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(requireContext())
        ).get(ProductViewModel::class.java)

        binding.btnFavoriteProductCartFragment.setOnClickListener {

            if (Utils.isFavoriteProduct){
                binding.imageviewFavoriteProductCartFragment.setImageResource(R.drawable.ic_star_favorite)
                Utils.isFavoriteProduct=false
                val newProduct= Product(
                    this.cart.id,
                    this.cart.previousPrice,
                    this.cart.currentPrice,
                    this.cart.image,
                    this.cart.status,
                    this.cart.title,
                    this.cart.info,
                    Utils.isFavoriteProduct
                )
                val newCart=Cart(
                    this.cart.id,
                    this.cart.previousPrice,
                    this.cart.currentPrice,
                    this.cart.image,
                    this.cart.status,
                    this.cart.title,
                    this.cart.info,
                    Utils.isFavoriteProduct,
                    //bug
                    this.cart.count
                )
                mainViewModel.updateProduct(newProduct)
                cartViewModel.updateCart(newCart)

            }else{
                binding.imageviewFavoriteProductCartFragment.setImageResource(R.drawable.ic_star_favorite_fill)
                Utils.isFavoriteProduct=true
                val newProduct=Product(
                    this.cart.id,
                    this.cart.previousPrice,
                    this.cart.currentPrice,
                    this.cart.image,
                    this.cart.status,
                    this.cart.title,
                    this.cart.info,
                    Utils.isFavoriteProduct
                )
                val newCart=Cart(
                    this.cart.id,
                    this.cart.previousPrice,
                    this.cart.currentPrice,
                    this.cart.image,
                    this.cart.status,
                    this.cart.title,
                    this.cart.info,
                    Utils.isFavoriteProduct,
                    //bug
                    this.cart.count
                )
                mainViewModel.updateProduct(newProduct)
                cartViewModel.updateCart(newCart)
            }
        }
    }
}