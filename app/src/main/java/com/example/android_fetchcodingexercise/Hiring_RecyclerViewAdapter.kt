package com.example.android_fetchcodingexercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class HiringRecyclerViewAdapter(private val context: Context, var dataList: List<DataItem>)
    : RecyclerView.Adapter<HiringRecyclerViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.rvhiring_row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList[position]
        holder.tvId.text = item.id.toString()
        holder.tvListId.text = item.listId
        holder.tvName.text = item.name

        // Coloring of rows
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.tvId.context, R.color.lightOrange))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.tvId.context, R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // Initializing of views
         var tvId: TextView
         var tvListId: TextView
         var tvName: TextView

        init {
            tvId = itemView.findViewById(R.id.tvId)
            tvListId = itemView.findViewById(R.id.tvListId)
            tvName = itemView.findViewById(R.id.tvName)
        }
    }

    fun updateData(newList: List<DataItem>) {
        // Calculate differences and update
        val diffResult = DiffUtil.calculateDiff(MyDiffCallback(dataList, newList))
        dataList = newList
        diffResult.dispatchUpdatesTo(this)
    }

}