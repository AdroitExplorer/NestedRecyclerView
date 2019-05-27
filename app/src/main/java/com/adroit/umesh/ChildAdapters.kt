package com.adroit.umesh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.child_adapter_layout.view.*

open class ChildAdapters(
    private val context: Context,
    private val list: MutableList<String>) :
    RecyclerView.Adapter<ChildAdapters.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val item = LayoutInflater.from(context).inflate(R.layout.child_adapter_layout, parent, false)
        item.text.text = list[0]
        return ChildViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.tv.text= list[position]
    }

    class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById<View>(R.id.text) as TextView
    }
}
