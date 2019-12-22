package com.example.navigation
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card3.view.*

class tripAdapter(val places:ArrayList<test>):RecyclerView.Adapter<tripAdapter.tripViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tripViewHolder {
        return tripViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card3,parent,false)
        )
    }

    override fun getItemCount()=places.size

    override fun onBindViewHolder(holder: tripViewHolder, position: Int) {
       var place=places[position]
        holder.view.textViewTitle.text=place.name
        holder.view.textViewDuration.text=place.duration
        holder.view.day.text=place.day
        holder.view.distance.text=place.distance
        holder.view.time.text=place.time
    }

    class tripViewHolder(val view:View):RecyclerView.ViewHolder(view)
}