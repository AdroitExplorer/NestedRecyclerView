package com.adroit.nestedrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_adapter_layout.view.*

open class MainAdapter(
    private val context: Context,
    private val list: MutableList<AdapterModel<RecyclerView.Adapter<RecyclerView.ViewHolder>>>,
    private val rvp: RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var parentClickListener: ParentClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.main_adapter_layout, parent, false)
        return MainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val manager = CustomLinearLayoutManager(context)
        holder.itemView.child_rv.layoutManager = manager
        holder.itemView.child_rv.adapter = list[position].adapter
        holder.itemView.child_rv.setRecycledViewPool(rvp)
        holder.itemView.section_title.text = list[position].sectionTitle

        holder.itemView.drop_down.visibility = if (list[position].canDropDown) View.VISIBLE else View.GONE
        holder.itemView.child_rv.visibility = list[position].visibility
        holder.itemView.child_rv.isNestedScrollingEnabled = true
        holder.itemView.drop_down.setOnClickListener { reverseChildVisibility(list[position], holder.itemView.child_rv) }
        holder.itemView.setOnClickListener { parentClickListener?.onClick(position, list[position]) }
    }

    private fun reverseChildVisibility(
        model: AdapterModel<RecyclerView.Adapter<RecyclerView.ViewHolder>>,
        recyclerView: RecyclerView
    ) {
        if (model.visibility == View.VISIBLE) {
            model.visibility = View.GONE
            recyclerView.visibility = View.GONE
        } else {
            model.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
        }
    }

    open class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
