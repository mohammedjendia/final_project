package com.example.jerusalem

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.jerusalem_item.view.*

class JerusalemAdapter(var activity: Activity, var data: MutableList<Jerusalem>) :
    RecyclerView.Adapter<JerusalemAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName = itemView.tvName
        val tvAverage = itemView.tvAverage
        val btnDetails = itemView.btnDetails
        val image = itemView.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.jerusalem_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvName.text = data[position].name
        holder.tvAverage.text = data[position].details
        Glide.with(activity).load(data[position].Image).into(holder.image)

        holder.btnDetails.setOnClickListener {
            Toast.makeText(activity," ${data[position].name}", Toast.LENGTH_LONG).show()
        }
    }
}