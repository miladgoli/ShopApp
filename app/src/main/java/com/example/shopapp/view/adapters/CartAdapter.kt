package com.example.shopapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.R
import com.example.shopapp.model.entitys.Cart
import com.example.shopapp.model.entitys.Product
import com.example.shopapp.viewmodel.products.ProductViewModel
import com.squareup.picasso.Picasso

class CartAdapter(var itemClickCartListener:CartAdapterListener) : RecyclerView.Adapter<CartAdapter.HomeViewHolder>() {

    var shops:ArrayList<Cart> = ArrayList()
    lateinit var viewModel:ProductViewModel

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView=itemView.findViewById(R.id.textViewTitle)
//        val prviousPrice:TextView=itemView.findViewById(R.id.textViewPreviousPrice)
//        val price:TextView=itemView.findViewById(R.id.textViewPrice)
        val image:ImageView=itemView.findViewById(R.id.imageViewProduct)
        val count:TextView=itemView.findViewById(R.id.textviewCountProductItemCart)
        val addBtn:ImageView=itemView.findViewById(R.id.btnAddCountItemCart)
        val lowBtn:ImageView=itemView.findViewById(R.id.btnLowCountItemCart)


        fun bindItems(cart: Cart){

            title.text=cart.title

//            prviousPrice.text= DecimalFormat("###,###,###").format(cart.previousPrice)
//            price.text=DecimalFormat("###,###,###").format(cart.currentPrice)

            count.text=cart.count.toString()

            Picasso.with(itemView.context).load(cart.image).centerCrop().fit().error(R.drawable.error).placeholder(R.drawable.ic_nike_logo).into(image)

            itemView.setOnClickListener {
                itemClickCartListener.onItemClickItemListener(cart)
            }
            itemView.setOnLongClickListener {
                itemClickCartListener.onLongClickItemListener(cart)
                return@setOnLongClickListener true
            }

            addBtn.setOnClickListener {
                itemClickCartListener.onAddCartClickItemListener(cart)
            }

            lowBtn.setOnClickListener {
                itemClickCartListener.onLowCartClickItemListener(cart)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
       return HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rec_cart,parent,false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindItems(shops.get(position))
    }

    override fun getItemCount(): Int {
        return shops.size
    }

    //Methods

    fun addProductCart(product: Product){
        val cart:Cart=Cart(product.id,product.previousPrice,product.currentPrice,product.image,product.status,product.title,product.info,product.isFavorite,1)
        shops.add(cart)
        notifyItemInserted(0)
    }

    fun deleteProductCart(cart: Cart){
        shops.remove(cart)
        notifyDataSetChanged()
    }

    fun deleteAllProductCart(){
        shops.removeAll(shops)
        notifyDataSetChanged()
    }

    fun updateCart(cart: Cart){

        val index= shops.indexOf(cart)

        if(index>-1){
            notifyItemChanged(index)
        }

    }


    fun setListProducts(list:ArrayList<Cart>){
        shops.clear()
        shops.addAll(list)
        notifyDataSetChanged()
    }

    interface CartAdapterListener{
        fun onItemClickItemListener(cart: Cart)
        fun onAddCartClickItemListener(cart: Cart)
        fun onLowCartClickItemListener(cart: Cart)
        fun onLongClickItemListener(cart: Cart)
    }
}