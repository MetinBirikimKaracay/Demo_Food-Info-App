package com.example.foodinfo.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.Food
import com.example.foodinfo.service.FoodDAO
import com.example.foodinfo.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application) : BaseViewModel(application) {

    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(uuid : Int){

        //suspend kullandığımız için launch ile coroutine scopu kullanıyoruz.
        //bunu yapabilmek içinde BaseViewModel yaptık sınıfı
        //9.9
        launch {

            val food = FoodDatabase(getApplication()).foodDao().getFood(uuid)
            foodLiveData.value = food

        }



    }
}