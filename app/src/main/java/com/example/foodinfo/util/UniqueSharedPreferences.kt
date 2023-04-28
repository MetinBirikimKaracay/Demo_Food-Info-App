package com.example.foodinfo.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class UniqueSharedPreferences {

    companion object{

        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : UniqueSharedPreferences? = null
        private var lock = Any()
        operator fun invoke(context: Context) : UniqueSharedPreferences = instance ?: synchronized(lock){

            instance ?: uniqueSharedPreferenceMaker(context).also {
                instance = it
            }

        }

        private fun uniqueSharedPreferenceMaker(context: Context): UniqueSharedPreferences{
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return UniqueSharedPreferences()
        }


    }

    fun timer(time : Long){

        sharedPreferences?.edit(commit = true){
            putLong("time",time)
        }

    }


}