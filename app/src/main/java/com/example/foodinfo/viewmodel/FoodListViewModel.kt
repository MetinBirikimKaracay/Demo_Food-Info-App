package com.example.foodinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.Food

class FoodListViewModel : ViewModel() {

    val foods = MutableLiveData<List<Food>>()
    val errorMesage = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshData(){

        val elma = Food("Elma","80","15","0","1","www.test.com")
        val armut = Food("Armut","100","25","2","4","www.test.com")
        val kiraz = Food("Kiraz","30","5","0","0","www.test.com")

        val FoodList = arrayListOf<Food>(elma,armut,kiraz)

        foods.value = FoodList
        errorMesage.value = false
        loading.value = false
    }

}