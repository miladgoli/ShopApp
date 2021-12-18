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
import com.example.shopapp.databinding.FragmentFavoriteBinding
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.model.utils.Dialogs
import com.example.shopapp.model.utils.Utils
import com.example.shopapp.view.adapters.CartAdapter
import com.example.shopapp.view.adapters.FavoriteAdapter
import com.example.shopapp.view.adapters.HomeAdapter
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider

class FavoriteFragment : Fragment(), FavoriteAdapter.ListenerFavoriteAdapter {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var viewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private val favoritList: ArrayList<Product> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        adapter = FavoriteAdapter(this)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()

        initializeAndCheckViewModel()

        getProductsFromViewModel()

        binding.btnClearFavorite.setOnClickListener {
            if (adapter.getListFavorite().size > 0) {

                Dialogs.showDeleteAllFavoriteDialog(requireContext(), View.OnClickListener {

                    favoritList.forEach {
                        if (it.isFavorite) {

                            val newProduct = Product(
                                it.id,
                                it.previousPrice,
                                it.currentPrice,
                                it.image,
                                it.status,
                                it.title,
                                it.info,
                                false
                            )
                            val newCart = Cart(
                                it.id,
                                it.previousPrice,
                                it.currentPrice,
                                it.image,
                                it.status,
                                it.title,
                                it.info,
                                false,
                                //bug
                                1
                            )
                            viewModel.updateProduct(newProduct)
                            cartViewModel.updateCart(newCart)
                            adapter.deleteListProducts()
                        }
                    }
                    favoritList.clear()
                })



            }
        }

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

            val newList:ArrayList<Product> = ArrayList()
            it.forEach {

                if (it.isFavorite) {
                    favoritList.add(it)
                    newList.add(it)
                }
            }
            adapter.setListFavorite(newList)

        })

    }

    fun setupRecycleView() {
        binding.favoriteRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.favoriteRecyclerView.adapter = adapter
    }

    override fun onClickItems(product: Product) {
        val bundle = Bundle()
        val navHostFragment = findNavController()
        bundle.putParcelable("product", product)
        navHostFragment.navigate(R.id.action_favoriteFragment_to_product, bundle)
    }

}