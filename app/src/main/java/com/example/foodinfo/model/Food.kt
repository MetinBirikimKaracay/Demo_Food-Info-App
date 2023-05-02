package com.example.foodinfo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
//Entity diyerek buranın sql tablosu olduğunu belirtiyoruz. İstersek Entity'in yanına parantez açarak
// tablo ismi belirleyebiliriz eğer bunu yapmazsak tablo ismi otomatik olarak class ismi olur yani Food
@Entity
data class Food (

    //Primary Constructor burası

    //Retrofit için çektiğim verideki keyleri SerializedName olarak üstüne ekledim.
    //Sql tablosu için sütun olduklarını Columninfo diyerek belirtip sütun ismini yazıyoruz.
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val name:String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val calorie:String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val carbohydrate:String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val protein:String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val fat:String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val image:String?

    ) {

    //Body

    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0

}