package com.example.materialdesign

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row_list.view.*

class RecyclerViewAdapter(val clickListener:OnItemClickListener):RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var userList  = mutableListOf<User>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater= LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list,parent,false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener{
            clickListener.onItemEditClick(userList[position])
        }
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textViewName = view.textViewName
        val textViewEmail = view.textViewEmail
        val textViewGender= view.textViewGender

        fun bind(data:User){
            textViewName.text = data.name
            textViewEmail.text = data.email
            textViewGender.text = data.gender
        }
    }
    interface OnItemClickListener{
        fun onItemEditClick(user:User)
    }

}

