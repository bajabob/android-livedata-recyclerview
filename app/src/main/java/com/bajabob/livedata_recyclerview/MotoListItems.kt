package com.bajabob.livedata_recyclerview

import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.view.View
import android.widget.ImageView
import android.widget.TextView

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
class HeaderViewHolder(v: View) : Bindable(v){
    val header: TextView = v.findViewById(R.id.header)

    override fun onBind(viewModel: Any) {
        header.text = (viewModel as HeaderViewModel).name
    }
}

class ClickableViewHolder(v: View) : Bindable(v) {
    val icon: ImageView = view.findViewById(R.id.icon)
    val itemName: TextView = view.findViewById(R.id.item_name)

    override fun onBind(viewModel: Any) {
        icon.setImageResource((viewModel as ClickableEntryViewModel).icon)
        itemName.text = (viewModel as ClickableEntryViewModel).name
    }
}