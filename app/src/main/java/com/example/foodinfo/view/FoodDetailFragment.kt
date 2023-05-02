package com.example.foodinfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.foodinfo.view.FoodDetailFragmentArgs
import com.example.foodinfo.databinding.FragmentFoodDetailBinding
import com.example.foodinfo.util.loadImage
import com.example.foodinfo.util.placeholderMaker
import com.example.foodinfo.viewmodel.FoodDetailViewModel

class FoodDetailFragment : Fragment() {

    private lateinit var binding : FragmentFoodDetailBinding
    private lateinit var viewModel : FoodDetailViewModel

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

        //çekeceğimiz besinin idsi
        arguments?.let {
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId

        }

        viewModel = ViewModelProviders.of(this).get(FoodDetailViewModel::class.java)
        viewModel.getRoomData(foodId)

        observeLiveData()

    }

    fun observeLiveData(){

        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer {food ->

            food?.let {

                binding.foodName.text = it.name
                binding.foodCalorie.text = it.calorie
                binding.foodCarbohydrate.text = it.carbohydrate
                binding.foodProtein.text = it.protein
                binding.foodFat.text = it.fat

                context?.let {
                    binding.foodImage.loadImage(food.image, placeholderMaker(it))
                }

            }

        })


    }


}