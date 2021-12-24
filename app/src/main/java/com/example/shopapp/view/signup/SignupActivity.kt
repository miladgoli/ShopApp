package com.example.shopapp.view.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import com.example.shopapp.R
import com.example.shopapp.databinding.ActivitySigninBinding
import com.example.shopapp.databinding.ActivitySignupBinding
import com.example.shopapp.view.signin.SigninActivity
import android.graphics.Typeface

import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.model.entitys.User
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.model.utils.Methods
import com.example.shopapp.view.MainActivity
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.example.shopapp.viewmodel.products.ProductViewModelProvider
import com.example.shopapp.viewmodel.user.UserViewModel
import com.example.shopapp.viewmodel.user.UserViewModelProvider


class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var sharedPreferences: CheckDataProducts
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = CheckDataProducts(this)

        userViewModel = ViewModelProvider(
            this,
            UserViewModelProvider(this)
        ).get(UserViewModel::class.java)

        binding.buttonIHaveAccountSignIn.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
            finish()
        }

        checkIsShowPasswords()



        binding.buttonSignUpSignIn.setOnClickListener {
            if (checkNotNullInputs()){
                val user=User(
                    0
                    ,binding.editTextUserNameSignIn.text.toString()
                    ,binding.editTextEmailSignIn.text.toString()
                    ,binding.editTextPhoneNumberSignIn.text.toString()
                    ,binding.editTextPasswordSignIn.text.toString()
                    ,true
                )
                val intent:Intent=Intent(this,MainActivity::class.java)
                intent.putExtra("user",user)
                startActivity(intent)
                finish()

                userViewModel.addUser(user)

                sharedPreferences.setCheckedApp(true)
            }
        }


    }

    private fun checkNotNullInputs(): Boolean {

        if (binding.editTextUserNameSignIn.text.isEmpty()) {
            return false
        } else if (binding.editTextPhoneNumberSignIn.text.isEmpty()) {
            return false
        } else if (binding.editTextPasswordSignIn.text.isEmpty()) {
            return false
        } else if (binding.editTextConfirmPasswordSignIn.text.isEmpty()) {
            return false
        }else if (binding.editTextPasswordSignIn.text.toString()!=binding.editTextConfirmPasswordSignIn.text.toString()){
            return false
        } else{
            return true
        }

    }

    private fun checkIsShowPasswords() {

        binding.btnShowPasswordSignupActivity.setOnClickListener {
            if (Methods.isPasswordVisible(binding.editTextPasswordSignIn)) {
                Methods.enableInputHiddenPassword(binding.editTextPasswordSignIn);
            } else {
                Methods.enableInputVisiblePassword(binding.editTextPasswordSignIn);
            }
            binding.editTextPasswordSignIn.setSelection(binding.editTextPasswordSignIn.getText().length);
        }

        binding.btnShowConfirmPasswordSignupActivity.setOnClickListener {
            if (Methods.isPasswordVisible(binding.editTextConfirmPasswordSignIn)) {
                Methods.enableInputHiddenPassword(binding.editTextConfirmPasswordSignIn);
            } else {
                Methods.enableInputVisiblePassword(binding.editTextConfirmPasswordSignIn);
            }
            binding.editTextConfirmPasswordSignIn.setSelection(binding.editTextConfirmPasswordSignIn.getText().length);
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
        finish()
    }
}