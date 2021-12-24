package com.example.shopapp.model.entitys

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shopapp.model.utils.Utils
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Utils.TABLE_NAME_USER)

data class User (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("user_name")
    val userName: String,

    val email: String,

    @SerializedName("phone_number")
    val phoneNumber: String,

    var password: String,

    var isLogin:Boolean

) : Parcelable
