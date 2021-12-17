package com.example.shopapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.rezomemasoomie.view.fragments.HomeFragment
import com.example.rezomemasoomie.view.fragments.ProductFragment
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentHomeBinding
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.model.utils.Methods
import com.example.shopapp.model.utils.WichFragment
import com.example.shopapp.view.adapters.HomeAdapter
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), HomeAdapter.CallBackHomeAdapter {

    private lateinit var sharedPreferences: CheckDataProducts
    private lateinit var viewModel: ProductViewModel
    private lateinit var adapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var witchFragment: WichFragment
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = FragmentHomeBinding.inflate(layoutInflater)
        adapter = HomeAdapter(this)

        var list: ArrayList<Product> = Methods.getNewListProducts()

        //Setup Navigation View And Bottom Navigation
        navigationSetups()

        //initialize view model and adapter and shredPreference
        initializeBaseUtils()


        //check first open app
        if (!sharedPreferences.getSuccess()) {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
            sharedPreferences.setCheckedApp(true)



            for (i in Methods.getNewListProducts().indices) {
                viewModel.addProduct(list.get(i))
            }
        }
    }


    fun navigationSetups() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.mainFragment
        ) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationMain)
        bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.home, R.id.cart, R.id.profile)
        )
    }

    fun initializeBaseUtils() {

        viewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(this)
        ).get(ProductViewModel::class.java)

        sharedPreferences = CheckDataProducts(this)
    }


    override fun onBackPressed() {
        /*
       if(supportFragmentManager.findFragmentByTag("tag")!!.allowReturnTransitionOverlap){
         */
        super.onBackPressed()

    }


    override fun onLongCartClickListenerHomeFragment(product: Product) {
        TODO("Not yet implemented")
    }

    override fun onCartClickListenerHomeFragment(product: Product) {
    }


}