package com.example.recipesearchapp.models


import com.google.gson.annotations.SerializedName

data class RecipeDetails(
    @SerializedName("recipe")
    val recipe: RecipeX
)