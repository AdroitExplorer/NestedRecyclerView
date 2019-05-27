package com.adroit.nestedrecyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NestedRecyclerView : RecyclerView {

    var parentClickListener: ParentClickListener? = null
    set(value) {
        if(adapter!=null){
            (adapter as MainAdapter).parentClickListener = value
        }
        field = value
    }
    constructor(context: Context) : this(context, null)
    constructor (context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor (context :Context, attrs : AttributeSet?, defStyle :Int) : super(context, attrs, defStyle){
        layoutManager = LinearLayoutManager(context)
    }

    fun setChildAdapters(childAdapter : MutableList<AdapterModel<Adapter<ViewHolder>>>){
        adapter = MainAdapter(context, childAdapter)
    }
}
