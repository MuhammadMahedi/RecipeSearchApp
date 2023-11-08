package com.example.recipesearchapp.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("publisher_url")
    val publisherUrl: String,
    @SerializedName("recipe_id")
    val recipeId: String,
    @SerializedName("social_rank")
    val socialRank: Double,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("title")
    val title: String
) : Parcelable