package com.example.recipesearchapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesearchapp.R
import com.example.recipesearchapp.models.Recipe

class IngredientAdapter(private val context: Context,
                        private val list:List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipeAdapter.MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_ingredient, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model=list[position]
        if(holder is MyViewHolder){
            holder.tvIngredient.text = model
        }
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvIngredient= itemView.findViewById<TextView>(R.id.tv_ingred)
    }
}