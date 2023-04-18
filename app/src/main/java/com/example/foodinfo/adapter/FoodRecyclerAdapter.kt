package com.example.foodinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodinfo.databinding.FoodRecyclerRowBinding
import com.example.foodinfo.model.Food

class FoodRecyclerAdapter(val foodList:ArrayList<Food>) :RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder>() {

    class FoodViewHolder(val binding: FoodRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        val binding = FoodRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.binding.recyclerRowFoodName.text = foodList.get(position).name
        holder.binding.recyclerRowFoodCalorie.text = foodList.get(position).calorie

        //gorsel çekme eklenecek

    }

    fun refreshFoodList(newFoodList : ArrayList<Food>){

        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()

    }
}