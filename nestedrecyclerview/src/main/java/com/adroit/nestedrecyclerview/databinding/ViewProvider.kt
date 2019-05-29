package com.adroit.nestedrecyclerview.databinding

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel

interface ViewProvider {
    @LayoutRes
    fun getView(vm: ViewModel): Int
}
