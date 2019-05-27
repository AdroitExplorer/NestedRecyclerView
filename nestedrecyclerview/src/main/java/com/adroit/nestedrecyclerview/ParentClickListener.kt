package com.adroit.nestedrecyclerview

import androidx.recyclerview.widget.RecyclerView

interface ParentClickListener{
    fun onClick(parentPosition: Int, adapter: AdapterModel<RecyclerView.Adapter<RecyclerView.ViewHolder>>)
}
