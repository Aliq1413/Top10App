package com.example.top10app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.top10app.databinding.ItemRowBinding

class RVAdapter(val list: List<Top10>?) : RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        return ViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        val item = list!![position]

        holder.binding.apply {
            tvTop10.text = item.name
        }
    }

    override fun getItemCount(): Int = list!!.size

}