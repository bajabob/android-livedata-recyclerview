package com.bajabob.livedata_recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class Bindable(val view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(viewModel: Any)
}