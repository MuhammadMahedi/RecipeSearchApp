package com.example.recipesearchapp.services

import com.example.recipesearchapp.models.Recipe
import com.example.recipesearchapp.models.RecipeDetails
import com.example.recipesearchapp.models.RecipeResponse
import com.example.recipesearchapp.models.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {
    @GET("search")
    fun searchRecipes(@Query("q") query: String): Call<SearchResponse>   //<List<Recipe>>

    @GET("get")
    fun getRecipeDetails(@Query("rId") recipeId: String): Call<RecipeDetails>
}