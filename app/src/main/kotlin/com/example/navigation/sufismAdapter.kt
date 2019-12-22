package com.example.navigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.card.view.*

class sufismAdapter(val places:List<place>):RecyclerView.Adapter<sufismAdapter.placeholder>() {
    companion object {
        var checkedplaces = ArrayList<place>()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): placeholder {
        return placeholder(
            LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false))
    }

    override fun getItemCount()=places.size

    override fun onBindViewHolder(holder: placeholder, position: Int) {
        val place=places[position]
        holder.view.textViewDay.text=place.duration.toString()+" Hours"
        holder.view.textViewShortDesc.text=place.description
        holder.view.textViewTitle.text=place.name
        holder.view.textViewRating.isChecked=place.isSelected
        Glide.with(holder.view.context)
            .load(place.image)
            .into(holder.view.imageView)
        holder.setItemClickListener(object : placeholder.ItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                val textViewRating= v as CheckBox
                val currentPlace = places[pos]

                if (textViewRating.isChecked) {
                    currentPlace.isSelected = true
                    checkedplaces.add(currentPlace)
                } else if (!textViewRating.isChecked) {
                    currentPlace.isSelected = false
                    checkedplaces.remove(currentPlace)
                }
            }
        })

    }
    class placeholder(val view: View):RecyclerView.ViewHolder(view),View.OnClickListener{
        var textViewTitle: TextView
        var textViewDay: TextView
        var textViewShortDesc: TextView
        var textViewRating: CheckBox

        lateinit var myItemClickListener: ItemClickListener

        init {

            textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
            textViewDay = view.findViewById<TextView>(R.id.textViewDay)
            textViewShortDesc = view.findViewById<TextView>(R.id.textViewShortDesc)
            textViewRating = view.findViewById<CheckBox>(R.id.textViewRating)

            textViewRating.setOnClickListener(this)
        }

        fun setItemClickListener(ic: ItemClickListener) {
            this.myItemClickListener = ic
        }

        override fun onClick(v: View) {
            this.myItemClickListener.onItemClick(v, layoutPosition)
        }

        interface ItemClickListener {

            fun onItemClick(v: View, pos: Int)
        }

    }
}