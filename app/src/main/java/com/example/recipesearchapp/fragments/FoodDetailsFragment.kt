package com.example.recipesearchapp.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recipesearchapp.R
import com.example.recipesearchapp.adapters.IngredientAdapter
import com.example.recipesearchapp.databinding.FragmentFoodDetailsBinding
import com.example.recipesearchapp.models.Recipe
import com.example.recipesearchapp.models.RecipeDetails
import com.example.recipesearchapp.services.RecipeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FoodDetailsFragment : Fragment() {

    var foodItem:Recipe? = null
    var ingridentText = ""
    lateinit var binding:FragmentFoodDetailsBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        foodItem = arguments?.getParcelable("FoodItem",Recipe::class.java)
        Log.d("FoodItemShow",foodItem.toString())

        getFoodDetails(foodItem!!.recipeId)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFoodDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide
            .with(requireContext())
            .load(foodItem?.imageUrl)
            .centerCrop()
            .placeholder(R.color.black)
            .dontAnimate()
            .into(binding.imgFood)

        binding.tvFoodName.setText(foodItem?.title)

        binding.imgFood.setOnClickListener {

        }
    }


    //resolve this one
    fun getFoodDetails(id: String){


        val retrofit = Retrofit.Builder()
            .baseUrl("https://forkify-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val recipeApi = retrofit.create(RecipeApiService::class.java)


        val call = recipeApi.getRecipeDetails(id)
        call.enqueue(object : Callback<RecipeDetails>{
            override fun onResponse(
                call: Call<RecipeDetails>,
                response: Response<RecipeDetails>
            ) {
                if (response.isSuccessful) {
                    val recipeDetail = response.body()
                    val ingredients = recipeDetail?.recipe?.ingredients

                    if(ingredients!=null){
                        for (i in ingredients){
                            ingridentText = ingridentText + i +"\n\n"
                        }

                        binding.tvIngredientlist.text = ingridentText

                        Log.d("RecipeResponse", ingredients.toString())
                        val adapter = IngredientAdapter(requireContext(),ingredients!!)
                       // binding.rvIngredients.adapter = adapter
                        //binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
                    }


                    //val ingredients= recipeDetail?.ingredients


                } else {

                }
            }

            override fun onFailure(call: Call<RecipeDetails>, t: Throwable) {
                Toast.makeText(requireContext(), "failed somehow", Toast.LENGTH_SHORT).show()
            }

        })


    }


}