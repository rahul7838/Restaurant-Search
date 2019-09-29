package com.example.restaurentsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurentsearch.R

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    val onItemClick: (()->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    fun updateRestaurantList() {
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {onItemClick?.invoke()}
        }
    }
}