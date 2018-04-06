package com.bajabob.livedata_recyclerview

import android.arch.lifecycle.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MotoViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    /**
     * different types of data our list can represent
     */
    enum class MotoListType(
            // the layout to inflate
            @LayoutRes val layoutId: Int,
            // given a view, creates a new instance of Bindable
            val createNewViewHolder: (View) -> Bindable
    ) {
        HEADER(R.layout.view_holder_header, { v -> HeaderViewHolder(v)}),
        CLICKABLE_ENTRY(R.layout.view_holder_clickable_entry, { v -> ClickableViewHolder(v)})
    }

    /**
     * represents one item in the RV (separates view from data)
     */
    data class MotoListItem<out Any>(
            val view: MotoListType,
            val data: Any
    )

    /**
     * ViewModels -> data as received from single source of truth (db, or remote). Should only
     *  contain data that maps to views.
     */

    data class HeaderViewModel(
            val name: String
    )

    data class ClickableEntryViewModel(
            val name: String,
            @DrawableRes val icon: Int
    )

    /**
     * ViewHolders -> views that have been instantiated, that can have data bound to them
     */
    abstract class Bindable(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun onBind(viewModel: Any)
    }

    class HeaderViewHolder(val v: View) : Bindable(v){
        val header: TextView = v.findViewById(R.id.header)

        override fun onBind(viewModel: Any) {
            header.text = (viewModel as HeaderViewModel).name
        }
    }

    class ClickableViewHolder(val v: View) : Bindable(v) {
        val icon: ImageView = view.findViewById(R.id.icon)
        val itemName: TextView = view.findViewById(R.id.item_name)

        override fun onBind(viewModel: Any) {
            icon.setImageResource((viewModel as ClickableEntryViewModel).icon)
            itemName.text = (viewModel as ClickableEntryViewModel).name
        }
    }

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MotoViewModel::class.java)

        viewAdapter = MotoListAdapter(this, viewModel.motoListLiveData())

        recyclerView = findViewById<RecyclerView>(R.id.list).apply {
            adapter = viewAdapter
        }
    }
}
