package com.example.foodinfo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodinfo.adapter.FoodRecyclerAdapter
import com.example.foodinfo.databinding.ActivityMainBinding
import com.example.foodinfo.databinding.FragmentFoodListBinding
import com.example.foodinfo.viewmodel.FoodListViewModel

class FoodListFragment : Fragment() {

    private lateinit var binding: FragmentFoodListBinding

    private lateinit var viewModel : FoodListViewModel
    private val recyclerFoodAdapter : FoodRecyclerAdapter by lazy {
        FoodRecyclerAdapter(arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        binding.foodListRecycler.layoutManager = LinearLayoutManager(context)
        binding.foodListRecycler.adapter = recyclerFoodAdapter

        observeLiveData()

    }

    fun observeLiveData(){

        //owner olarak this kullandık
        viewModel.foods.observe(this, Observer {
            it?.let {

                binding.foodListRecycler.visibility = View.VISIBLE
                recyclerFoodAdapter.refreshFoodList(it)

            }
        })

        //owner olarak viewLifecycleOwner kullandık. İkisi arasında fark yok, istediğini kullan
        viewModel.errorMesage.observe(viewLifecycleOwner, Observer {
            it?.let {

                if(it){

                    binding.foodListRecycler.visibility = View.INVISIBLE
                    binding.foodListErrorMessage.visibility = View.VISIBLE

                }
                else{
                    binding.foodListErrorMessage.visibility = View.GONE
                }

            }
        })

        viewModel.loading.observe(this, Observer {
            it?.let {

                if (it){

                    binding.foodListRecycler.visibility = View.GONE
                    binding.foodListErrorMessage.visibility = View.GONE
                    binding.foodListProgressBar.visibility = View.VISIBLE

                }
                else{

                    binding.foodListProgressBar.visibility = View.INVISIBLE

                }

            }
        })

    }

}