package com.example.shopapp.model.utils

import android.R.attr
import android.R.attr.*
import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.rezomemasoomie.view.fragments.HomeFragment
import com.example.rezomemasoomie.view.fragments.ProductFragment
import com.example.shopapp.R
import com.example.shopapp.model.entitys.Product
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

import androidx.core.content.res.ResourcesCompat

import android.graphics.Typeface
import android.view.Gravity
import android.app.ActionBar
import android.content.Context

import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.findNavController


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
            "کفش زنانه طبی نیز یکی از مدل هایی است که طرفداران خاص خود را دارد. برای کارمندانی که نیاز به ایستادن طولانی مدت دارند یا پرستاران و کادر درمان این مدل کفش\u200Cها بسیار کاربردی است؛ بهتر است این مدل کفش زنانه از جنس چرم یا الیاف مصنوعی قابل تنفس و سبک تهیه شود.\n" +
                    "در دسته بندی کفش اسپرت زنانه باید به انواع مدل محبوب کفش کالج و لوفر نیز اشاره کرد. این مدل\u200Cها که گاهی شبیه به کفش مردانه هم هستند، در بین طرفداران کفش\u200Cهای تخت از محبوبیتزیادی برخوردارند.",
            false
        )

        val product2 = Product(
            0,
            630000,
            540000,
            "https://sc04.alicdn.com/kf/H828da70a61dd4505a373de23a09f133be.jpg",
            1,
            "کفش مردانه اسپورت",
            "کفش مردانه اسپرت مناسب برای فعالیت\u200Cهای روزمره است. کفش کتانی را نیز می\u200Cتوان در بخشی از این دسته\u200Cبندی قرار داد. این مدل کفش\u200Cها مدت زمان طولانی در پا هستند و به همین دلیل باید کاملا راحت و سبک باشند. همچنین این مدل کفش مردانه بیشتر با شلوارهای جین، کتان یا اسلش پوشیده شده و مناسب برای استایل\u200Cهای نیمه رسمی و اسپرت هستند.",
            false
        )


        val product3 = Product(
            0,
            147000,
            174000,
            "https://i.pinimg.com/originals/d6/7c/6b/d67c6b346e4a8c47711e8a372d5ad26c.jpg",
            1,
            "کفش بچگانه اسپورت",
            //
            "اگر سن آن\u200Cها کمتر باشد خرید کفش اسپرت بچه گانه متناسب با فعالیت\u200Cهای بدنی آن\u200Cها به عهده بزرگترها خواهد بود. \n" +
                    "\n" +
                    " بهترین برندهای کفش ورزشی پسربچه\n" +
                    "در خرید کفش ورزشی پسرانه یکی از برندهای قدیمی و معروف، کفش ورزشی پسرانه نایک است. این برند سالهاست در زمینه طراحی و تولید انواع محصول برای سنین مختلف فعالیت می\u200Cکند. از طراحی انواع کفش ورزشی پسرانه شیک برای کودکان و نوجوانان گرفته تا تولید انواع کتانی مردانه و  لباس\u200Cهای ورزشی برای بزرگسالان. نایکی به خصوص یکی از بهترین تولید کننده\u200Cهای کفش فوتبال در سطح جهان است. کفش\u200Cهای پیاده روی نایک نیز گزینه\u200Cهایی عالی برای پیاده\u200Cروی\u200Cهای طولانی مدت در مسیرهای سنگی و آسفالت به شمار می\u200Cروند.",
            //
            false
        )

        val product4 = Product(
            0,
            548000,
            394560,
            "https://www.myshoestories.com/media/catalog/product/cache/ba6bdded24c758ed685d4e4087894314/g/r/grace_ivory_satin_1_1.jpg",
            1,
            "کفش زنانه مجلسی",
            "کفش پاشنه دار مجلسی به رنگ\u200Cهای نود Nude  یا مشکی\n" +
                    "کفش زنانه اسپرت برای پوشیدن طولانی مدت یا گاهی مناسب برای محل کار\n" +
                    " بوت زمستانی برای فصل های سرد سال و اوقات بارانی\n" +
                    "کفش زنانه جلو باز مجلسی برای کاهش فشار روی پنجه و راحتی بیشتر. مثلا کفش زنانه چرم برای حضور در مکان های رسمی بهتر است و در عین حال راحتی خاصی دارد",
            false
        )

        val product5 = Product(
            0,
            129000,
            119000,
            "https://sc04.alicdn.com/kf/Hf659e001f7824408b275e45fc1947dfbZ.jpg",
            1,
            "کفش ورزشی پسرانه",
            "کفش\u200C ورزشی پسرانه انواع متنوعی دارد و بسته به رشته ورزشی پسرها متفاوت خواهد بود. از آنجایی که محبوب\u200Cترین ورزش\u200Cها بین پسران معمولا فوتبال و فوتسال است، خرید کفش فوتسال و کفش کتانی فوتبال بین پسرها رایج\u200Cتر است. اگر سن آن\u200Cها کمتر باشد خرید کفش اسپرت بچه گانه متناسب با فعالیت\u200Cهای بدنی آن\u200Cها به عهده بزرگترها خواهد بود. ",
            false
        )

        val product6 = Product(
            0,
            120000,
            49000,
            "https://slipperio.ir/wp-content/uploads/2020/03/58d273883750993ce3acbcda68638364.jpg",
            1,
            "دمپایی زنانه",
            //
            "مدلهای دمپایی زنانه جدید برخلاف دمپایی زنانه قدیمی، دارای تنوع زیادی است که برای روی فرش، خارج از خانه و سرویس بهداشتی کاربرد دارد و در انواع متنوع دمپایی جلو بسته، جلو باز، دمپایی دست دوز و دیگر انواع آن در دسترس خانم\u200Cها قرار می\u200Cگیرد.\n" +
                    "با توجه به اینکه این روزها در خانه\u200Cها از کفپوش\u200Cهایی مانند موکت، سنگ یا سرامیک استفاده می\u200Cشود استفاده از انواع برند دمپایی زنانه روفرشی نیز در بازار افزایش پیدا کرده است. دمپایی\u200Cهای تولید شده توسط برندهای معتبر استانداردهای بیشتری را رعایت می\u200Cکنند.",
            //
            false
        )

        val product7 = Product(
            0,
            250000,
            115000,
            "https://sisishoe.com/wp-content/uploads/2018/06/%DA%A9%D9%81%D8%B4-%D9%85%D8%B1%D8%AF%D8%A7%D9%86%D9%87-%D9%85%D8%AC%D9%84%D8%B3%DB%8C-%DA%A9%D9%81%D8%B4-%D8%AF%D8%B3%D8%AA%D8%AF%D9%88%D8%B2-%D8%B3%DB%8C-%D8%B3%DB%8C-sisi-shoes.jpg",
            1,
            "کفش مردانه مجلسی",
            //
            "لزوم آشنایی با انواع مختلف کفش رسمی مردانه به عنوان یکی از آیتم\u200Cهای اصلی و روزمره استایل، ضروری است و به شما در داشتن یک استایل جذاب کمک می\u200Cکند. به همین دلیل در ادامه به معرفی انواع کفش مردانه می\u200Cپردازیم.\n" +
                    "کفش رسمی مردانه معمولا شامل انواع کفش\u200Cهای کالج سگک\u200Cدار، مدل کفش آکسفورد، کالج رسمی پاپیون\u200Cدار، انواع کفش مردانه بندی با جنس\u200Cهای چرم و ورنی می\u200Cشود. معمولا کفش\u200Cهایی که از جنس جیر تهیه شده باشند، در استایل نیمه رسمی کاربرد دارند و برای استایل رسمی کاملا مناسب نبوده و به اصطلاح کفش مجلسی به شمار نمی\u200Cآیند.",
            //
            false
        )

        val product8 = Product(
            0,
            148000,
            99900,
            "https://cdnfa.com/abeefa/20bd/files/2726053.jpg",
            1,
            "دمپایی مردانه",
            //
            "مپایی\u200Cهای مردانه به نوعی از راحت\u200Cترین پاپوش\u200Cهای آقایان هستند؛ این دمپایی\u200Cها سبک هستند، می\u200Cتوان همه جا آن ها را با خود به همراه داشت و اگر مدلی شیک داشته باشند می\u200Cتوان به جای صندل مردانه سراغ\u200Cشان رفت. \n" +
                    "\n" +
                    "کاربرد دمپایی مردانه\n" +
                    "\n" +
                    "اولین و معمولا مهم\u200Cترین کاربرد دمپایی مردانه برای آقایان استفاده از آن به عنوان کفش رو فرشی است. در پایان روز هنگامی که بعد از چندین ساعت پوشیدن کفش پاها خسته شده\u200Cاند، کفی نرم و سبک انواع دمپایی روفرشی مردانه با جنس مرغوبی که دارد، می\u200Cتواند خستگی را به خوبی از پاها درکند و آرامش را به بدن بازگرداند.",
            //
            false
        )

        val product9 = Product(
            0,
            120000,
            114000,
            "https://lotto.expressleather.com.bd/wp-content/uploads/2020/08/Lotto-BD-Soft-Sport-Slipper-for-Men-3.jpg",
            1,
            "دمپایی انگشتی مردانه",
            "از دوست\u200C داشتنی\u200Cترین انواع مدل دمپایی\u200Cهای مردانه، دمپایی ابری است که ابتدا به ویژه در مناطق جنوبی کشور با آب و هوای گرم،  از قدیم مورد استفاده بوده است. در هنگام انتخاب دمپایی ابری یا انگشتی باید دقت کافی به جنس کفی دمپایی و لایه پلاستیک میان دمپایی داشت؛ چون در صورت غیر مرغوب بودن این دو مورد دمپایی ابری بدترین دشمن پای شماست و فقط باعث زخم و تاول خواهد شد.",
            false
        )

        val product10 = Product(
            0,
            45000,
            39000,
            "https://www.libertyshoesonline.com/pub/media/catalog/product/cache/e1206a7caced86c4e49cf28d4d46c3d9/c/o/comfywalk2_grey_1.jpg",
            1,
            "دمپایی انگشتی زنانه",
            "دمپایی انگشتی در ماه\u200Cهای گرم سال کاربرد بیشتری دارد. شما از دمپایی لا انگشتی می\u200Cتوانید علاوه بر خانه، در استخر، کنار ساحل و حتی در آرایشگاه نیز هنگام دریافت خدماتی همچون پدیکور یا لاک ناخن استفاده کنید.",
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
            transaction.replace(R.id.mainFragment, ProductFragment(), "tag")
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onSnackAddedCart(view: View, text: String, listener: View.OnClickListener) {

        val snackbar = Snackbar.make(
            view, text,
            Snackbar.LENGTH_SHORT
        ).setAction("Action", null)

        val snackbarView = snackbar.view

        val textView =
            snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView

        textView.textSize = 16f

        snackbarView.setBackgroundColor(view.resources.getColor(R.color.m_blue))

        textView.setTextColor(view.resources.getColor(R.color.m_white))

        val typeface = ResourcesCompat.getFont(view.context, R.font.vazir_medium)
        textView.setTypeface(typeface)

        snackbar.setAction("نمایش سبد خرید", listener)
        snackbar.setActionTextColor(view.resources.getColor(R.color.m_very_dark_blue))

        snackbar.show()
    }

    val INPUT_TYPE_VISIBLE_PASSWORD =
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

    val INPUT_TYPE_HIDDEN_PASSWORD =
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

     fun isPasswordVisible(editText: EditText): Boolean {
        return editText.inputType == INPUT_TYPE_VISIBLE_PASSWORD
    }

     fun enableInputVisiblePassword(editText: EditText) {
        val cache = editText.typeface
        editText.inputType = INPUT_TYPE_VISIBLE_PASSWORD
        editText.typeface = cache
    }

     fun enableInputHiddenPassword(editText: EditText) {
        val cache = editText.typeface
        editText.inputType = INPUT_TYPE_HIDDEN_PASSWORD
        editText.typeface = cache
    }

}