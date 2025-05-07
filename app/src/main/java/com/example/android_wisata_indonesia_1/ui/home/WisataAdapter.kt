package com.example.android_wisata_indonesia_1.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.android_wisata_indonesia_1.R
import com.example.android_wisata_indonesia_1.model.Wisata

class WisataAdapter(
    private val onItemClick: (Wisata) -> Unit,
) : RecyclerView.Adapter<WisataAdapter.TaskViewHolder>() {

    private var wisataList: List<Wisata> = emptyList()

    fun submitList(list: List<Wisata>) {
        wisataList = list
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = wisataList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = wisataList[position]
        holder.tvName.text = task.name
        holder.tvDescription.text = task.description
        holder.tvLocation.text = task.location

        holder.itemView.setOnClickListener {
            onItemClick(task)
        }
    }
}
