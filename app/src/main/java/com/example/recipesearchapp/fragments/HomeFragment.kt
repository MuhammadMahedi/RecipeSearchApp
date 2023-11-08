package com.example.recipesearchapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipesearchapp.R
import com.example.recipesearchapp.adapters.RecipeAdapter
import com.example.recipesearchapp.databinding.FragmentHomeBinding
import com.example.recipesearchapp.models.Recipe
import com.example.recipesearchapp.models.SearchResponse
import com.example.recipesearchapp.services.RecipeApiService
import com.example.recipesearchapp.viewModels.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.searchIcon.setOnClickListener {
            var food = binding.etSearchFood.text.toString()
            Toast.makeText(requireContext(), food, Toast.LENGTH_SHORT).show()
            searchFoodItems(food)
        }





    }

    private fun searchFoodItems(food: String){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://forkify-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val recipeApi = retrofit.create(RecipeApiService::class.java)

        val call = recipeApi.searchRecipes(food)

        call.enqueue(object : Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if(response.isSuccessful){
                    val result : SearchResponse? = response.body()
                    Log.d("RecipeResponse", result.toString())
                    val recipeList= result?.recipes
                    if (recipeList != null) {

                        setAdapter(recipeList)

                    }


                    Log.d("Recepies",recipeList.toString())
                }else{
                    val rc=response.code()
                    when(rc){
                        400->{
                            Log.e("error 400","Bad Connection")
                        }
                        404->{
                            Log.e("Error 404","Response Not Found")
                        }else->{
                        Log.e("Error","Generic Error")
                    }

                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("Error",t.message.toString())
            }


        })
    }

    fun setAdapter(list: List<Recipe> ){
        val adapter = RecipeAdapter(requireContext(),list)
        binding.rvRecipeList.adapter =adapter
        //binding.rvRecipeList.layoutManager = LinearLayoutManager(requireContext())
        adapter.setOnClickListener(object :RecipeAdapter.OnCLickListener{
            override fun onClick(position: Int, model: Recipe) {
                val bundle = Bundle()
                bundle.putParcelable("FoodItem",model)
                findNavController().navigate(R.id.action_homeFragment_to_foodDetailsFragment,bundle)
            }

        })
    }


}