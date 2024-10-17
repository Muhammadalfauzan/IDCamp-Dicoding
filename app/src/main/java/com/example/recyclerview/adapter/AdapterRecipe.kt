package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.DataRecipe

class AdapterRecipe : ListAdapter<DataRecipe, AdapterRecipe.ListViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickAdapter: OnItemClickAdapter


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageFood: ImageView = itemView.findViewById(R.id.img_food)
        private val nameFood: TextView = itemView.findViewById(R.id.tvNameFood)
        private val ingredients: TextView = itemView.findViewById(R.id.tvIngredients)


        fun bind(data: DataRecipe) {
            imageFood.setImageResource(data.imageFood)
            nameFood.text = data.nameFood
            ingredients.text = data.ingredients


            itemView.setOnClickListener {
                onItemClickAdapter.onItemClick(data)
            }
        }
    }

    // Set listener untuk klik item
    fun setOnItemClickListener(listener: OnItemClickAdapter) {
        this.onItemClickAdapter = listener
    }

    // Inflate layout item dan buat ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wisata, parent, false)
        return ListViewHolder(view)
    }

    // Menghubungkan data dengan ViewHolder
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    // Interface untuk menangani klik item
    interface OnItemClickAdapter {
        fun onItemClick(data: DataRecipe)
    }

    // DiffUtil untuk optimasi pembaruan data
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataRecipe>() {
            override fun areItemsTheSame(oldItem: DataRecipe, newItem: DataRecipe): Boolean {

                return oldItem.nameFood == newItem.nameFood
            }

            override fun areContentsTheSame(oldItem: DataRecipe, newItem: DataRecipe): Boolean {
                return oldItem == newItem
            }
        }
    }
}
