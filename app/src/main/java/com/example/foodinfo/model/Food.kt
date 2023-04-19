package com.example.foodinfo.model

import com.google.gson.annotations.SerializedName

data class Food (

    //Retrofit için çektiğim verideki keyleri SerializedName olarak üstüne ekledim.

    @SerializedName("isim")
    val name:String?,
    @SerializedName("kalori")
    val calorie:String?,
    @SerializedName("karbonhidrat")
    val carbohydrate:String?,
    @SerializedName("protein")
    val protein:String?,
    @SerializedName("yag")
    val fat:String?,
    @SerializedName("gorsel")
    val image:String?
    ) {

}