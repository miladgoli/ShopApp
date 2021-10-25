package com.example.shopapp.model.sharedprefrence

import android.content.Context
import android.content.SharedPreferences

class CheckDataProducts(val context: Context) {

    val sharedPreferences:SharedPreferences= context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    fun setCheckedApp(cheked: Boolean){
        val editor = sharedPreferences.edit()
        editor.putBoolean("check", cheked)
        editor.apply()
    }

    fun getSuccess(): Boolean {
        return sharedPreferences.getBoolean("check", false)
    }
}