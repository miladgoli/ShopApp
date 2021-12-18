package com.example.shopapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.model.entitys.Product
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class FavoriteAdapter(val listener:ListenerFavoriteAdapter) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    val favoriteList:ArrayList<Product> = ArrayList()

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                 listener.onClickItems(product)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rec_main, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItems(favoriteList.get(position))
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun setListFavorite(list: ArrayList<Product>) {
        favoriteList.clear()
        favoriteList.addAll(list)
        notifyDataSetChanged()
    }

    fun deleteListProducts() {
        favoriteList.clear()
        notifyDataSetChanged()
    }

    fun getListFavorite():ArrayList<Product>{
        return favoriteList
    }

    interface ListenerFavoriteAdapter{
        fun onClickItems(product: Product)
    }

}