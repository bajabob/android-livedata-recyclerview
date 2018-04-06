package com.bajabob.livedata_recyclerview

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * RecyclerView.Adapter -> listens for changes to data, handles instantiating/management of concrete views
 */
class MotoListAdapter(lifecycle: LifecycleOwner, val dataset: LiveData<List<MotoListItem<Any>>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    init {
        dataset.observe(lifecycle, Observer {
            /**
             * rebuild list when data changes. a better implementation would use DiffUtils here :-)
             */
            notifyDataSetChanged()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(MotoListType.values()[viewType].layoutId, parent, false)

        return MotoListType.values()[viewType].createNewViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val viewModel = dataset.value?.get(position)?.data ?: throw IllegalStateException("Null MotoListItem not expected")

        when (holder) {
            is Bindable -> holder.onBind(viewModel)
            else -> throw IllegalStateException("Missing bindable ViewHolder")
        }
    }

    override fun getItemViewType(position: Int) = dataset.value?.get(position)?.view?.ordinal ?: 0

    override fun getItemCount() = dataset.value?.size ?: 0
}