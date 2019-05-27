package com.adroit.nestedrecyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AdapterModel<V: RecyclerView.Adapter<RecyclerView.ViewHolder>>(val canDropDown : Boolean,var adapter: V, val sectionTitle :String){
    var visibility = View.VISIBLE
}
