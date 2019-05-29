package com.adroit.nestedrecyclerview.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.adroit.nestedrecyclerview.BR

internal open class BindingRecyclerViewAdapter<T : ViewModel>(
    protected val viewModels: ObservableList<T>, private val viewProvider: ViewProvider,
    private val binder: ViewModelBinder
) : RecyclerView.Adapter<BindingRecyclerViewAdapter.DataBindingViewHolder<ViewDataBinding>>() {
    private val callback: SimpleObservableListCallback<T> = SimpleObservableListCallback(this)
    private val defaultViewModelBinder = object : ViewModelBinder {
        override fun bind(binding: ViewDataBinding, vm: ViewModel) {
            binding.setVariable(BR.vm, vm)
        }
    }

    val isEmpty: Boolean
        get() = itemCount == 0

    init {
        this.viewModels.addOnListChangedCallback(callback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<ViewDataBinding> {
        return DataBindingViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<ViewDataBinding>, position: Int) {
        val vm = getObjForPosition(position)
        defaultViewModelBinder.bind(holder.binding, vm)
        binder.bind(holder.binding, vm)
        holder.binding.executePendingBindings()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        viewModels.removeOnListChangedCallback(callback)
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    protected fun getObjForPosition(position: Int): T {
        return viewModels[position]
    }

    override fun getItemViewType(position: Int): Int {
        return viewProvider.getView(getObjForPosition(position))
    }

    internal class DataBindingViewHolder<T : ViewDataBinding> private constructor(val binding: T) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun <T : ViewDataBinding> create(
                parent: ViewGroup,
                @LayoutRes layoutId: Int
            ): DataBindingViewHolder<T> {
                val binding = DataBindingUtil.inflate<T>(
                    LayoutInflater.from(parent.context),
                    layoutId, parent, false
                )
                return DataBindingViewHolder(binding)
            }
        }
    }
}
