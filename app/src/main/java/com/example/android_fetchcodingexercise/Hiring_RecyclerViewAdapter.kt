package com.example.android_fetchcodingexercise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Hiring_RecyclerViewAdapter(private val context: Context, private val dataList: List<DataItem>)
    : RecyclerView.Adapter<Hiring_RecyclerViewAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Hiring_RecyclerViewAdapter.MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.rvhiring_row, parent, false)

        return Hiring_RecyclerViewAdapter.MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: Hiring_RecyclerViewAdapter.MyViewHolder, position: Int) {
        val item = dataList[position]
        holder.tvId.text = item.id.toString()
        holder.tvListId.text = item.listId
        holder.tvName.text = item.name
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         var tvId: TextView
         var tvListId: TextView
         var tvName: TextView

        init {
            tvId = itemView.findViewById(R.id.tvId)
            tvListId = itemView.findViewById(R.id.tvListId)
            tvName = itemView.findViewById(R.id.tvName)


        }

    }
}