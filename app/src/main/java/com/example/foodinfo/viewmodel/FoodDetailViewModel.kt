package com.example.foodinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.Food

class FoodDetailViewModel : ViewModel() {

    val foodLiveData = MutableLiveData<Food>()

    fun roomVerisiniAl(){

        val elma = Food("Elma","80","15","0","1","www.test.com")

        foodLiveData.value = elma

    }
}