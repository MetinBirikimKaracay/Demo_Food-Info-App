package com.example.foodinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
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

        binding.btnFoodList.setOnClickListener {
            val action = FoodDetailFragmentDirections.actionFoodDetailFragmentToFoodListFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }

}