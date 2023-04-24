package com.example.foodinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodinfo.model.Food
import com.example.foodinfo.service.FoodAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.ScheduledThreadPoolExecutor

class FoodListViewModel : ViewModel() {

    val foods = MutableLiveData<List<Food>>()
    val errorMesage = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val foodAPIService = FoodAPIService()
    private val disposable = CompositeDisposable()

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

                        loading.value = false
                        foods.value = t
                        errorMesage.value = false

                    }

                    override fun onError(e: Throwable) {

                        loading.value = false
                        errorMesage.value = true
                        e.printStackTrace()

                    }

                })
        )

    }

}