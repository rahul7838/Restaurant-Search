package com.example.restaurentsearch.ui.restaurantlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurentsearch.R
import com.example.restaurentsearch.data.db.DislikeRestaurant
import com.example.restaurentsearch.data.db.Restaurant

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.ViewHolder>() {

    lateinit var restaurantList: ArrayList<Restaurant>

    var onThumbsDownClick: (((position: Int, dislikeRestaurant: DislikeRestaurant) -> Unit))? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (restaurantList.size > 50) {
            50
        } else {
            restaurantList.size - 1
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.restaurantNameView.text = restaurantList[position].name
    }

    fun updateRestaurantList(restaurantList: ArrayList<Restaurant>) {
        this.restaurantList = restaurantList
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restaurantNameView: AppCompatTextView = view.findViewById(R.id.restaurant_name)
        private val thumbsDownView: AppCompatImageButton = view.findViewById(R.id.thumbs_down)

        init {
            thumbsDownView.setOnClickListener {
                val restaurantId = restaurantList[adapterPosition].id
                onThumbsDownClick?.invoke(adapterPosition, DislikeRestaurant(restaurantId, 0))
            }
        }
    }
}