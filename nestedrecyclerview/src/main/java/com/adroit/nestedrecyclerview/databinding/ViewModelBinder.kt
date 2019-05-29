package com.adroit.nestedrecyclerview.databinding

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

interface ViewModelBinder {
    fun bind(binding: ViewDataBinding, vm: ViewModel)
}
