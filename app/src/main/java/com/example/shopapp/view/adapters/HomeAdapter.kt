package com.example.shopapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.model.entitys.Product
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class HomeAdapter(var listener:CallBackHomeAdapter) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var products: ArrayList<Product> = ArrayList()


    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewTitle)
        val prviousPrice: TextView = itemView.findViewById(R.id.textViewPreviousPrice)
        val price: TextView = itemView.findViewById(R.id.textViewPrice)
        val image: ImageView = itemView.findViewById(R.id.imageViewProduct)

        fun bindItems(product: Product) {

            title.text = product.title

            prviousPrice.text = DecimalFormat("###,###,###").format(product.previousPrice)
            price.text = DecimalFormat("###,###,###").format(product.currentPrice)
            Picasso.with(itemView.context).load(product.image).error(R.drawable.error)
                .placeholder(R.drawable.ic_nike_logo).centerCrop().fit().into(image)

            itemView.setOnClickListener {
                listener.onCartClickListenerHomeFragment(product)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rec_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindItems(products.get(position))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    //Methods

    fun setListProducts(list: ArrayList<Product>) {
        products.clear()
        products.addAll(list)
        notifyDataSetChanged()
    }

    fun getListProducts():ArrayList<Product> {
        return products
    }

    fun addProduct(product: Product) {
        products.add(product)
        notifyItemInserted(0)
    }

    fun filterSearchAdapter(list: List<Product>){

        products= ArrayList()
        products= list as ArrayList<Product>
        notifyDataSetChanged()

    }

    interface CallBackHomeAdapter {
        fun onLongCartClickListenerHomeFragment(product: Product)
        fun onCartClickListenerHomeFragment(product: Product)
    }
}





