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
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentProductBinding
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.utils.Methods
import com.example.shopapp.model.utils.Utils
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class ProductFragment: Fragment() {

    private lateinit var binding: FragmentProductBinding
    private lateinit var product:Product
    private lateinit var cartViewModel: CartViewModel
    private lateinit var mainViewModel: ProductViewModel
    private lateinit var cartsList:ArrayList<Cart>
    private var index:Int=-1


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
        Utils.isFavoriteProduct=product.isFavorite

        bindItems()
        cartsList= ArrayList()
        cartViewModel.getAllCarts()
        cartViewModel.getCarts().observe(requireActivity(), Observer {
            cartsList= it as ArrayList<Cart>
        })


    }

    fun bindItems(){

        binding.titleProductProductFragment.text=product.title

        Picasso.with(requireContext()).load(product.image).error(R.drawable.error)
            .placeholder(R.drawable.ic_nike_logo).centerInside().fit().into(binding.imageviewProductFragment)

        binding.infoProductProductFragment.text=product.info

        if(Utils.isFavoriteProduct){
            binding.imageviewFavoriteProductFragment.setImageResource(R.drawable.ic_star_favorite_fill)
        }else{
            binding.imageviewFavoriteProductFragment.setImageResource(R.drawable.ic_star_favorite)
        }

        binding.previousCurrentFragmentProduct.text=DecimalFormat("###,###,###").format(product.previousPrice)
        binding.nowCurrentFragmentProduct.text= DecimalFormat("###,###,###").format(product.currentPrice)

        cartViewModel = ViewModelProvider(
            this,
            CartViewModelProvider(requireContext())
        ).get(CartViewModel::class.java)

        mainViewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(requireContext())
        ).get(ProductViewModel::class.java)

        binding.btnAddToCartProductFragment.setOnClickListener {

            product.isFavorite=Utils.isFavoriteProduct
            index= cartsList.indexOfFirst { it.id==product.id }

            if (index >= 0){
                var myCart=cartsList[index]

                val cart=Cart(
                    product.id,
                    product.previousPrice,
                    product.currentPrice,
                    product.image,
                    product.status,
                    product.title,
                    product.info,
                    product.isFavorite,
                    ++myCart.count
                )
                cartViewModel.updateCart(cart)

            }else{
                val cart=Cart(
                    product.id,
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
                cartsList.add(cart)
            }


            Methods.onSnackAddedCart(it,"به سبد خرید شما اضاف شد", View.OnClickListener {
                val navHostFragment = findNavController()
                navHostFragment.navigate(R.id.action_product_to_cart)
            })
        }

        binding.btnFavoriteProductFragment.setOnClickListener {

            if (Utils.isFavoriteProduct){
                binding.imageviewFavoriteProductFragment.setImageResource(R.drawable.ic_star_favorite)

                Utils.isFavoriteProduct=false

                val newProduct= Product(
                    this.product.id,
                    this.product.previousPrice,
                    this.product.currentPrice,
                    this.product.image,
                    this.product.status,
                    this.product.title,
                    this.product.info,
                    Utils.isFavoriteProduct
                )
                val newCart=Cart(
                    this.product.id,
                    this.product.previousPrice,
                    this.product.currentPrice,
                    this.product.image,
                    this.product.status,
                    this.product.title,
                    this.product.info,
                    Utils.isFavoriteProduct,
                    //bug
                    1
                )
                mainViewModel.updateProduct(newProduct)
                cartViewModel.updateCart(newCart)

            }else{
                binding.imageviewFavoriteProductFragment.setImageResource(R.drawable.ic_star_favorite_fill)
                Utils.isFavoriteProduct=true
                val newProduct=Product(
                    this.product.id,
                    this.product.previousPrice,
                    this.product.currentPrice,
                    this.product.image,
                    this.product.status,
                    this.product.title,
                    this.product.info,
                    Utils.isFavoriteProduct
                )
                val newCart=Cart(
                    this.product.id,
                    this.product.previousPrice,
                    this.product.currentPrice,
                    this.product.image,
                    this.product.status,
                    this.product.title,
                    this.product.info,
                    Utils.isFavoriteProduct,
                    //bug
                    1
                )
                mainViewModel.updateProduct(newProduct)
                cartViewModel.updateCart(newCart)
            }
        }

    }
}