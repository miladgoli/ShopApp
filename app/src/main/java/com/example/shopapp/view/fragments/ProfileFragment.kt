package com.example.rezomemasoomie.view.fragments

import android.content.Intent
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
import com.example.shopapp.databinding.FragmentProfileBinding
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.entitys.User
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.model.utils.Dialogs
import com.example.shopapp.view.signin.SigninActivity
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider
import com.example.shopapp.viewmodel.user.UserViewModel
import com.example.shopapp.viewmodel.user.UserViewModelProvider

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var viewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var listUser: ArrayList<User>
    private lateinit var sharedPreferences: CheckDataProducts


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = CheckDataProducts(requireContext())

        initializeAndCheckViewModel()

        getUserFromViewModel()

        binding.favoritesListTextViewFragmentProfile.setOnClickListener {
            val navHostFragment = findNavController()
            navHostFragment.navigate(R.id.action_profile_to_favoriteFragment)
        }

        binding.profileEditTextViewFragmentProfile.setOnClickListener {
            val navHostFragment = findNavController()
            navHostFragment.navigate(R.id.action_profile_to_editProfileFragment)
        }

        binding.orderHistoryTextViewFragmentProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Comming Soon...", Toast.LENGTH_SHORT).show()
        }

        binding.signoutTextViewFragmentProfile.setOnClickListener {
            Dialogs.showSignoutDialog(requireContext(), View.OnClickListener {
                sharedPreferences.setCheckedApp(false)
                val intent = Intent(requireContext(), SigninActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
                cartViewModel.deleteAllProduct()
                viewModel.getAllProducts()
                viewModel.getProducts().observe(requireActivity(), Observer {
                    it.forEach { prodduct ->

                        val prod = Product(
                            prodduct.id,
                            prodduct.previousPrice,
                            prodduct.currentPrice,
                            prodduct.image,
                            prodduct.status,
                            prodduct.title,
                            prodduct.info,
                            false
                        )

                        viewModel.updateProduct(prod)
                    }
                })
                userViewModel.getUsersMethod()
                userViewModel.getUsers().observe(requireActivity(), Observer {
                    it.forEach {
                        it.isLogin = false
                        userViewModel.updateUser(it)
                    }
                })
            })

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

        userViewModel = ViewModelProvider(
            this,
            UserViewModelProvider(requireContext())
        ).get(UserViewModel::class.java)


    }

    fun getUserFromViewModel() {

        var bundle: Bundle? = requireActivity().intent.extras


        if (bundle == null) {
            userViewModel.getUsersMethod()
            userViewModel.getUsers().observe(requireActivity(), Observer {
                it.forEach {
                    if (it.isLogin) {
                        binding.usernameTextViewProfileFragment.text = it.userName
                        binding.phoneTextViewProfileFragment.text = it.phoneNumber
                    }
                }
            })
        } else {
            val user = bundle.getParcelable<User>("user")
            binding.usernameTextViewProfileFragment.text = user!!.userName
            binding.phoneTextViewProfileFragment.text = user.phoneNumber
        }


    }
}