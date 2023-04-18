package com.example.foodinfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodinfo.view.FoodDetailFragmentArgs
import com.example.foodinfo.databinding.FragmentFoodDetailBinding

class FoodDetailFragment : Fragment() {

    private lateinit var binding : FragmentFoodDetailBinding
    private var foodId : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId
            println(foodId)
        }

    }

}