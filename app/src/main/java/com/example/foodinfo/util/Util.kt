package com.example.foodinfo.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foodinfo.R

//Vide 8.8 ve 8.9
//imageView'a yeni bir fonksiyon ekledik.
fun ImageView.loadImage(url : String?, placeholder : CircularProgressDrawable){

    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)

}

//indirilen fotoğraflar gösterilene kadar onların yerinde dönen progress barı tasarladık.
fun placeholderMaker(context : Context) : CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}