package com.example.foodinfo.service

import com.example.foodinfo.model.Food
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FoodAPIService {

    //APİ oluşturmak için kullanıyoruz. 8.5 videosunu izle
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        //Json formatını modele çevirmek için kullanıyoruz
        .addConverterFactory(GsonConverterFactory.create())
        //RxJava kullacağımız için ekliyoruz. Kullanmayacaksak eklemeye gerek yok
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        //Bitirip oluşturmak için build ekliyoruz.
        .build()
        .create(FoodAPI::class.java)

    //"FoodAPI" adlı arayüz ile internetten çektiğimiz verileri bu fonksiyonla alıyoruz
    fun getData() : Single<List<Food>> {
        return api.getFood()
    }

}