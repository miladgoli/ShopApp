package com.example.shopapp.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentEditProfileBinding
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.model.entitys.User
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.model.utils.Dialogs
import com.example.shopapp.view.resetpassword.ResetActivity
import com.example.shopapp.view.signin.SigninActivity
import com.example.shopapp.viewmodel.cart.CartViewModel
import com.example.shopapp.viewmodel.cart.CartViewModelProvider
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider
import com.example.shopapp.viewmodel.user.UserViewModel
import com.example.shopapp.viewmodel.user.UserViewModelProvider

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var user: User
    private lateinit var viewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var listUser: ArrayList<User>
    private lateinit var sharedPreferences: CheckDataProducts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = CheckDataProducts(requireContext())

        userViewModel = ViewModelProvider(
            this,
            UserViewModelProvider(requireContext())
        ).get(UserViewModel::class.java)

        viewModel = ViewModelProvider(
            this,
            ProductViewModelProvider(requireContext())
        ).get(ProductViewModel::class.java)

        cartViewModel = ViewModelProvider(
            this,
            CartViewModelProvider(requireContext())
        ).get(CartViewModel::class.java)

        userViewModel.getUsersMethod()
        userViewModel.getUsers().observe(requireActivity(), Observer {
            it.forEach { newUser ->
                if (newUser.isLogin) {
                    binding.editTextUserNameEdit.setText(newUser.userName)
                    binding.editTextEmailEdit.setText(newUser.email)
                    binding.editTextPhoneNumberEdit.setText(newUser.phoneNumber)

                    user = User(
                        newUser.id,
                        binding.editTextUserNameEdit.text.toString(),
                        binding.editTextEmailEdit.text.toString(),
                        binding.editTextPhoneNumberEdit.text.toString(),
                        newUser.password,
                        newUser.isLogin
                    )
                }
            }
        })

        binding.buttonResetPasswordEdit.setOnClickListener {
            sharedPreferences.setCheckedApp(false)
            val intent = Intent(requireContext(), ResetActivity::class.java)
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
        }
0
        binding.buttonSubmitEdit.setOnClickListener {

            if (user.userName == binding.editTextUserNameEdit.text.toString() &&
                user.phoneNumber == binding.editTextPhoneNumberEdit.text.toString() &&
                user.email == binding.editTextEmailEdit.text.toString()
            ) {

                val navHostFragment = findNavController()
                navHostFragment.navigate(R.id.action_editProfileFragment_to_profile)

            } else {

                if (binding.editTextUserNameEdit.text.isNotEmpty()) {
                    user.userName = binding.editTextUserNameEdit.text.toString()
                }
                if (binding.editTextEmailEdit.text.isNotEmpty()) {
                    user.email = binding.editTextEmailEdit.text.toString()
                }
                if (binding.editTextPhoneNumberEdit.text.isNotEmpty()) {
                    user.phoneNumber = binding.editTextPhoneNumberEdit.text.toString()
                }
                Dialogs.showSubmitEditedInfoDialog(requireContext(), {

                    userViewModel.updateUser(user)
                    val navHostFragment = findNavController()
                    navHostFragment.navigate(R.id.action_editProfileFragment_to_profile)

                }, {

                    val navHostFragment = findNavController()
                    navHostFragment.navigate(R.id.action_editProfileFragment_to_profile)

                })

            }
        }
    }

}