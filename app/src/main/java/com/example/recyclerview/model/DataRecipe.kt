package com.example.recyclerview.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataRecipe (val nameFood :  String,
                       val imageFood : Int,
                       val deskripsi : String,
                       val ingredients: String) : Parcelable