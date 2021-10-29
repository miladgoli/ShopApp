package com.example.shopapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.model.utils.Methods
import com.example.shopapp.view.adapters.HomeAdapter
import com.example.shopapp.view.adapters.callBackHomeAdapter
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), callBackHomeAdapter {
    private lateinit var sharedPreferences: CheckDataProducts
    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding= FragmentHomeBinding.inflate(layoutInflater)

        var list:ArrayList<Product> = Methods.getNewListProducts()

        //Setup Navigation View And Bottom Navigation
        navigationSetups()

        //initialize view model and adapter and shredPreference
        initializeBaseUtils()

        //check first open app
        if (!sharedPreferences.getSuccess()) {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
            sharedPreferences.setCheckedApp(true)

            for (i in Methods.getNewListProducts().indices){
                viewModel.addProduct(list.get(i))
            }
        }



    }


    fun navigationSetups() {
        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationMain)
        val navController = Navigation.findNavController(this, R.id.mainFragment)
        NavigationUI.setupWithNavController(navView, navController)
    }

    fun initializeBaseUtils() {
        adapter = HomeAdapter(this)

        viewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(this)
        ).get(ProductViewModel::class.java)

        sharedPreferences = CheckDataProducts(this)
    }

    override fun onCartClickListener(product: Product) {
    }
}