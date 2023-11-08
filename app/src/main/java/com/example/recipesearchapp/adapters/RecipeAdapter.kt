package com.example.recipesearchapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesearchapp.R
import com.example.recipesearchapp.models.Recipe

class RecipeAdapter(private val context: Context,
                    private val list:List<Recipe>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var onCLickListener:OnCLickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(
                R.layout.item_recipe, parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model=list[position]
        if(holder is MyViewHolder){
            Glide
                .with(context)
                .load(model.imageUrl)
                .centerCrop()
                .placeholder(R.color.black)
                .dontAnimate()
                .into(holder.foodImage)


            holder.foodName.text = model.title

            holder.itemView.setOnClickListener {
                if(onCLickListener!=null){
                    onCLickListener!!.onClick(position, model)
                }
            }
        }

    }

    interface OnCLickListener{
        fun onClick(position: Int, model:Recipe)
    }

    fun setOnClickListener(onCLickListener: OnCLickListener){
        this.onCLickListener=onCLickListener

    }
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val foodName= itemView.findViewById<TextView>(R.id.food_name)
        val foodImage= itemView.findViewById<ImageView>(R.id.iv_food)
    }

}