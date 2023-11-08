package com.example.recipesearchapp.models


import com.google.gson.annotations.SerializedName

data class RecipeX(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("publisher_url")
    val publisherUrl: String,
    @SerializedName("recipe_id")
    val recipeId: String,
    @SerializedName("social_rank")
    val socialRank: Int,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("title")
    val title: String
)