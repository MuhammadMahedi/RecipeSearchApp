package com.example.recipesearchapp.models


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("recipes")
    val recipes: List<Recipe>
)