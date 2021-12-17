package com.example.rezomemasoomie.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentProductBinding
import com.example.shopapp.databinding.FragmentProfileBinding
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.utils.Methods
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class ProductFragment: Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var product:Product
    private lateinit var cartViewModel: CartViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product= arguments?.getParcelable("product")!!

        bindItems()
    }

    fun bindItems(){

        binding.titleProductProductFragment.text=product.title

        Picasso.with(requireContext()).load(product.image).error(R.drawable.error)
            .placeholder(R.drawable.ic_nike_logo).centerInside().fit().into(binding.imageviewProductFragment)

        binding.infoProductProductFragment.text=product.info

        binding.previousCurrentFragmentProduct.text=DecimalFormat("###,###,###").format(product.previousPrice)
        binding.nowCurrentFragmentProduct.text= DecimalFormat("###,###,###").format(product.currentPrice)

        cartViewModel = ViewModelProvider(
            this,
            CartViewModelProvider(requireContext())
        ).get(CartViewModel::class.java)

        binding.btnAddToFavoriteProductFragment.setOnClickListener {

            val cart=Cart(
                0,
                product.previousPrice,
                product.currentPrice,
                product.image,
                product.status,
                product.title,
                product.info,
                product.isFavorite,
                1
            )
            cartViewModel.addCart(cart)

            Methods.onSNACK(it,"به سبد خرید شما اضاف شد")
        }

    }
}