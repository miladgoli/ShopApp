package com.example.shopapp.view.resetpassword

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.abproject.athsample.data.mail.SendEmailApi
import com.example.shopapp.R
import com.example.shopapp.databinding.ActivityResetBinding
import com.example.shopapp.model.entitys.User
import com.example.shopapp.model.utils.Methods
import com.example.shopapp.model.utils.Resource
import com.example.shopapp.view.signin.SigninActivity
import com.example.shopapp.viewmodel.user.UserViewModel
import com.example.shopapp.viewmodel.user.UserViewModelProvider
import javax.mail.Quota

class ResetActivity : AppCompatActivity() {

    lateinit var binding: ActivityResetBinding
    val sendEmail = SendEmailApi()
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIsShowPasswords()

        userViewModel = ViewModelProvider(
            this,
            UserViewModelProvider(this)
        ).get(UserViewModel::class.java)

        binding.buttonGetCodeForgetPassword.setOnClickListener {

            if (binding.editTextEmailForgetPassword.text.isNotEmpty()) {

                userViewModel.getUsersMethod()
                userViewModel.getUsers().observe(this, Observer {

                    it.forEach { user ->
                        if (user.email == binding.editTextEmailForgetPassword.text.toString()) {

                            binding.frameNewPass.visibility = View.VISIBLE
                            binding.frameConfirmNewPass.visibility = View.VISIBLE
                            binding.editTextEmailForgetPassword.isEnabled = false
                            openSoftKeyboard(this, binding.editTextNewPasswordReset)
                            binding.buttonGetCodeForgetPassword.visibility = View.GONE
                            binding.buttonSuccessCodeForgetPassword.visibility = View.VISIBLE
                            binding.buttonSuccessCodeForgetPassword.isEnabled = false
                            binding.buttonSuccessCodeForgetPassword.setTextColor(this.resources.getColor(R.color.m_hint))
                            binding.buttonSuccessCodeForgetPassword.setStrokeColorResource(R.color.m_hint)
                            binding.editTextConfirmNewPasswordReset.addTextChangedListener(object :
                                TextWatcher {
                                override fun beforeTextChanged(
                                    s: CharSequence?,
                                    start: Int,
                                    count: Int,
                                    after: Int
                                ) {

                                }

                                override fun onTextChanged(
                                    s: CharSequence?,
                                    start: Int,
                                    before: Int,
                                    count: Int
                                ) {
                                    if (s.toString() == binding.editTextNewPasswordReset.text.toString()) {
                                        binding.buttonSuccessCodeForgetPassword.isEnabled = true
                                        binding.buttonSuccessCodeForgetPassword.setTextColor(binding.root.resources.getColor(R.color.m_blue_dark))
                                        binding.buttonSuccessCodeForgetPassword.setStrokeColorResource(R.color.m_blue_dark)

                                    }
                                }

                                override fun afterTextChanged(s: Editable?) {

                                }
                            })
                        }



                        binding.buttonSuccessCodeForgetPassword.setOnClickListener {
                            val newUser = User(
                                user.id,
                                user.userName,
                                user.email,
                                user.phoneNumber,
                                binding.editTextNewPasswordReset.text.toString(),
                                user.isLogin
                            )
                            userViewModel.updateUser(newUser)

                            val intent = Intent(this, SigninActivity::class.java)
                            startActivity(intent)
                            finish()
                            Toast.makeText(this, "Password Changed !", Toast.LENGTH_SHORT).show()
                        }

                    }
                })


            } else {

            }

        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
        finish()

    }

    fun openSoftKeyboard(context: Context, view: View) {

        view.requestFocus()
        // open the soft keyboard
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        
    }


    private fun checkIsShowPasswords() {

        binding.btnShowPasswordSignupActivity.setOnClickListener {
            if (Methods.isPasswordVisible(binding.editTextNewPasswordReset)) {
                Methods.enableInputHiddenPassword(binding.editTextNewPasswordReset);
            } else {
                Methods.enableInputVisiblePassword(binding.editTextNewPasswordReset);
            }
            binding.editTextNewPasswordReset.setSelection(binding.editTextNewPasswordReset.getText().length);
        }

        binding.btnShowConfirmPasswordSignupActivity.setOnClickListener {
            if (Methods.isPasswordVisible(binding.editTextConfirmNewPasswordReset)) {
                Methods.enableInputHiddenPassword(binding.editTextConfirmNewPasswordReset);
            } else {
                Methods.enableInputVisiblePassword(binding.editTextConfirmNewPasswordReset);
            }
            binding.editTextConfirmNewPasswordReset.setSelection(binding.editTextConfirmNewPasswordReset.getText().length);
        }

    }

    fun sendEmailToUser(
        userEmail: String
    ): LiveData<Resource<String>> {

        sendEmail.sendEmail(userEmail)

        return sendEmail.emailStatus
    }

}