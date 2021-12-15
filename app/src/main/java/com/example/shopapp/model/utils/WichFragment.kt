package com.example.shopapp.model.utils

import com.example.rezomemasoomie.view.fragments.HomeFragment
import com.example.shopapp.R

class WichFragment {
    private var fragment: Int = 1

    fun nextFragment(){
        fragment++
    }

    fun befureFragment(){
        fragment--
    }

    fun getWichFragmet():Int{
        return fragment
    }
}