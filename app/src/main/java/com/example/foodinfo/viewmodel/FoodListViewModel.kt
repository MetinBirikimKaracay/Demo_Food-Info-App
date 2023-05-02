package com.example.foodinfo.viewmodel

import android.app.Application
import android.widget.Toast
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

    //10 Dakikayı nanotime a çevirdik sonuna l koyarak long yaptık.
    private val updateTime = 0.2 * 60 * 1000 * 1000 * 1000L
    private val foodAPIService = FoodAPIService()
    private val disposable = CompositeDisposable()
    private val uniqueSharedPreferences = UniqueSharedPreferences(getApplication())

    fun refreshData(){

        val downloadTime = uniqueSharedPreferences.getTime()
        if (downloadTime != null && downloadTime != 0L && System.nanoTime() - downloadTime < updateTime){
            //güncelleme zamanı gelmedi sqlitetan çek

            downloadDataFromSQLite()

        }else{
            downloadDataFromInternet()
        }

    }

    fun downloadDataFromSQLite(){

        launch {

            val foods = FoodDatabase(getApplication()).foodDao().getAllFood()
            showFoods(foods)

            Toast.makeText(getApplication(),"Verileri SQLite'tan çekti",Toast.LENGTH_LONG).show()

        }

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

                        Toast.makeText(getApplication(),"Verileri İnternet'ten çekti",Toast.LENGTH_LONG).show()

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