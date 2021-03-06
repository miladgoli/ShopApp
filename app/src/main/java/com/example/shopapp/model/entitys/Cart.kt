package com.example.shopapp.model.entitys

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shopapp.model.utils.Utils.TABLE_NAME_CART
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME_CART)
data class Cart (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @SerializedName("previous_price")
    val previousPrice: Long,
    @SerializedName("price")
    val currentPrice: Long,
    val image: String,
    val status: Int,
    val title: String,
    val info:String,
    //for contain favorite section
    var isFavorite: Boolean = false,
    var count:Int
    ) : Parcelable