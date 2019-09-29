package com.example.restaurentsearch.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurentsearch.R
import com.example.restaurentsearch.data.model.Result

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    lateinit var restaurantList: ArrayList<Result>

    val onItemClick: (()->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item_view, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 50
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.restaurantNameView.text = restaurantList[position].venue.name
    }

    fun updateRestaurantList(restaurantList: ArrayList<Result>) {
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restaurantNameView: AppCompatTextView = view.findViewById(R.id.restaurant_name)
        init {
            view.setOnClickListener {onItemClick?.invoke()}
        }
    }
}