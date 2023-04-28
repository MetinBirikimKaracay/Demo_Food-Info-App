package com.example.foodinfo.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.Food
import com.example.foodinfo.service.FoodAPIService
import com.example.foodinfo.service.FoodDatabase
import com.example.foodinfo.util.UniqueSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.ScheduledThreadPoolExecutor

class FoodListViewModel(application: Application) : BaseViewModel(application) {

    val foods = MutableLiveData<List<Food>>()
    val errorMesage = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val foodAPIService = FoodAPIService()
    private val disposable = CompositeDisposable()
    private val uniqueSharedPreferences = UniqueSharedPreferences(getApplication())

    fun refreshData(){

        downloadDataFromInternet()

    }

    //8.6 video
    fun downloadDataFromInternet() {

        loading.value = true

        disposable.add(
            foodAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                    override fun onSuccess(t: List<Food>) {

                        sqlite(t)

                    }

                    override fun onError(e: Throwable) {

                        loading.value = false
                        errorMesage.value = true
                        e.printStackTrace()

                    }

                })
        )

    }

    //9.6
    private fun showFoods(foodsList : List<Food>){

        loading.value = false
        foods.value = foodsList
        errorMesage.value = false

    }

    private fun sqlite(foodList: List<Food>) {

        launch {

            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val uuidList = dao.insertAll(*foodList.toTypedArray())
            var i = 0
            while (i < foodList.size){

                foodList[i].uuid = uuidList[i].toInt()
                i += 1
            }
            showFoods(foodList)

        }

        uniqueSharedPreferences.timer(System.nanoTime())

    }

}