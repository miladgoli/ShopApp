package com.example.shopapp.view.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shopapp.view.resetpassword.ResetActivity
import com.example.shopapp.databinding.ActivitySigninBinding
import com.example.shopapp.model.entitys.User
import com.example.shopapp.model.sharedprefrence.CheckDataProducts
import com.example.shopapp.model.utils.Methods
import com.example.shopapp.view.MainActivity
import com.example.shopapp.view.signup.SignupActivity
import com.example.shopapp.viewmodel.user.UserViewModel
import com.example.shopapp.viewmodel.user.UserViewModelProvider

class SigninActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySigninBinding
    private lateinit var sharedPreferences: CheckDataProducts
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = CheckDataProducts(this)

        userViewModel = ViewModelProvider(
            this,
            UserViewModelProvider(this)
        ).get(UserViewModel::class.java)

        checkIsShowPasswords()

        binding.buttonGoToSignUp.setOnClickListener {
            val intent= Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonForgetPasswordSignIn.setOnClickListener {
            val intent= Intent(this, ResetActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonSignInSignIn.setOnClickListener {
            userViewModel.getUsersMethod()
            userViewModel.getUsers().observe(this, Observer {
                checkAccountUser(it as ArrayList<User>)
            })

        }

    }

    private fun checkIsShowPasswords() {
        binding.btnShowPasswordSigninActivity.setOnClickListener {
            if (Methods.isPasswordVisible(binding.editTextPasswordSignIn)) {
                Methods.enableInputHiddenPassword(binding.editTextPasswordSignIn);
            } else {
                Methods.enableInputVisiblePassword(binding.editTextPasswordSignIn);
            }
            binding.editTextPasswordSignIn.setSelection(binding.editTextPasswordSignIn.getText().length);
        }
    }

    fun checkAccountUser(list:ArrayList<User>){
        if(binding.editTextUserNameSignIn.text.isEmpty()){

        }else if (binding.editTextPasswordSignIn.text.isEmpty()){

        }else{
            list.forEach {
                if (it.userName==binding.editTextUserNameSignIn.text.toString()){
                    if (it.password==binding.editTextPasswordSignIn.text.toString()){
                        val user=User(
                            it.id
                            ,it.userName
                            ,it.email
                            ,it.phoneNumber
                            ,it.password
                            ,true
                        )
                        val intent=Intent(this,MainActivity::class.java)
                        intent.putExtra("user",user)
                        startActivity(intent)
                        finish()
                        userViewModel.updateUser(user)
                        sharedPreferences.setCheckedApp(true)
                    }else{
                        Toast.makeText(this,"رمز اشتباه است",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}