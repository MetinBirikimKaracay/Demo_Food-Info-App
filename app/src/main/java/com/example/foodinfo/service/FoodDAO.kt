package com.example.foodinfo.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodinfo.model.Food

@Dao
interface FoodDAO {

    //Data Access Object
    //Video 9.4

    @Insert
    suspend fun insertAll(vararg food: Food) : List<Long>

    //Insert -> insert into işlemini yapmak için gerekli
    //suspend -> coroutine scope
    //vararg -> çok sayıda food verebilmek için

    @Query("SELECT * FROM food")
    suspend fun getAllFood() : List<Food>

    @Query("SELECT * FROM food WHERE uuid = :foodId")
    suspend fun getFood(foodId : Int) : Food

    @Query("DELETE FROM food")
    suspend fun deleteAllFood()

}