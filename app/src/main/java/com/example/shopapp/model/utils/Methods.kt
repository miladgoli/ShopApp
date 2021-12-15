package com.example.shopapp.model.utils

import androidx.fragment.app.FragmentManager
import com.example.rezomemasoomie.view.fragments.HomeFragment
import com.example.rezomemasoomie.view.fragments.ProductFragment
import com.example.shopapp.R
import com.example.shopapp.model.entitys.Product

object Methods {

    var fragment: Int = 1

    fun getNewListProducts(): ArrayList<Product> {
        var list: ArrayList<Product> = ArrayList()

        val product1 = Product(
            0,
            310000,
            160000,
            "https://5.imimg.com/data5/EK/FU/MY-23146560/womens-sports-shoes-fogg-208-500x500.jpg",
            1,
            "کفش زنانه اسپورت",
            false
        )

        val product2 = Product(
            0,
            630000,
            540000,
            "https://sc04.alicdn.com/kf/H828da70a61dd4505a373de23a09f133be.jpg",
            1,
            "کفش مردانه اسپورت",
            false
        )


        val product3 = Product(
            0,
            147000,
            174000,
            "https://i.pinimg.com/originals/d6/7c/6b/d67c6b346e4a8c47711e8a372d5ad26c.jpg",
            1,
            "کفش بچگانه اسپورت",
            false
        )

        val product4 = Product(
            0,
            548000,
            394560,
            "https://www.myshoestories.com/media/catalog/product/cache/ba6bdded24c758ed685d4e4087894314/g/r/grace_ivory_satin_1_1.jpg",
            1,
            "کفش زنانه مجلسی",
            false
        )

        val product5 = Product(
            0,
            129000,
            119000,
            "https://sc04.alicdn.com/kf/Hf659e001f7824408b275e45fc1947dfbZ.jpg",
            1,
            "کفش ورزشی پسرانه",
            false
        )

        val product6 = Product(
            0,
            120000,
            49000,
            "https://slipperio.ir/wp-content/uploads/2020/03/58d273883750993ce3acbcda68638364.jpg",
            1,
            "دمپایی زنانه",
            false
        )

        val product7 = Product(
            0,
            250000,
            115000,
            "https://kafashkhane.ir/wp-content/uploads/2019/04/84332ec6bf3aa1cdcf35861ecdf51907.jpg",
            1,
            "کفش مردانه مجلسی",
            false
        )

        val product8 = Product(
            0,
            148000,
            99900,
            "https://cdnfa.com/abeefa/20bd/files/2726053.jpg",
            1,
            "دمپایی مردانه",
            false
        )

        val product9 = Product(
            0,
            120000,
            114000,
            "https://lotto.expressleather.com.bd/wp-content/uploads/2020/08/Lotto-BD-Soft-Sport-Slipper-for-Men-3.jpg",
            1,
            "دمپایی انگشتی مردانه",
            false
        )

        val product10 = Product(
            0,
            45000,
            39000,
            "https://www.libertyshoesonline.com/pub/media/catalog/product/cache/e1206a7caced86c4e49cf28d4d46c3d9/c/o/comfywalk2_grey_1.jpg",
            1,
            "دمپایی انگشتی زنانه",
            false
        )

        list.add(product1)
        list.add(product2)
        list.add(product3)
        list.add(product4)
        list.add(product5)
        list.add(product6)
        list.add(product7)
        list.add(product8)
        list.add(product9)
        list.add(product10)

        return list
    }

    fun witchFragment(witchClicked: Int, manager: FragmentManager) {

        val transaction = manager.beginTransaction()

        if (witchClicked == 1) {
            transaction.replace(R.id.mainFragment, HomeFragment())

        } else if (witchClicked == 2) {
            transaction.replace(R.id.mainFragment, ProductFragment(),"tag")
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }


}